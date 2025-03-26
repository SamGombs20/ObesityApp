package com.josh.obesityapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.josh.obesityapp.data.model.BlogType
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen

@Composable
fun BlogItem(blog: BlogType, onClick: () -> Unit){
    val previewText = blog.body.firstOrNull()?.children?.firstOrNull()?.text?: ""
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4 .dp
        )
    ) {
        Row(Modifier.fillMaxWidth()) {
            AsyncImage(
                model = blog.mainImage?.asset?.url,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.weight(0.3f)
            )
            Spacer(Modifier.width(8 .dp))
            Column(Modifier.weight(0.6f)) {
                Text(
                    text = blog.title,
                    fontWeight = FontWeight(600),
                    color = customBrown
                )
                if (previewText.isNotEmpty()){
                    Text(
                        text = previewText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = customDarkGreen,
                        fontWeight = FontWeight(400),
                        fontSize = 12 .sp
                    )
                }
            }

        }
    }
}