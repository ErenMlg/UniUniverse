package com.softcross.uniuniverse.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.softcross.uniuniverse.R
import com.softcross.uniuniverse.common.util.navigate
import com.softcross.uniuniverse.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllUser().observe(viewLifecycleOwner) {
            binding.rvProfilesLogin.adapter =
                ProfilesAdapter(it, ::onItemClick)
            binding.rvProfilesLogin.layoutManager =
                CenterZoomLayoutManager(requireContext(), HORIZONTAL, 3)
        }

        binding.apply {
            tvLoginToRegister.setOnClickListener {
                Navigation.navigate(it, R.id.NavLoginToRegister)
            }
        }
    }

    private fun onItemClick(itemID: Int) {
        binding.rvProfilesLogin.smoothScrollToPosition(itemID)
    }
}