package com.example.kotlin_basic.ui.login
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_basic.R
import com.example.kotlin_basic.data.model.LoginRequest
import com.example.kotlin_basic.databinding.FragmentLoginBinding
import com.example.kotlin_basic.utils.NetworkResult


class loginFragment : Fragment(R.layout.fragment_login){

    // Manual Create a Shipment
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            callLoginApi();
        }
        observeLogin()
    }

    private fun callLoginApi() {
        var request = LoginRequest(
            email = binding.etEmail.text.toString().trim(),
            password = binding.etPassword.text.toString().trim(),
        )
        viewModel.login(request)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loadingOverlay.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !isLoading
    }

    private fun observeLogin() {
        viewModel.loginResult.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Loading -> {
                    showLoading(true)
                }

                is NetworkResult.Success -> {
                    showLoading(false)
                    Toast.makeText(
                        requireContext(),
                        "Welcome ${result.data.data.firstName}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Error -> {
                    showLoading(false)
                    Toast.makeText(
                        requireContext(),
                        result.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}

