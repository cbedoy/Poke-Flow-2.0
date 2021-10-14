package com.poke.feature_poke_list.feature.list.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.model.firstType
import com.poke.feature_poke_list.feature.style.*

@Composable
fun PokeItem(poke: Poke){
    val pokeColor = PokeColorMap[poke.firstType]?: Normal
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CardRadius)
    ){
        ConstraintLayout(
            modifier = Modifier
                .background(Color(pokeColor))
                .padding(MediumPadding)
        ) {
            val (name, types, image) = createRefs()
            Text(text = poke.name, color = White, modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, margin = 0.dp)
            })
            Column(
                modifier = Modifier.constrainAs(types) {
                    top.linkTo(name.bottom, margin = 4.dp)
                }
            ) {
                poke.type.split(",").forEach { type ->
                    Spacer(modifier = Modifier.height(4.dp))
                    PokeTypeText(type = type)
                }
            }

            Image(
                painter = rememberImagePainter(
                    data = poke.image,
                    builder = {
                        transformations(CircleCropTransformation())
                    }
                ),
                contentDescription = "poke image",
                modifier = Modifier
                    .size(PokeCardSize)
                    .constrainAs(image) {
                        top.linkTo(name.bottom, margin = 8.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                        end.linkTo(parent.end, margin = 8.dp)
                    }
            )

        }

    }
}

@Preview(showBackground = true, backgroundColor = 0xFF333333)
@Composable
fun PokeItemPreview(){
    PokeItem(Poke(
        number = 1,
        name = "Poke name",
        type = "Fire,Water"
    ))
}
