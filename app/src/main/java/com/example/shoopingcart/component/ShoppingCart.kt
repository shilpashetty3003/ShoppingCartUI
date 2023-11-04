package com.example.shoopingcart.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoopingcart.R
import com.example.shoopingcart.data.Cart
import com.example.shoopingcart.ui.theme.White
import com.example.shoopingcart.ui.theme.dp10
import com.example.shoopingcart.ui.theme.dp150
import com.example.shoopingcart.ui.theme.dp16
import com.example.shoopingcart.ui.theme.dp20
import com.example.shoopingcart.ui.theme.dp5
import com.example.shoopingcart.ui.theme.dp7
import com.example.shoopingcart.ui.theme.dp8
import com.example.shoopingcart.ui.theme.dp80

@Composable
fun ShoppingCart(cart: Cart, onClickOnApply: (value: Boolean) -> Unit) {

    var isAppliedCode by remember {
        mutableStateOf(false)
    }

    var couponTextVisible by remember {
        mutableStateOf(false)
    }

    var coupon by remember {
        mutableStateOf("")
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.Grey)
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(dp16)
    ) {
        Header(
            cart,
            Modifier
                .padding(dp8)
                .fillMaxWidth(), isAppliedCode, couponTextVisible
        )
        SellerName(
            cart = cart,
            Modifier
                .padding(dp8)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(dp5))
        Divider(color = colorResource(id = R.color.white).copy(0.2f), thickness = 2.dp)
        Spacer(modifier = Modifier.height(dp5))
        ApplyCoupon(isAppliedCode, couponTextVisible, coupon, onCouponTextClick = {
            couponTextVisible = !couponTextVisible

        }, onCouponTextChanged = {
            coupon = it
        }, onClickApply = {
            isAppliedCode = !isAppliedCode
            if (!isAppliedCode) {
                couponTextVisible = false
                coupon = ""
            }
            onClickOnApply(isAppliedCode)
        }, Modifier
            .padding(dp8)
            .fillMaxWidth()
        )
    }
}

@Composable
fun Header(cart: Cart, modifier: Modifier, isAppliedCode: Boolean, couponTextVisible: Boolean) {

    val typography = MaterialTheme.typography
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.darkBlack)
        ), modifier = modifier

    ) {
        Row(
            modifier = Modifier
                .padding(top = dp16, start = dp16, bottom = dp16)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = cart.photo), contentDescription = "",
                modifier = Modifier
                    .size(width = dp80, height = 120.dp)
                    .clip(RoundedCornerShape(dp10)), contentScale = ContentScale.Crop
            )
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(start = dp20)
                    .height(dp150)
            ) {

                Text(text = cart.name, style = typography.bodyMedium,color = White)
                if (isAppliedCode) {
                    Row {
                        Text(text = "\u20B9 ${cart.discountPrice}", style = typography.bodyMedium, color = White)
                        Text(
                            text = " \u20B9 ${cart.actualPrice}",
                            style = typography.bodyMedium,
                            color = Color.White.copy(0.5f),
                            textDecoration = TextDecoration.LineThrough,
                            modifier = Modifier.padding(horizontal = dp5)
                        )
                        Text(
                            text = "(50% off)", style = typography.bodyMedium,
                            color = colorResource(id = R.color.Green),
                        )
                    }
                } else {
                    Text(
                        text = "\u20B9 ${cart.actualPrice}",
                        style = typography.bodyMedium,
                        fontWeight = FontWeight.Bold,color = White
                    )
                }

                Row {
                    Text(
                        text = "Size: ",
                        style = typography.labelMedium,
                        color = Color.White.copy(0.5f)
                    )
                    Text(text = cart.size, style = typography.bodySmall,color = White)
                }
                Row {
                    Text(
                        text = "Qty: ",
                        style = typography.labelMedium,
                        color = Color.White.copy(0.5f)
                    )
                    Text(text = cart.qty.toString(), style = typography.bodySmall,color = White)
                }

            }

            //Icon(imageVector = Icons.Outlined.Delete, contentDescription = "")
        }

    }
}

@Composable
fun SellerName(cart: Cart, modifier: Modifier) {
    val typography = MaterialTheme.typography
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        Row(modifier = Modifier.padding(horizontal = dp5)) {
            Text(text = cart.sellerName, style = typography.labelMedium,color = White)
            Image(
                painter = painterResource(id = R.drawable.baseline_verified_24),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = dp5)
            )
            Text(
                text = "Buy ok verified",
                style = typography.bodySmall,
                color = Color.White.copy(0.5f)
            )
        }
        Text(text = "ETA 5-7 working days", style = typography.bodySmall,color = White)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplyCoupon(
    isAppliedCode: Boolean,
    couponTextVisible: Boolean,
    coupon: String,
    onCouponTextClick: () -> Unit,
    onCouponTextChanged: (text: String) -> Unit,
    onClickApply: () -> Unit, modifier: Modifier,
) {
    val focusRequester = FocusRequester()

    if (couponTextVisible) {
        OutlinedTextField(
            value = coupon,
            trailingIcon = {
                if (coupon.length > 0) {
                    ClickableText(
                        text = if (isAppliedCode) AnnotatedString("Remove") else AnnotatedString("Apply"),
                        onClick = {
                            onClickApply()
                        }, modifier = Modifier.padding(horizontal = dp10),

                        style = TextStyle(
                            color = colorResource(id = R.color.Green),
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }

            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                ,
            label = {
                Text(
                    text = "Promo Code",
                    color = colorResource(id = R.color.white).copy(0.5f)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.Green),
                focusedLabelColor = colorResource(id = R.color.Green),
                cursorColor = colorResource(id = R.color.white), textColor = White

            ),
            onValueChange = {
                onCouponTextChanged(it)
            }
        )

    } else {
        Card(
            modifier = modifier, colors = CardDefaults.cardColors(
                containerColor = colorResource(
                    id = R.color.Grey
                )
            ), onClick = {
                onCouponTextClick()
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_discount_24),
                    contentDescription = "", modifier = Modifier.padding(horizontal = dp8), tint  = White
                )
                Text(text = "Apply coupon code", style = MaterialTheme.typography.labelMedium,color = White)
            }

        }
    }

}

@Preview
@Composable
fun DefaultShoppingCart() {

    ShoppingCart(
        cart = Cart(
            3,
            "Check Cotton Shirt",
            R.drawable.shirt,
            "Medium",
            2,
            "Nike",
            2000,
            1000, false

        ),
    ) {

    }
}