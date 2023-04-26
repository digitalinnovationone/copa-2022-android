package me.dio.copa.catar.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.ui.theme.Shapes

@Composable
fun MainScreen(matches: List<MatchDomain>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)){
        LazyColumn(){
            items(matches){match ->
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
        Box(){
            AsyncImage(
                model = match.stadium.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
                )
            Column(modifier = Modifier.padding(16.dp)) {
                Notification(match)
                Title()
                Teams()
            }
        }
    }
}


@Composable
fun Notification(match: MatchDomain) {
    Row{
        val drawable = if (match.notificationEnabled) me.dio.copa.catar.R.drawable.ic_notifications_active
        else me.dio.copa.catar.R.drawable.ic_notifications
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null)
    }
}

@Composable
fun Title() {

}

@Composable
fun Teams() {

}