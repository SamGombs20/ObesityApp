package com.josh.obesityapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.josh.obesityapp.data.model.BlogType
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.utils.transformSanityImageUrl

@Composable
fun BlogItem(blog: BlogType, onClick: () -> Unit){
    val previewText = blog.body.firstOrNull()?.children?.firstOrNull()?.text?: ""
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16 .dp, vertical = 8 .dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 4 .dp
        )
    ) {
        Row(Modifier.fillMaxWidth()) {
//            Image(
//                painter = rememberAsyncImagePainter(blog.mainImage?.asset?.url),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.weight(0.4f)
//            )
            blog.mainImage?.asset?.url?.let {
                SanityImage(imageUrl = it)
            }
            Spacer(Modifier.width(8 .dp))
            Column {
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
@Composable
fun SanityImage(
    imageUrl:String,
    modifier: Modifier= Modifier
){
    AsyncImage(
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .width(150 .dp)
            .height(120 .dp)
            .clip(RoundedCornerShape(8 .dp)),
        onError = {exception->
            Log.e("SanityImage", "Error loading image", exception.result.throwable)
        }
    )
}
