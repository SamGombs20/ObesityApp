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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.josh.obesityapp.data.model.BlogType
import com.josh.obesityapp.ui.theme.customBrown
import com.josh.obesityapp.ui.theme.customDarkGreen
import com.josh.obesityapp.utils.transformSanityImageUrl

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
//            Image(
//                painter = rememberAsyncImagePainter(blog.mainImage?.asset?.url),
//                contentDescription = null,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.weight(0.4f)
//            )
            blog.mainImage?.asset?.url?.let {
                SanityImage(
                    imageUrl = it,
                    modifier = Modifier.weight(0.4f)
                )
             }?: Box(
                modifier = Modifier
                    .weight(0.4f)
                    .background(Color.Green) // Optional: placeholder background
            )
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
fun BlogImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Blog Image",
        modifier = Modifier
            .width(150.dp)
            .height(100.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop,
        onError = { exception ->
            Log.e("BlogItem", "Image load error: ${exception.result}")
        }
    )
}
@Composable
fun SanityImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
){
    val transformedUrl = transformSanityImageUrl(imageUrl)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(transformedUrl)
            .transformations(
                RoundedCornersTransformation(8f), // Optional: adds rounded corners
                // You can add more transformations if needed
            )
            .build(),
        contentDescription = contentDescription,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray), // Placeholder background
        contentScale = ContentScale.Crop,
        onError = { exception ->
            Log.e("SanityImage", "Image load error: ${exception.result}")
        }
    )
}
@Preview(showBackground = true)
@Composable
fun BlogImagePreview() {
    BlogImage("https://cdn.sanity.io/images/40v865zp/production/430762e46dab4fbd3dcd351543248a38ab1acfa0-636x568.png")
}

