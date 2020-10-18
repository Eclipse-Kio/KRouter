package studio.kio.krouter

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.io.Serializable

/**
 * created by KIO on 2020/10/13
 */
object KRouter {

    const val KROUTER_KEY = "_KROUTER_DEFAULT_KEY"
    const val KROUTER_CODE = 3344

    inline fun <reified I : Serializable, reified O : Serializable> navigateTo(
        context: Context,
        route: Route<I, O>,
        parameter: I
    ): KRouterConnection<O> {

        val connection = KRouterConnection<O>()

        val intent = Intent(context, RouterMapping.getActivityClassWithRoute(route)?.java)
        intent.putExtra(KROUTER_KEY, parameter)

        //TODO 怎么确定是不需要返回的？
        if (O::class == Unit::class) {
            context.startActivity(intent)
        } else {
            val jumperFragment = connection.connectTo(context)
            jumperFragment.jumpTo(intent)
        }

        return connection
    }


    inline fun <reified T : Serializable> getParameter(
        intent: Intent,
        route: Route<T, out Serializable>
    ): T {
        return T::class.java.cast(intent.getSerializableExtra(KROUTER_KEY))!!
    }

    inline fun <reified T : Serializable> setReturn(
        activity: Activity,
        listRoute: Route<out Serializable, T>,
        data: T
    ) {
        val i = Intent()
        i.putExtra(KROUTER_KEY, data)
        activity.setResult(KROUTER_CODE, i)
    }

}