package studio.kio.demo.a

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import studio.kio.demo.R
import studio.kio.demo.b.ListActivity
import studio.kio.demo.c.DemoActivity
import studio.kio.demo.common.DemoRoute
import studio.kio.demo.common.ListRoute
import studio.kio.demo.common.User
import studio.kio.krouter.KRouter
import studio.kio.krouter.KRouterConnection
import studio.kio.krouter.RouterMapping

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //先手动做一下注解处理器要做的事情
        RouterMapping.routeActivityMap[ListRoute] = ListActivity::class
        RouterMapping.routeActivityMap[DemoRoute] = DemoActivity::class

        findViewById<Button>(R.id.to_list).setOnClickListener {

            KRouter
                .navigateTo(this, ListRoute, 10)
                .onReturn(object : KRouterConnection.KRouterCallback<Int> {
                    override fun onResult(t: Int) =
                        Toast.makeText(this@MainActivity, "Back from List $t", Toast.LENGTH_LONG)
                            .show()
                })

        }

        findViewById<Button>(R.id.to_demo).setOnClickListener {
            KRouter.navigateTo(this, DemoRoute, User("kio", 21))
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(">>>>", "$data")
    }
}