package com.arpak.ikrayourself.ui.main.navfragment.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.arpak.ikrayourself.R
import com.arpak.ikrayourself.databinding.FragmentProfileBinding
import com.arpak.ikrayourself.ui.login.LoginActivity


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding
        get() = _binding!!

    private val viewModel by lazy { ProfileFragmentViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.profileFragmentObj = this


        // SignOut Button use to press

        binding.profileSignOutButton.setOnClickListener {

            viewModel.signOut()
            val intent = Intent(requireActivity(),LoginActivity::class.java)
            startActivity(intent)
            TODO()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}