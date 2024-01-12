package com.example.firebaseauth_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauth_demo.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.goRegister.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

        mAuth = FirebaseAuth.getInstance()
        binding.loginBt.setOnClickListener {
            signInWithEmailAndPassword(binding.logemail.text.toString(),binding.logPass.text.toString())
        }



    }
    private fun signInWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    startActivity(Intent(this,HomeActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            }
    }

    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser != null){
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
    }

}