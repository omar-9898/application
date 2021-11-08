package com.example.activities

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickButton = findViewById(R.id.btn_click_me)
        text = findViewById(R.id.textView)
        text.setVisibility(View.INVISIBLE)
        clickButton.setOnClickListener {
            nbClick++
            val newText = "Cliquez moi $nbClick"
            text.setVisibility(View.VISIBLE)
            text.text = newText
            if (nbClick>4){
                clickButton.setClickable(false);


            }
        }

    }
    private lateinit var clickButton: Button
    private lateinit var text: TextView
    private var nbClick = 0
}