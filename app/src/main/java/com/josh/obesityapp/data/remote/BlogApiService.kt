package com.josh.obesityapp.data.remote

import com.josh.obesityapp.data.model.Blog
import com.josh.obesityapp.data.model.SanityResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface BlogApiService {
    @Headers(
        "Content-Type: application/json",
        "Authorization: Bearer sktntqHWzf2isaoQDA6QsZhMooJR7lYwyH6JPEEsQFHmhmBbK1JjyBXnU5tbPJ5OcFcR8t14rHaHhChTqAcRSagEQajUPNKRVlEluJ9Zsw94qcZPP0kx48BcwNif82TBpt2vd8oWH89iCRO0hL4bozQsFonaZpCPpqYodOumFLHOS3l66Qxo"
    )
    @GET("v2025-03-26/data/query/production?query=*[_type == 'blogPost']{_id, title, slug, body, mainImage{ asset->{url} }, author->{_id, name, bio}, publishedAt}")
    suspend fun getBlogs(): Response<SanityResponse>

}
