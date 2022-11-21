package com.zeksta.zekart.activities.ordersummary

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeksta.zekart.R
import com.zeksta.zekart.databinding.LayoutOrderSummaryProductBinding
import com.zeksta.zekart.models.CartProduct
import com.zeksta.zekart.models.Product

class OrderSummaryAdapter(private val cartProductList:ArrayList<CartProduct>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val binding = LayoutOrderSummaryProductBinding.inflate(LayoutInflater.from(context), parent, false)
        return OrderSummaryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is OrderSummaryViewHolder->{
                holder.bind(cartProductList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }

    inner class OrderSummaryViewHolder(private val binding:LayoutOrderSummaryProductBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(product:CartProduct){
            binding.apply {
                tvSummaryProductName.text = product.productName
                tvSummaryBrand.text = product.brand!!.name
                tvSummaryColor.text = product.colors
                tvSummaryPrice.text = product.price
                tvSummaryQty.text = product.qty.toString()
                val totalPrice = convertPriceToFloat(product.price) * product.qty
                tvSummaryTotalPrice.text = "$$totalPrice"
            }

            Glide.with(context)
                .load(product.picture)
                .placeholder(R.drawable.placeholder_img)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.ivSummaryProduct)

        }
    }

    fun convertPriceToFloat(price:String):Float{
        var priceString = ""
        for(eachChar in price){
            if(eachChar != '$' && eachChar != ',' && eachChar != ' '){
                priceString += eachChar
            }
        }
        return priceString.toFloat()
    }
}