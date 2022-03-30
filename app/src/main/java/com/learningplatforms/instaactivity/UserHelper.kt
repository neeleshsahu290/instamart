package com.learningplatforms.instaactivity

import com.github.instagram4j.instagram4j.IGClient
import com.github.instagram4j.instagram4j.models.user.User
import com.github.instagram4j.instagram4j.requests.friendships.FriendshipsActionRequest
import com.github.instagram4j.instagram4j.requests.media.MediaInfoRequest
import com.github.instagram4j.instagram4j.responses.friendships.FriendshipStatusResponse
import com.github.instagram4j.instagram4j.responses.media.MediaInfoResponse

import java.util.concurrent.CompletableFuture

class UserHelper(private val igClient: IGClient) {


    /**
     * Get user instagram id (pk) based on [username]
     */
    fun getPk(username: String): Dyad<Long?> {
        val (action, error) = igClient.actions.users().findByUsername(username).actionPair()
        return if (action != null && error == null) action.user.pk to null else null to error

    }


    /**
     * Get [User] info based on instagram [userId] (pk)
     */
    private fun getUserInfoByPk(userId: Long): Dyad<User?> {
        return igClient.actions.users().info(userId).actionPair()
    }

    /**
     * Get [User] info based on [username]
     */
    fun getUserInfoByUsername(username: String): Dyad<User?> {
        val (action, error) = igClient.actions.users().findByUsername(username).actionPair()
        return if (action != null && error == null) action.user to null else null to error
    }
    /**
     * Get current logged in [User] info
     */
    fun getCurrentUserInfo(): Dyad<User?> {
        val (action, error) = igClient.actions.account().currentUser().actionPair()
        return if (action != null && error == null) action.user to null else null to error
    }

    fun getCurrentUserPk() : Dyad<Long?> {
        val (action, error) = igClient.actions.account().currentUser().actionPair()
        return if (action != null && error == null) action.user.pk to null else null to error
    }

    fun actionbyuserid(action: FriendshipsActionRequest.FriendshipsAction?, userId: Long): CompletableFuture<FriendshipStatusResponse> {
        return FriendshipsActionRequest(userId, action!!).execute(igClient)
    }

    fun actionbyusername(action: FriendshipsActionRequest.FriendshipsAction?,username: String): CompletableFuture<FriendshipStatusResponse> {

        val(userid,error)=getPk(username)
        return FriendshipsActionRequest(userid!!, action!!).execute(igClient)
    }
    fun info(): CompletableFuture<MediaInfoResponse> {
        return MediaInfoRequest(2796676824698154973.toString()).execute(igClient)
    }
}

private typealias Dyad<T> = Pair<T, Throwable?>

fun <U> CompletableFuture<U>.actionPair(): Dyad<U?> {
    return try {
        val response = this.get()
        response to null
    } catch (e: Throwable) {
        null to e
    }
}