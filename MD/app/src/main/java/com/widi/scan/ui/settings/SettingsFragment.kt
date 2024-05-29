package com.widi.scan.ui.settings

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.widi.scan.databinding.FragmentSettingsBinding
import com.widi.scan.ui.utils.safeNavigate

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth

        binding?.btnLogout?.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        binding?.progressBar?.visibility = View.VISIBLE
        auth.signOut()
        binding?.progressBar?.visibility = View.GONE
        findNavController().safeNavigate(SettingsFragmentDirections.actionSettingsFragmentToLoginFragment())
    }


}