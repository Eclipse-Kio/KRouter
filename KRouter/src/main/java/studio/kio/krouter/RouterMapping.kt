package studio.kio.krouter

import android.app.Activity
import java.io.Serializable
import kotlin.reflect.KClass

/**
 * 假装这是注解处理器生成的类
 * created by KIO on 2020/10/13
 */
object RouterMapping {

    private lateinit var activity: KClass<out Activity>

    public var routeActivityMap =
        HashMap<Route<out Serializable, out Serializable>, KClass<out Activity>>()
        private set
    //暂时暴露一下这个方法


    fun <I : Serializable, O : Serializable> getActivityClassWithRoute(route: Route<I, O>): KClass<out Activity>? {
        return routeActivityMap[route]
    }


}