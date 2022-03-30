package com.learningplatforms.instaactivity.modelclass

data class Node(
    val followed_by_viewer: Boolean,
    val full_name: String,
    val id: String,
    val is_private: Boolean,
    val is_verified: Boolean,
    val location: Any,
    val requested_by_viewer: Boolean,
    val tracking_token: String,
    val username: String,

)