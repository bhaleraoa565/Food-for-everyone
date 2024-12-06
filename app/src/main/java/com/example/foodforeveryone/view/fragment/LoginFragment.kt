package com.example.foodforeveryone.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.foodforeveryone.R
import com.example.foodforeveryone.view.activity.DashboardActivity
import com.example.foodforeveryone.viewmodel.LoginViewModel
import com.google.android.material.textfield.TextInputEditText
import android.widget.TextView

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Find views by ID
        val emailField: TextInputEditText = view.findViewById(R.id.emailId)
        val passwordField: TextInputEditText = view.findViewById(R.id.password)
        val loginButton: TextView = view.findViewById(R.id.login)

        // Observe ViewModel's login state
        loginViewModel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginViewModel.LoginState.Success -> {
                    // Navigate to MainActivity
                    val intent = Intent(requireContext(), DashboardActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                    Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show()
                }
                is LoginViewModel.LoginState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Set click listener on the login button
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()
            loginViewModel.validateCredentials(email, password)
        }
    }
}
