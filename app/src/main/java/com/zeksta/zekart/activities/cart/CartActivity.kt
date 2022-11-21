package com.zeksta.zekart.activities.cart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zeksta.zekart.activities.ordersummary.OrderSummaryActivity
import com.zeksta.zekart.activities.zekart.ZekartApplication
import com.zeksta.zekart.databinding.ActivityCartBinding
import com.zeksta.zekart.di.ApplicationComponent
import com.zeksta.zekart.di.CartComponent
import com.zeksta.zekart.models.CartProduct
import com.zeksta.zekart.models.Product
import com.zeksta.zekart.viewmodels.CartViewModel
import com.zeksta.zekart.viewmodels.ViewModelFactory
import java.io.Serializable
import javax.inject.Inject

class CartActivity : AppCompatActivity() {

    private lateinit var appComponent:ApplicationComponent
    private lateinit var cartComponent: CartComponent
    private lateinit var cartViewModel: CartViewModel
    private var products:List<Product> = listOf()
    private lateinit var binding:ActivityCartBinding
    private lateinit var adapter: CartAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        title = "Product Lists"
        appComponent = (application as ZekartApplication).appComponent
        cartComponent = appComponent.getCartComponentFactory().create()
        cartComponent.inject(this)
        cartViewModel = ViewModelProvider(this, viewModelFactory)[CartViewModel::class.java]
        binding.rvCart.layoutManager = LinearLayoutManager(this)


        cartViewModel.products.observe(this){
            products = it
            adapter = CartAdapter(it)
            binding.rvCart.adapter = adapter
        }


        binding.buCartSubmit.setOnClickListener {
            val cartList = adapter.onSubmitClick()
            val intent = Intent(this@CartActivity,OrderSummaryActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("cartList", cartList)
            intent.putExtra("bundle", bundle)
            startActivity(intent)
        }

    }

}