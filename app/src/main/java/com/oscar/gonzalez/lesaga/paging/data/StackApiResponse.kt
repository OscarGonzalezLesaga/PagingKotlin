package com.oscar.gonzalez.lesaga.paging.data

data class Owner(
    val reputation: Int,
    val user_id: Long,
    val user_type: String,
    val accept_rate: Int,
    val profile_image: String,
    val display_name: String,
    val link: String
)

data class Item(
    val owner: Owner,
    val is_accepted: Boolean,
    val score: Int,
    val last_activity_date: Long,
    val last_edit_data: Long,
    val creation_date: Long,
    val answer_id: Long,
    val question_id: Long
)

data class StackApiResponse(
    val items: List<Item>,
    val has_more: Boolean,
    val backoff: Int,
    val quota_max: Int,
    val quota_remaining: Int
)