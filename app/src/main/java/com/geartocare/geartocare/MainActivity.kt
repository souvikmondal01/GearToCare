package com.geartocare.geartocare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geartocare.geartocare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.vCheckList.setOnClickListener {
            val intent = Intent(this, CheckListActivity::class.java)
            startActivity(intent)
        }
        binding.vBookingDetails.setOnClickListener {
            val intent = Intent(this, BookingDetailsActivity::class.java)
            startActivity(intent)
        }


    }
}