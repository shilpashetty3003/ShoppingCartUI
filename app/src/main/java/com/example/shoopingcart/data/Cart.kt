package com.example.shoopingcart.data

data class Cart (
    val cardID:Int,
    val name:String,
    val photo:Int,
    val size:String,
    val qty:Int,
    val sellerName:String,
    val actualPrice:Int,
    val discountPrice:Int,
    val isCouponApplied:Boolean
)