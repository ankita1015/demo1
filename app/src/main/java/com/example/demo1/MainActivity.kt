package com.example.demo1

import android.Manifest
import android.R.attr
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.R.attr.phoneNumber




class MainActivity : AppCompatActivity() {
    lateinit var callUser : Button
    lateinit var Email : Button
    lateinit var Gmail : Button
    lateinit var Website :Button
    lateinit var Playstore:Button
    lateinit var sms:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callUser = findViewById(R.id.btnCallUser)
        Email=findViewById(R.id.btnEmail)
        Gmail = findViewById(R.id.btnGmail)
        Website = findViewById(R.id.btnWebsite)
        Playstore = findViewById(R.id.btnPlayStore)
        sms=findViewById(R.id.btnSms)
    }

    override fun onStart() {
        super.onStart()

        Email.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("ankita151015@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "about")
            intent.putExtra(Intent.EXTRA_TEXT, "helooo.....")
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(intent, ""))
            }
        }

        Gmail.setOnClickListener {
            val uriText = "mailto:ankita151015@gmail.com" +
                    "?subject=" + Uri.encode("About") +
                    "&body=" + Uri.encode("hello world....")

            val uri = Uri.parse(uriText)

            val sendIntent = Intent(Intent.ACTION_SENDTO)
            sendIntent.data = uri
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(Intent.createChooser(sendIntent, "Send email"))
            }
        }

        Website.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com"))
            if (browserIntent.resolveActivity(packageManager) != null) {
                startActivity(browserIntent)
            }
        }

        Playstore.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=" + this.packageName)
            )
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }
        }

        sms.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + 9998923489))
            intent.putExtra("sms_body", "abc")
            startActivity(intent)
        }


        callUser.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:9104686689")
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                requestForPermission()
            }else{
                startActivity(intent)
            }

        }
    }

    private fun requestForPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


}