package com.learningplatforms.instaactivity

import com.github.instagram4j.instagram4j.IGClient
import com.github.instagram4j.instagram4j.IGClient.Builder.LoginHandler
import com.github.instagram4j.instagram4j.responses.accounts.LoginResponse
import com.github.instagram4j.instagram4j.utils.IGChallengeUtils
import java.io.File
import java.util.concurrent.Callable


class Loginclint {

    // Callable that returns inputted code from System.in
    var inputCode: Callable<String> = Callable<String> {
        print("Please input code: ")
        "nothing happen"
    }



    var twoFactorHandler =
        LoginHandler { client: IGClient?, response: LoginResponse? ->
            IGChallengeUtils.resolveTwoFactor(
                client!!, response!!, inputCode
            )
        }



    fun login(Username: String, Password: String): IGClient {
        return IGClient.builder()
            .username(Username)
            .password(Password) // h  .onChallenge(twoFactorHandler)
            .login()
    }

}

private fun createSessionFiles(
    client: IGClient,
    username: String
) {
    File("sessions/$username").mkdirs()
    val clientFile = File("sessions/$username/client.ser")
    val cookieFile = File("sessions/$username/cookie.ser")
    client.serialize(clientFile, cookieFile)
}

private fun deleteSessionFiles(
    client: IGClient,
    username: String
) {
    File("sessions/$username").mkdirs()
    val clientFile = File("sessions/$username/client.ser")
    val cookieFile = File("sessions/$username/cookie.ser")
    IGClient.deserialize(clientFile, cookieFile)

}