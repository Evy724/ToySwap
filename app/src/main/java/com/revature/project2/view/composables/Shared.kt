package com.revature.project2.view.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import com.revature.project2.model.api.alltoys.ToyItem


@Composable
fun ToyCard(toy: ToyItem){

    Row(modifier = Modifier.fillMaxWidth(.95f)) {

        Image(painter = rememberCoilPainter(
            request = toy.sImagePath)
            , contentDescription = null,
            modifier = Modifier.size(70.dp))

        Spacer(Modifier.size(5.dp))

        Column {

            Text(toy.sName,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center)

            Spacer(Modifier.size(2.dp))

            Text(text = toy.sDescription,
                style = MaterialTheme.typography.body1)
        }
    }
}