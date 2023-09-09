package com.softcross.uniuniverse.presentation.login

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.databinding.LoginProfileItemBinding


class ProfilesAdapter(private val usersList: List<String>) :
    RecyclerView.Adapter<ProfilesAdapter.ProfilesCardHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfilesCardHolder =
        ProfilesCardHolder(
            LoginProfileItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: ProfilesCardHolder, position: Int) {
        val profileOnTheQueue = usersList[position]
        holder.bind(profileOnTheQueue)
    }

    inner class ProfilesCardHolder(private val binding: LoginProfileItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: String) = with(binding) {
            tvUserName.text = user
        }
    }
}