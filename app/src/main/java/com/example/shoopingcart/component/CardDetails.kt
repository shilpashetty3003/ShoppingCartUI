package com.example.shoopingcart.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoopingcart.R
import com.example.shoopingcart.ui.theme.White
import com.example.shoopingcart.ui.theme.dp10
import com.example.shoopingcart.ui.theme.dp16
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class)
fun CardDetails(price:String,CouponPrice:String,totalPrice:String) {

    val typography = androidx.compose.material3.MaterialTheme.typography.bodyMedium
    val color= colorResource(id = R.color.white).copy(0.5f)

    val modifier = Modifier
        .fillMaxWidth()
        .padding(all = dp10)
    val textStyle=TextStyle(

    )
    Card(modifier = Modifier.padding(dp16), colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.Grey))) {

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
            Text(text = "Price", style = typography, color = color)
            Text(text = "₹ $price",style = typography, color = White)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = modifier) {
            Text(text = "Delivery Charge",style = typography, color = color)
            Text(text = "Free",style = typography, color = White)
        }

        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = modifier) {
            Text(text = "Coupon Discount",style = typography, color = color)
            Text(text = "-  ₹ $CouponPrice",style = typography, color = White)
        }

        Divider(color = colorResource(id = R.color.white).copy(0.2f), thickness = 2.dp)

        Row(modifier = modifier,horizontalArrangement = Arrangement.SpaceBetween,) {
            Text(text = "Total",style = typography, color = color, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = "₹ $totalPrice",style = typography, color = White)
        }
    }
}
