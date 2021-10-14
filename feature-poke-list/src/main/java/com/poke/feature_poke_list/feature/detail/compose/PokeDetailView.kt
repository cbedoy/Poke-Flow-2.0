package com.poke.feature_poke_list.feature.detail.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.model.firstType
import com.poke.core.extensions.asPokeNumber
import com.poke.feature_poke_list.feature.list.compose.PokeTypeText
import com.poke.feature_poke_list.feature.style.*

@Composable
fun PokeDetailView(poke: Poke) {
    val pokeColor = PokeColorMap[poke.firstType]?: Normal
    ConstraintLayout(
        modifier = Modifier.background(Color(pokeColor))
    ) {
        val (number, type, name, background, container) = createRefs()

        Text(
            text = poke.name,
            fontSize = 40.sp,
            color = White,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, margin = CommonMargin)
                start.linkTo(parent.start, margin = CommonMargin)
            }
        )

        Row(
            modifier = Modifier.constrainAs(type) {
                top.linkTo(name.bottom, margin = MediumMargin)
                start.linkTo(parent.start, margin = CommonMargin)
            }
        ) {
            poke.type.split(",").forEach {
                PokeTypeText(type = it)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Text(
            text = poke.number.asPokeNumber,
            fontSize = 100.sp,
            color = PokeNumberColor,
            modifier = Modifier.constrainAs(number) {
                top.linkTo(parent.top, margin = CommonMargin)
                end.linkTo(parent.end, margin = CommonMargin)
            }
        )

        Box(modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topStart = LargeMargin,
                    topEnd = LargeMargin,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                )
            )
            .background(White)
            .fillMaxSize()
            .constrainAs(container) {
                top.linkTo(type.bottom, margin = 450.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                Box(modifier = Modifier.padding(top = 120.dp).fillMaxWidth()) {
                    PokePager(poke = poke)
                }
        }

        Box(modifier = Modifier
            .clip(shape = CircleShape)
            .background(PokeNumberColor)
            .size(256.dp)
            .constrainAs(background) {
                top.linkTo(type.bottom, margin = LargeMargin)
                centerHorizontallyTo(parent)
            }) {
            Image(
                painter = rememberImagePainter(
                    data = poke.image,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "poke image",
                modifier = Modifier.size(256.dp)
            )
        }

    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xffffff)
@Composable
fun PokeDetailViewPreview() {
    PokeDetailView(
        Poke(number = 0, name = "Charmander", type = "water,fire")
    )
}