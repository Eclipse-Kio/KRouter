package studio.kio.krouter

import java.io.Serializable
import kotlin.reflect.KClass

/**
 * created by KIO on 2020/10/13
 */
abstract class Route<I : Serializable, O : Serializable>

annotation class RoutePath(val value: KClass<out Route<*, *>>)