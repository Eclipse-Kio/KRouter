package studio.kio.krouter

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import studio.kio.krouter.KRouter.KROUTER_CODE
import studio.kio.krouter.KRouter.KROUTER_KEY
import java.io.Serializable
import java.lang.ref.WeakReference

/**
 * created by KIO on 2020/10/13
 */

@Suppress("UNCHECKED_CAST")
class KRouterConnection<T : Serializable> {

    private var contextReference: WeakReference<FragmentActivity>? = null

    private var callback: KRouterCallback<T>? = null

    private val activityResultProxyFragment = ActivityResultProxyFragment(this)

    fun onReturn(callback: KRouterCallback<T>) {
        this.callback = callback
    }

    @SuppressWarnings("UNCHECKED")
    fun onActivityResult(data: Intent?) {
        contextReference?.get()
            ?.supportFragmentManager
            ?.beginTransaction()
            ?.remove(activityResultProxyFragment)
            ?.commit()
        callback?.onResult(data?.getSerializableExtra(KROUTER_KEY) as T)
    }

    fun connectTo(context: Context): CallbackJumper {

        if (this.contextReference != null) {
            throw IllegalStateException("KRouterConnection Can connect only one time")
        }

        if (context !is FragmentActivity) {
            throw IllegalArgumentException("When Output Type is not Unit,context $context must be an instance of FragmentActivity")
        }

        contextReference = WeakReference(context)

        contextReference?.get()?.supportFragmentManager?.beginTransaction()
            ?.add(0, activityResultProxyFragment)
            ?.commitNow()

        return object : CallbackJumper {
            override fun jumpTo(intent: Intent) {
                activityResultProxyFragment.startActivityForResult(intent, KROUTER_CODE)
            }
        }
    }

    interface KRouterCallback<T : Serializable> {
        fun onResult(t: T)
    }
}