package com.softcross.uniuniverse.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.softcross.uniuniverse.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding:FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tempUserList = ArrayList<String>()
        tempUserList.add("Eren Mollaoğlu")
        tempUserList.add("Ceren Molla")
        tempUserList.add("Gülçin Yaşar")
        tempUserList.add("Çiğdem Oğurlu")
        binding.rvProfilesLogin.adapter = ProfilesAdapter(tempUserList)

    }
}