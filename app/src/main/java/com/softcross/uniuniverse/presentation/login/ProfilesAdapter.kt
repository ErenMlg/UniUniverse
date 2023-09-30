package com.softcross.uniuniverse.presentation.login

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.common.util.navigate
import com.softcross.uniuniverse.data.model.entities.User
import com.softcross.uniuniverse.databinding.LoginProfileItemBinding


class ProfilesAdapter(private val usersList: List<User>, val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ProfilesAdapter.ProfilesCardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesCardHolder =
        ProfilesCardHolder(
            LoginProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: ProfilesCardHolder, position: Int) {
        val profileOnTheQueue = usersList[position]
        holder.itemView.setOnClickListener {
            it.requestFocus()
            onItemClick(position)
        }
        holder.bind(profileOnTheQueue)
    }

    inner class ProfilesCardHolder(private val binding: LoginProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(binding) {
            root.setOnClickListener {
                ivProfile.strokeWidth = 5f
                if (user.userID == 1) {
                    Navigation.navigate(it, R.id.NavLoginToRegister)
                }
            }
            val userFullName = user.userName + " " + user.userSurname
            tvUserName.text = userFullName
            ivProfile.setImageBitmap(user.userPhoto)
        }
    }
}