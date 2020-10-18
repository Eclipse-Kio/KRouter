package studio.kio.krouter

import android.content.Intent

/**
 * created by KIO on 2020/10/18
 */

interface CallbackJumper {
    fun jumpTo(intent: Intent)
}