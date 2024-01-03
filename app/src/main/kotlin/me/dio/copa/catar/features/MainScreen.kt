package me.dio.copa.catar.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dio.copa.catar.R
import me.dio.copa.catar.domain.extensions.getDate
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.ui.theme.Shapes

@Composable
fun MainScreen(matches: List<MatchDomain>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        //resto da tela

        //scopo da lista
        LazyColumn() {
            items(matches) { match ->
                MatchInfo(match)
            }
        }
    }
}

@Composable
fun MatchInfo(match: MatchDomain) {
    Card(
        shape = Shapes.large,
        modifier = Modifier.fillMaxWidth()
    ) {
        //usar a imagem
        Box {
            AsyncImage(
                model = match.stadium.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Notification()
                Title()
                Teams()
            }
        }
    }
}

@Composable
fun Notification(match: MatchDomain) {
    Row {
        val drawable = if (match.notificationEnabled) R.drawable.ic_notifications_active
        else R.drawable.ic_notifications

        Image(
            painter = painterResource(id = drawable),
            contentDescription = null
        )
    }
}

@Composable
fun Title(match: MatchDomain) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${match.date.getDate()} - ${match.name}",
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        ) //copy - aproveitar toda abstração e mexer somente onde quer
    }
}

@Composable
fun Teams() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

    }
}