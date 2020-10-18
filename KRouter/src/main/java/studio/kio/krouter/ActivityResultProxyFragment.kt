package studio.kio.krouter

import android.content.Intent
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * created by KIO on 2020/10/18
 */
class ActivityResultProxyFragment(private val connection: KRouterConnection<out Serializable>) :
    Fragment() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == KRouter.KROUTER_CODE && resultCode == KRouter.KROUTER_CODE)
            connection.onActivityResult(data)
    }
}