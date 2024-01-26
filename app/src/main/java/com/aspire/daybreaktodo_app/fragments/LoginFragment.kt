package com.aspire.daybreaktodo_app.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.aspire.daybreaktodo_app.R
import com.aspire.daybreaktodo_app.databinding.FragmentLoginBinding
import com.aspire.daybreaktodo_app.databinding.FragmentSignUpBinding
import com.aspire.daybreaktodo_app.utils.validateEditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var navControl : NavController
    private lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()

    }

    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }
    private fun registerEvents() {
        binding.toSignupTv.setOnClickListener {
            navControl.navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        binding.loginBtn.setOnClickListener {
            val email = binding.loginEmailTiet.text.toString().trim()
            val pass = binding.loginPassTiet.text.toString().trim()

            if(email.isNotEmpty() && pass.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                    OnCompleteListener {
                        binding.loginProgress.visibility = View.VISIBLE
                        binding.loginBtn.visibility = View.INVISIBLE
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                            navControl.navigate(R.id.action_loginFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_LONG)
                                .show()
                        }
                }).addOnFailureListener(OnFailureListener{
                    binding.loginProgress.visibility = View.INVISIBLE
                    binding.loginBtn.visibility = View.VISIBLE
                    Toast.makeText(context,"Unexpected Error Occured",Toast.LENGTH_SHORT).show()
                })
            }
        }
    }


}