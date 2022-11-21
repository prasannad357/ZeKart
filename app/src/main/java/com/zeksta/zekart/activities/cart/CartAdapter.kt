package com.zeksta.zekart.activities.cart

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zeksta.zekart.R
import com.zeksta.zekart.databinding.LayoutCartProductBinding
import com.zeksta.zekart.models.Brand
import com.zeksta.zekart.models.Product
import com.zeksta.zekart.models.CartProduct
import com.zeksta.zekart.utils.ZekartUtils.afterTextChanged

/**
 * Note:
 * I have added "Select" to brand list dropdown because I want to make user select a brand
 * Above condition is must as I have to check user has entered quantity, selected a brand and color to proceed to order summary
 */

class CartAdapter(private val products:List<Product>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private val cartProductList:ArrayList<CartProduct> = ArrayList()
    private val selectedProductIdList:ArrayList<String> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val productBinding = LayoutCartProductBinding.inflate(LayoutInflater.from(context),parent, false)

        return ProductViewHolder(productBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(holder){
           is ProductViewHolder -> {
               holder.bind(position)
           }
       }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun onSubmitClick(): ArrayList<CartProduct> {
        return validateCart(cartProductList)
    }

    private inner class ProductViewHolder(val binding:LayoutCartProductBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            val product = products[position]
            binding.tvCartProductName.text = product.productName
            binding.tvCartPrice.text = product.price
            val brandNameList:ArrayList<String> = arrayListOf("Select")
            for(brand in product.brands){
                brandNameList.add(brand.name)
            }
            binding.spinCartBrand.adapter = ArrayAdapter(context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, brandNameList)
            when{
                !product.colors.contains("Red") -> binding.rbCartRed.visibility = RadioButton.GONE
                !product.colors.contains("Blue") -> binding.rbCartBlue.visibility = RadioButton.GONE
                !product.colors.contains("Green") -> binding.rbCartGreen.visibility = RadioButton.GONE
            }

            binding.rgCartColor.setOnCheckedChangeListener(object:RadioGroup.OnCheckedChangeListener{
                override fun onCheckedChanged(radiogroup: RadioGroup?, id: Int) {
                    val selectedColor:String = when(id){
                        R.id.rbCartBlue -> "Blue"
                        R.id.rbCartGreen -> "Green"
                        R.id.rbCartRed -> "Red"
                        else -> {""}
                    }
                    when{
                        !selectedProductIdList.contains(product._id) -> {
                            val selectedProduct = CartProduct(product._id, productName = product.productName,
                                price = product.price, picture = product.picture, colors = selectedColor)
                            selectedProductIdList.add(product._id)
                            cartProductList.add(selectedProduct)
                        }
                        else -> {
                            cartProductList[selectedProductIdList.indexOf(product._id)].colors = selectedColor
                        }
                    }
                }

            })

            binding.spinCartBrand.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, optionPosition: Int, id: Long) {
                    val brandList = product.brands.filter { it.name == brandNameList[optionPosition] }
                    var selectedBrand: Brand? = null
                    if(brandList.isNotEmpty()){
                        selectedBrand = brandList[0]
                    }
                    if(optionPosition != 0 && !selectedProductIdList.contains(product._id)){
                        val selectedProduct = CartProduct(product._id, brand = selectedBrand, productName = product.productName,
                            price = product.price, picture = product.picture)
                        selectedProductIdList.add(selectedProduct._id)
                        cartProductList.add(selectedProduct)
                    }else if(optionPosition != 0 && selectedProductIdList.contains(product._id)){
                        val index = selectedProductIdList.indexOf(product._id)
                        cartProductList[index].brand = selectedBrand
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }

            }

            binding.etCartQty.afterTextChanged{
                if(it.isNotEmpty() && it.toInt() > 0){
                    if(selectedProductIdList.contains(product._id)){
                        cartProductList[selectedProductIdList.indexOf(product._id)].qty = binding.etCartQty.text.toString().toInt()
                    }else{
                        val selectedProduct = CartProduct(product._id, productName = product.productName,
                            price = product.price, picture = product.picture, qty =binding.etCartQty.text.toString().toInt() )
                        cartProductList.add(selectedProduct)
                    }
                }
            }

            Glide.with(context)
                .load(product.picture)
                .placeholder(R.drawable.placeholder_img)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.ivCartProduct)

        }
    }

    private fun validateCart(cartProductList:ArrayList<CartProduct>):ArrayList<CartProduct>{
        val validatedCartList = arrayListOf<CartProduct>()
        cartProductList.forEach { cart ->
            if(cart.colors.isNotEmpty() && cart.brand != null && cart.qty > 0){
                validatedCartList.add(cart)
            }
        }
        return validatedCartList
    }

}