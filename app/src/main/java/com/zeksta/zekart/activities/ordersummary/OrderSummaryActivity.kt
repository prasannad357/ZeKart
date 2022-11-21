package com.zeksta.zekart.activities.ordersummary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeksta.zekart.R
import com.zeksta.zekart.databinding.ActivityOrderSummaryBinding
import com.zeksta.zekart.models.CartProduct
import java.io.Serializable

class OrderSummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOrderSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Order Summary"
        binding = ActivityOrderSummaryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("bundle")
        val cartList = bundle!!.getSerializable("cartList") as ArrayList<CartProduct>
        binding.rvOrderSummary.layoutManager = LinearLayoutManager(this)
        binding.rvOrderSummary.adapter = OrderSummaryAdapter(cartList)
    }
}