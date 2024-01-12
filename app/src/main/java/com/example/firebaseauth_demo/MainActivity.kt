package com.example.firebaseauth_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseauth_demo.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.goLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.SignupBtn.setOnClickListener {

            if (binding.userName.text.toString().isEmpty() || binding.enterPass.text.toString()
                    .isEmpty()
                || binding.confPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }

            else if (binding.etEmail.text.toString().isEmpty() || binding.enterPass.text.toString()
                    .isEmpty()
                || binding.confPassword.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
            else if (binding.enterPass.text.toString() != binding.confPassword.text.toString()) {
                Toast.makeText(
                    this,
                    "Password and confirm password should be same*",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (binding.enterPass.text.toString().length < 8) {
                Toast.makeText(
                    this,
                    "Password should be at least 8 characters long",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                Firebase.auth.createUserWithEmailAndPassword(
                    binding.etEmail.text.toString(),
                    binding.enterPass.text.toString()
                )
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            startActivity(Intent(this, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                Toast.makeText(this, "Registration Successful*", Toast.LENGTH_SHORT).show()


            }

        }
    }
}
