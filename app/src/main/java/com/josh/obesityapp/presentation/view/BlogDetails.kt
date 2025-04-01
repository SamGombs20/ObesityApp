package com.josh.obesityapp.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.josh.obesityapp.data.model.BodyBlock
import com.josh.obesityapp.presentation.viewmodel.BlogViewModel

@Composable
fun BlogDetailsScreen(blogViewModel: BlogViewModel){
    val selectedBlog by blogViewModel.selectedBlog.collectAsState()
    val imageUrl = selectedBlog?.mainImage?.asset?.url ?: ""
    var isLoading by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()
    if (selectedBlog != null){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16 .dp),
        ) {
            Text(
                text = selectedBlog!!.title,
                fontSize = 22 .sp,
                fontWeight = FontWeight(600)
            )
            Spacer(modifier = Modifier.height(16 .dp))
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
                modifier = Modifier.fillMaxWidth().height(200 .dp).clip(
                    shape = RoundedCornerShape(8 .dp)
                )
            )
            Spacer(modifier = Modifier.height(16 .dp))
            BlogBody(selectedBlog!!.body)

        }
    }
}
@Composable
fun BlogBody(body: List<BodyBlock>){
    Column {
        body.forEach { block->
            block.children.forEach {
                Text(text = it.text)
                Spacer(modifier = Modifier.height(8 .dp))
            }
        }
    }
}