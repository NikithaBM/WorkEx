package com.example.assignment.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.data.UserProfile
import com.example.assignment.databinding.SingleUserItemBinding

class UserAdapter( val fragment : Fragment): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: SingleUserItemBinding) : RecyclerView.ViewHolder(binding.root)
    {
        val name = binding.tvName
        val website = binding.tvWebsite
        val clUserCard = binding.clUserCard
    }
    private val diffCallback = object : DiffUtil.ItemCallback<UserProfile>() {
        override fun areItemsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserProfile, newItem: UserProfile): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var users : List<UserProfile>
        get() = differ.currentList
        set(value){ differ.submitList(value)}



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(SingleUserItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            val user = users[position]

            holder.name.text = user.name
            holder.website.text = user.website

            holder.clUserCard.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("email", user.email)
                NavHostFragment.findNavController(fragment).navigate(R.id.action_userFragment_to_profileFragment, bundle)
            }

        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    // method for filtering our recyclerview items.
    fun filterList(filterllist: List<UserProfile>) {
        // below line is to add our filtered
        // list in our course array list.
        users = filterllist
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged()
    }

}