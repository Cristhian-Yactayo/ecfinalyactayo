package com.yactayo.ecfinal_yactayo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yactayo.ecfinal_yactayo.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        acceso()
        firebaseAuth = Firebase.auth
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if (it.resultCode == RESULT_OK){
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    signInFirebaseWithGoogle(account.idToken)
                }catch (e:Exception){

                }
            }
        }

    }

    private fun signInFirebaseWithGoogle(idToken: String?) {

        val authCredential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(authCredential)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user: FirebaseUser = firebaseAuth.currentUser!!
                    // navegar a la siguiente pantalla
                    // guardar en shared preferences
                    /*sharedPreferences.edit().apply{
                        putString(EMAIL, user.email)
                            .apply()
                    }*/
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Ocurrio un error", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun acceso(){

        binding.txtCorreo.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validaciones(text.toString(), binding.txtPassword.editText?.text.toString())
        }
        binding.txtPassword.editText?.addTextChangedListener { text ->
            binding.btnLogin.isEnabled = validaciones(binding.txtCorreo.editText?.text.toString(), text.toString())
        }
        binding.btnLogin.setOnClickListener {

            signInWithEmailPassword()

        }

        binding.btnGoogle.setOnClickListener {
            entrarconGoogle()
        }

        binding.btnRegistrate.setOnClickListener {

            val intent = Intent(this, Registro::class.java)
            startActivity(intent)

        }


    }

    private fun signInWithEmailPassword() {
        val email = binding.txtCorreo.editText?.text.toString()
        val password = binding.txtPassword.editText?.text.toString()
        signInFirebaseWithEmail(email, password)

    }

    private fun signInFirebaseWithEmail(email: String, password: String){

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val user = firebaseAuth.currentUser
                    val intent = Intent(this, Menu::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "El usuario no se encontro", Toast.LENGTH_SHORT)
                }
            }


    }

    private fun entrarconGoogle() {
        val googleSignOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.clientId))
            .requestEmail().build()
        val client: GoogleSignInClient = GoogleSignIn.getClient(this, googleSignOptions)
        val intent = client.signInIntent
        googleLauncher.launch(intent)
    }




    private fun validaciones(correo: String, password: String): Boolean{
        val CorreoOk = correo.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(correo).matches()
        val PasswordOk = password.length >= 4
        return PasswordOk && CorreoOk
    }


}