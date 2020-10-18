package studio.kio.demo.c

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import studio.kio.demo.R
import studio.kio.demo.common.DemoRoute
import studio.kio.krouter.KRouter
import studio.kio.krouter.RoutePath

@RoutePath(DemoRoute::class)
class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        val s = KRouter.getParameter(intent, DemoRoute)

        findViewById<TextView>(R.id.textView).text = "$s"
    }
}