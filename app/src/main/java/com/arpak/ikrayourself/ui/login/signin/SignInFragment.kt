package com.arpak.ikrayourself.ui.login.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.arpak.ikrayourself.MainActivity
import com.arpak.ikrayourself.R
import com.arpak.ikrayourself.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar


class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy { SignInFragmentViewModel() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.isSignFragmentNesnesi = this
        initObservers()
    }

    private fun initObservers() = with(viewModel) {

        isSignIn.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Snackbar.make(requireView(), "Wrong email-password", 1000).show()
            }
        }
    }


    fun signButton(email: String, password: String) = viewModel.signIn(email, password)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}