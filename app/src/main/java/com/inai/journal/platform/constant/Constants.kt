package com.inai.journal.platform.constant

import androidx.datastore.preferences.core.stringSetPreferencesKey

object Constants {

    val AUTH_KEY = stringSetPreferencesKey("auth_key")


    object Network {
        const val GRPC_ADDRESS = "192.168.1.105"
        const val GRPC_PORT = 9999
    }


}