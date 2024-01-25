package com.example.hyuntalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.hyuntalk.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    lateinit var  mAuth: FirebaseAuth

    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //인증 초기화
        mAuth = Firebase.auth

        //
        mDbRef = Firebase.database.reference

        binding.signupBtn.setOnClickListener {
            val name = binding.nameEdit.text.toString().trim()
            val email = binding.emailEdit.text.toString().trim()
            val password = binding.passwordEdit.text.toString().trim()

            signup(name, email, password)
        }
    }

    // signup
    private fun signup(name: String, email:String, password:String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            task -> if(task.isSuccessful) {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
            val intent: Intent = Intent(this@SignupActivity, MainActivity::class.java)
            startActivity(intent)
            addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
        } else {
            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
            Log.d("SignUp", "Error: ${task.exception}")
        }
        }
    }
    private fun addUserToDatabase(name: String, email: String, uId: String){
        mDbRef.child("user").child(uId).setValue(User(name, email, uId))
    }
}