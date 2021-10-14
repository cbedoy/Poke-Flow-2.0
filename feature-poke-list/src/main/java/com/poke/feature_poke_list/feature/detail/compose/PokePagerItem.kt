package com.poke.feature_poke_list.feature.detail.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.poke.feature_poke_list.feature.style.CommonPadding
import com.poke.feature_poke_list.feature.style.PagerTextSize
import com.poke.feature_poke_list.feature.style.SmallMargin
import com.poke.feature_poke_list.feature.style.SmallPadding

@Composable
fun PokeAbilityView(ability: String) {
    Text(text = ability, fontSize = PagerTextSize, modifier = Modifier.padding(SmallPadding).fillMaxWidth())
}

@Composable
fun PokeMoveView(move: String) {
    Text(text = move, fontSize = PagerTextSize, modifier = Modifier.padding(SmallPadding).fillMaxWidth())
}

@Composable
fun PokeStatView(statName: String, value: Int) {
    ConstraintLayout {
        val (name, value, progress) = createRefs()

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PokePagerItemPreview() {
    Column {
        PokeAbilityView("Ability to eat tacos")
        PokeMoveView("drink")
        PokeStatView("coder", 1)
    }
}