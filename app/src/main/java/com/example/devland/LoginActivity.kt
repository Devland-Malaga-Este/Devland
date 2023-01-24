package com.example.devland

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.devland.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var etEmail: EditText? = binding.etMail
    private var etPassword: EditText? = binding.etPassword
    private var btnInicioSesion: Button? = binding.btnInicioSesion
    private var tvOlvidarPwd: TextView = binding.tvOlvidarPwd
    private var tvRegistro: TextView = binding.tvRegistro
    private var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prefs = getSharedPreferences("app", MODE_PRIVATE)

        establecerValoresSiExisten()

        tvOlvidarPwd.setOnClickListener {
            goToOlvidarPwd()
        }

        tvRegistro.setOnClickListener {
            goToRegistro()
        }

        btnInicioSesion?.setOnClickListener {
            val email = etEmail!!.text.toString()
            val password = etPassword!!.text.toString()

            if (login(email, password)) {
                goToFeed()
            }
            guardarPreferencias(email, password)
        }
    }

    private fun comprobarCorreo(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun comprobarPassword(password: String): Boolean {
        return !TextUtils.isEmpty(password) && password.length > 7
    }

    private fun login(email: String, password: String): Boolean {
        var valido = false
        if (!comprobarCorreo(email)) {
            Toast.makeText(
                this,
                "El correo electrónico introducido, no es válido. Introduce un e-mail correcto.",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!comprobarPassword(password)) {
            Toast.makeText(
                this,
                "Contraseña no válida. Ingresa al menos una contraseña de 8 caracteres.",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            valido = true
        }
        return valido
    }

    private fun guardarPreferencias(email: String, password: String) {
        val editor = prefs!!.edit()

        editor.putString("email", email)
        editor.putString("password", password)
        editor.apply()
    }

    private fun establecerValoresSiExisten() {
        val email = prefs!!.getString("email", "")
        val password = prefs!!.getString("password", "")
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            etEmail!!.setText(email)
            etPassword!!.setText(password)
        }
    }

    private fun goToFeed() {
        //val intent = Intent(this, MainActivity::class.java)
        //Evita que pasemos de nuevo a la activity login
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // startActivity(intent)
    }

    private fun goToRegistro() {
        //val intent = Intent(this, MainActivity::class.java)
        //Evita que pasemos de nuevo a la activity login
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // startActivity(intent)
    }

    private fun goToOlvidarPwd() {
        //val intent = Intent(this, MainActivity::class.java)
        //Evita que pasemos de nuevo a la activity login
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        // startActivity(intent)
    }
}