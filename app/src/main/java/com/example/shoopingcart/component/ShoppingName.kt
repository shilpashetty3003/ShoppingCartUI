package com.example.shoopingcart.component


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoopingcart.R
import com.example.shoopingcart.ui.theme.dp10
import com.example.shoopingcart.ui.theme.sp16

@Preview
@Composable
fun ShoppingName() {

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth().padding(vertical = dp10)) {
        Text(text = stringResource(id = R.string.shopping_text), textAlign = TextAlign.Center, fontSize = sp16, color = Color.White )
    }
}