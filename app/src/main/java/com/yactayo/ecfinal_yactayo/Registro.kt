package com.yactayo.ecfinal_yactayo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yactayo.ecfinal_yactayo.databinding.ActivityRegistroBinding

class Registro : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        registro()
    }


    private fun registro(){

        binding.btnRegistrate.setOnClickListener {
            signUpWithEmailPassword()
        }

    }

    private fun signUpWithEmailPassword() {
        val email = binding.txtCorreo.editText?.text.toString()
        val password = binding.txtPassword.editText?.text.toString()
        if (validaciones(email, password)){

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    if (it.isSuccessful){
                        Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT)
                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Error al crear usuario", Toast.LENGTH_SHORT)
                    }
                }

        }else{
            Toast.makeText(this, "Credenciales no validas", Toast.LENGTH_SHORT)
        }

    }


    private fun validaciones(correo: String, password: String): Boolean{
        val CorreoOk = correo.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()
        val PasswordOk = password.length >= 4
        return PasswordOk && CorreoOk
    }
}