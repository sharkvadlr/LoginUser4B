package com.example.loginuser4b

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Indica cual es la pantalla que se va a mostrar
        setContentView(R.layout.activity_main)

        // Valores inmutables para los valores requeridos
        var usuario = "admin"
        var pass = "admin123"

        // Valor mutable para contar los intentos fallidos
        var intentosFallidos = 0


        // Hacer referencia a los elementos de la vista
        // R hace referencia a todos los recursos del proyecto
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvMensaje = findViewById<TextView>(R.id.tvMensaje)

        // Crear un evento con el método de Button
        // Cuando el usuario de click al botón, verá un saludo
        btnLogin.setOnClickListener {

            // Crear variable y guardar el texto que escribe el usuario - GetText()
            val usuarioT = etUsuario.text.toString()
            val contraseñaT = etPass.text.toString()

            // Validar si el usuario y contraseña son correctos
            if (usuarioT == usuario && contraseñaT == pass){
                // Mandar mensaje al usuario con el TextView - SetText()
                tvMensaje.text = "Bienvenido $usuario, Inicio de Sesión con Éxito"
                intentosFallidos = 0
            } else {
                // Si falla, aumentamos el contador de intentos
                intentosFallidos++

                // Al tercer intento fallido
                if ( intentosFallidos >= 3){
                    tvMensaje.text = "Demasiados intentos fallidos. Favor de esperar al menos" +
                            " 3 minutos para volver a intertar"
                    // Se bloquea el boton al momento de agotar los intentos
                    btnLogin.isEnabled = false
                } else {
                    tvMensaje.text = "Credenciales Incorrectas. $intentosFallidos de 3"
                }
            }

        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}