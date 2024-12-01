package com.example.foodforeveryone

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hardcoded credentials
        val correctEmail = "Abhi@gmail.com"
        val correctPassword = "123456"

        // Find views by ID

        val emailField: TextInputEditText = view.findViewById(R.id.emailId)
        val passwordField: TextInputEditText = view.findViewById(R.id.password)
        val loginButton: TextView = view.findViewById(R.id.login)

        // Set click listener on the login button
        loginButton.setOnClickListener {
            val email = emailField.text.toString().trim()
            val password = passwordField.text.toString().trim()

            if (email == correctEmail && password == correctPassword) {
                // Navigate to MainActivity
                val intent = Intent(requireContext(), DashboardActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
                Toast.makeText(requireContext(),"Login Successfully",Toast.LENGTH_SHORT).show()
            } else {
                // Show error message
                Toast.makeText(requireContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
