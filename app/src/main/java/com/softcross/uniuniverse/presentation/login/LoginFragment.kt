package com.softcross.uniuniverse.presentation.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.softcross.uniuniverse.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

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
        tempUserList.add("User 1")
        tempUserList.add("User 2")
        tempUserList.add("User 3")
        tempUserList.add("User 4")
        tempUserList.add("User 5")
        tempUserList.add("User 6")
        tempUserList.add("User 7")
        binding.rvProfilesLogin.adapter = ProfilesAdapter(tempUserList, ::onItemClick)
        binding.rvProfilesLogin.layoutManager =
            CenterZoomLayoutManager(requireContext(), HORIZONTAL, 3)
    }

    private fun onItemClick(itemID: Int) {
        binding.rvProfilesLogin.smoothScrollToPosition(itemID)
    }
}