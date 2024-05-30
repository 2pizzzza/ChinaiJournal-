package com.inai.journal.platform.constant

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {

    val AUTH_KEY = stringSetPreferencesKey("auth_key")


    object Network {
        const val GRPC_ADDRESS = "10.0.3.2"
        const val GRPC_PORT = 8080
    }


}