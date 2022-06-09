package com.tokn.workexassignment.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.assignment.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        if(arguments?.getString("email")?.isNotEmpty() == true)
            binding.tvEmail.text = arguments?.getString("email")
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}