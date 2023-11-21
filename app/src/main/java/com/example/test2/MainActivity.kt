package com.example.test2

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val logo: ImageView = findViewById(R.id.logo_image)
        logo.setImageResource(R.drawable.kafka)

        findViewById<Button>(R.id.login_button).setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Thông báo")
            builder.setMessage("Nội dung thông báo")

            // Nút "Đóng" để đóng hộp thoại
            builder.setPositiveButton("Đóng") { dialog, _ ->
                dialog.dismiss()
            }

            // Tạo và hiển thị hộp thoại
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()

        }
    }
}