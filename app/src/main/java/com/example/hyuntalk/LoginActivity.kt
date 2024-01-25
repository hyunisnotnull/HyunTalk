package com.example.hyuntalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hyuntalk.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인증 초기화
        mAuth = Firebase.auth

        // login button event
        binding.loginBtn.setOnClickListener {
            val email = binding.emailEdit.text.toString()
            val password = binding.passwordEdit.text.toString()

            login(email, password)
        }

        // signup button event
        binding.signupBtn.setOnClickListener {
            val intent: Intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)
        }
    }
    // login
    private fun login(email: String, password: String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            task -> if (task.isSuccessful){
                val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
            Log.d("Login", "Error: ${task.exception}")
        }
        }
    }
}