package com.josh.obesityapp.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.josh.obesityapp.data.model.BlogType
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.ui.theme.customLightBrown
import com.josh.obesityapp.utils.formatDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BlogItem(blog: BlogType, onClick: () -> Unit){
    val previewText = blog.body.firstOrNull()?.children?.firstOrNull()?.text?: ""
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8 .dp, vertical = 4 .dp).height(120 .dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4 .dp
        )
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            blog.mainImage?.asset?.url?.let {
                SanityImage(imageUrl = it)
            }
            Spacer(Modifier.width(8 .dp))
            Column(Modifier.padding(
                horizontal = 4 .dp, vertical = 2 .dp
            ).fillMaxWidth().fillMaxHeight()) {
                Text(
                    text = blog.title,
                    fontWeight = FontWeight(500),
                    color = customBrown,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14 .sp
                )
                Spacer(Modifier.height(4 .dp))
                if (previewText.isNotEmpty()){
                    Text(
                        text = previewText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = customDarkGreen,
                        fontSize = 12 .sp
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(
                        horizontal = 4 .dp, vertical = 2 .dp
                    ),
                    contentAlignment = Alignment.BottomEnd
                ){
                    Text(
                        text = formatDate(blog.publishedAt),
                        color = customBrown,
                        fontSize = 12 .sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

        }
    }
}
@Composable
fun SanityImage(imageUrl: String) {
    var isLoading by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(120.dp)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = customDarkGreen
            )
        }

        // Load Image
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .listener(
                    onStart = { isLoading = true },
                    onSuccess = { _, _ -> isLoading = false },
                    onError = { _, _ -> isLoading = false }
                )
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}

