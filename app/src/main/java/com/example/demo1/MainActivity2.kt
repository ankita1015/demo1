package com.example.demo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    lateinit var txtEmail:TextView
    lateinit var txtPwd: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var email = intent.getStringExtra("email")
        var pwd = intent.getStringExtra("pwd")

        txtEmail  =findViewById(R.id.txtEmail)
        txtPwd  =findViewById(R.id.txtPwd)

        txtEmail.setText(email)
        txtPwd.setText(pwd)

        Log.d("SecondActivity","Email : $email")
        Log.d("SecondActivity","Password : $pwd")
    }
}