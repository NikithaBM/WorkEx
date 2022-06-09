package com.example.assignment.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.assignment.R
import com.example.assignment.data.Credentials
import com.example.assignment.databinding.FragmentLoginBinding
import com.example.assignment.viewmodel.UserViewModel


class LoginFragment : Fragment() {
   private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        binding.btnSignUp.isEnabled = true

        binding.btnSignUp.setOnClickListener {

            if(!binding.etEmail.text.isValidEmail())
            {
                Toast.makeText(requireContext(), "Please enter valid email", Toast.LENGTH_SHORT).show()
                binding.btnSignUp.isEnabled = false
            }

            if(!isValidPassword(binding.etPassword.text.toString()))
            {
                Toast.makeText(requireContext(), "Please enter valid password", Toast.LENGTH_SHORT).show()
                binding.btnSignUp.isEnabled = false
            }

            if(binding.etEmail.text.isValidEmail() && isValidPassword(binding.etPassword.text.toString())) {
               val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
                val credential = Credentials(0,binding.etEmail.text.toString() )
                userViewModel.saveEmail(credential)

                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_loginFragment_to_userFragment)
            }
        }

        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSignUp.isEnabled = true
            }
        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnSignUp.isEnabled = true
            }
        })

        binding.tvSignUp.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_signUpFragment)
        }

    }

    private fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun isValidPassword(password: String?) : Boolean {
        password?.let {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,10}$"
            val passwordMatcher = Regex(passwordPattern)

            return passwordMatcher.find(password) != null
        } ?: return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}