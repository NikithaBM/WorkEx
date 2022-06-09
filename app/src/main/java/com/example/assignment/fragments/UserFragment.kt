package com.example.assignment.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.adapter.UserAdapter
import com.example.assignment.data.UserProfile
import com.example.assignment.databinding.FragmentUserBinding
import com.example.assignment.viewmodel.UserViewModel
import java.util.*


class UserFragment : Fragment() {

    private lateinit var binding: FragmentUserBinding
    private lateinit var adapter: UserAdapter

    private val listUsers = mutableListOf<UserProfile>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        userViewModel.getData().observe(viewLifecycleOwner)
        { t ->

            adapter = UserAdapter(this)

            t?.let { listUsers.addAll(it) }
             Log.d("User", "${listUsers.size} and ${listUsers[0]}")
            adapter.users = listUsers
            binding.rvUserList.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            binding.rvUserList.setHasFixedSize(true)
            binding.rvUserList.adapter = adapter

        }



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        // below line is to get our inflater

        // inside inflater we are inflating our menu file.

        // inside inflater we are inflating our menu file.
       // activity?.menuInflater?.inflate(R.menu.search, menu)

        // below line is to get our menu item.

        // below line is to get our menu item.
        val searchItem = menu.findItem(R.id.actionSearch)

        // getting search view of our item.

        // getting search view of our item.
        val searchView = searchItem?.actionView as SearchView

        // below line is to call set on query text listener method.

        // below line is to call set on query text listener method.
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                if (newText != null) {
                    filter(newText)
                }
                return false
            }
        })
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<UserProfile> = ArrayList()

        // running a for loop to compare elements.
        for (item in listUsers) {
            // checking if the entered string matched with any item of our recycler view.

            if (item.name.toLowerCase(Locale.ROOT).contains(text.lowercase(Locale.getDefault()))) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(requireContext(), "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist)
        }
    }
}