package com.poke.feature_poke_list.feature.list.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.poke.feature_poke_list.feature.style.*
import java.util.*

@Composable
fun PokeTypeText(type: String, multiplier: Float = 1f){
    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = type.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            fontSize = TypeTextSize * multiplier,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .width(TypeWidth * multiplier)
                .border(BorderStroke(
                    BorderStrokeSize,
                    White
                ), shape = RoundedCornerShape(TypeRadius))
                .background(Color(WhiteTransparent))
                .padding(SmallPadding)
        )
    }
}

@Preview()
@Composable
fun PokeTypeTextPreview(){
    PokeTypeText("Tacos")
}