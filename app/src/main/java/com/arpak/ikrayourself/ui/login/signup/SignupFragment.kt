package com.arpak.ikrayourself.ui.login.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.arpak.ikrayourself.R
import com.arpak.ikrayourself.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar


class SignupFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null

    private val binding get() = _binding!!

    private val viewModel by lazy { SignUpFragmentViewModel() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpFragmentNesnesi = this

        initObservers()
    }

    private fun initObservers() = with(binding) {
        //This area can  fill with information
        viewModel.isInfoValid.observe(viewLifecycleOwner) { it ->
            if (it.not()) {
                Snackbar.make(requireView(), R.string.incomplete_info_entered, 1000).show()
            }
            viewModel.isValidMail.observe(viewLifecycleOwner) {
                emailTextInput.error = if (!it) getString(R.string.invalid_email_address) else ""
            }

            viewModel.isPasswordMatch.observe(viewLifecycleOwner) {
                passwordEditText.error = if (!it) getString(R.string.password_match_error) else ""
            }

            viewModel.isSignUp.observe(viewLifecycleOwner) {
                if (it) {
                    R.string.successfully_created
                    clearEmptyFields()
                } else {
                    emailTextInput.error = getString(R.string.mail_registered)
                }
            }
        }

    }

    private fun clearEmptyFields() {
        binding.emailEditText.setText("")
        binding.emailTextInput.error = ""
        binding.passwordEditText.setText("")
        binding.passwordInputLayout.error = ""
        binding.confirmPasswordEditText.setText("")
        binding.confirmPasswordTextLayout.error = ""
        binding.phoneNumberEditText.setText("")
        binding.phoneNumberInputLayout.error = ""

    }

    fun signUpButton(
        email: String,
        password: String,
        confirmPassword: String,
        phoneNumber: String
    ) = viewModel.signUp(email, password, confirmPassword, phoneNumber)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}