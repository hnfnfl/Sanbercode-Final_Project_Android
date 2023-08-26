package com.hnfnfl.finalproject.retrofit


import com.google.gson.annotations.SerializedName

data class AnimeResponse(@SerializedName("pagination") val pagination: Pagination, @SerializedName("data") val data: List<Data>) {
    data class Pagination(
        @SerializedName("last_visible_page") val lastVisiblePage: Int,
        @SerializedName("has_next_page") val hasNextPage: Boolean,
        @SerializedName("current_page") val currentPage: Int,
        @SerializedName("items") val items: Items
    ) {
        data class Items(
            @SerializedName("count") val count: Int, @SerializedName("total") val total: Int, @SerializedName("per_page") val perPage: Int
        )
    }

    data class Data(
        @SerializedName("mal_id") val malId: Int,
        @SerializedName("url") val url: String,
        @SerializedName("images") val images: Images,
        @SerializedName("title") val title: String,
        @SerializedName("title_english") val titleEnglish: String?,
        @SerializedName("title_japanese") val titleJapanese: String,
        @SerializedName("episodes") val episodes: Int?,
        @SerializedName("status") val status: String,
        @SerializedName("duration") val duration: String,
        @SerializedName("rating") val rating: String?,
        @SerializedName("score") val score: Double?,
        @SerializedName("synopsis") val synopsis: String,
        @SerializedName("producers") val producers: List<Producer>,
        @SerializedName("genres") val genres: List<Genre>,
    ) {
        data class Images(
            @SerializedName("jpg") val jpg: Jpg
        ) {
            data class Jpg(
                @SerializedName("image_url") val imageUrl: String, @SerializedName("small_image_url") val smallImageUrl: String, @SerializedName("large_image_url") val largeImageUrl: String
            )
        }

        data class Producer(
            @SerializedName("mal_id") val malId: Int, @SerializedName("type") val type: String, @SerializedName("name") val name: String, @SerializedName("url") val url: String
        )


        data class Genre(
            @SerializedName("mal_id") val malId: Int, @SerializedName("type") val type: String, @SerializedName("name") val name: String, @SerializedName("url") val url: String
        )

    }
}