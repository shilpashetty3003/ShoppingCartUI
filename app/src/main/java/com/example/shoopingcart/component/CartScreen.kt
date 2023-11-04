package com.example.shoopingcart.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoopingcart.data.Cart
import com.example.shoopingcart.data.DummyData
import com.example.shoopingcart.ui.theme.DarkJungleGreen
import com.example.shoopingcart.ui.theme.dp16
import com.example.shoopingcart.ui.theme.dp30
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview
@Composable
fun CartScreen() {

    var shoppingList by remember {
        mutableStateOf(DummyData.shoppingList)
    }
    var totalPrice by remember {
        mutableStateOf(0)
    }

    var price by remember {
        mutableStateOf(0)
    }
    var couponDiscount by remember {
        mutableStateOf(0)
    }

    val gradientColor = listOf(Color(0xFF4CBB17), Color(0xFF54F00A))

    LazyColumn(modifier = Modifier.background(color = DarkJungleGreen)) {
        shoppingList.forEach {
            if (it.isCouponApplied) totalPrice = totalPrice + it.discountPrice
            else totalPrice = totalPrice + it.actualPrice

            val discountPrice = it.actualPrice - it.discountPrice
            if (it.isCouponApplied) couponDiscount = couponDiscount + discountPrice

            price += it.actualPrice
        }

        item {
            ShoppingName()
        }
        items(items = shoppingList) { cart ->
            ShoppingCart(cart) { isCouponAvailable ->
                val shoppingData: List<Cart> = shoppingList.map {
                    if (it.cardID == cart.cardID) it.copy(isCouponApplied = isCouponAvailable) else it
                }
                totalPrice = 0
                couponDiscount = 0
                price = 0
                shoppingList = shoppingData
                Log.d("TAG", "CartScreen:  $shoppingList")
            }
        }
        item {
            CardDetails(price = price.toString(), couponDiscount.toString(), totalPrice.toString())
            GradientButton(
                gradientColors = gradientColor,
                cornerRadius = dp16,
                nameButton = "Pay",
                roundedCornerShape = RoundedCornerShape(dp30)
            )
        }
    }
}