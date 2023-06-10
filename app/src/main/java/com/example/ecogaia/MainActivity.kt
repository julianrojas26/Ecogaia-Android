package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarCondiguration: AppBarConfiguration
    private lateinit var user: JSONObject
    private lateinit var url: String
    private lateinit var bundle: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment?
                ?: return
        val navController = host.navController
        appBarCondiguration = AppBarConfiguration(navController.graph)
        setupActionBar(navController, appBarCondiguration)
        setupBottonNavMenu(navController)
        /// Session
        bundle = Bundle()
        this.user = JSONObject(intent.getStringExtra("user").toString())
<<<<<<< HEAD
        this.url = "http://192.168.1.67:8080/"
        Log.w("USERR", user.toString())
=======
        this.url = "http://192.168.1.66:8080/"


>>>>>>> 4d16e2deb4e8b1fd8d9c4b182a5d95e4f2f4c935
        bundle.putString("user", this.user.toString())
        bundle.putString("url", this.url)
        intent.putExtras(bundle)
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfiguration: AppBarConfiguration,
    ) {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun setupBottonNavMenu(navController: NavController) {
        val bottonNav = findViewById<BottomNavigationView>(R.id.btnMenu)
        bottonNav.setupWithNavController(navController)
    }

    /* Navegacion Extra */

    fun actualizar(view: View?){
        val i = Intent(this, activity_actualizar_perfil::class.java).apply { }
        i.putExtras(bundle)
        startActivity(i)

    }

    fun addProduct(view: View?) {
        val i = Intent(this, activity_agregar_producto::class.java).apply { }
        startActivity(i)
    }

    fun addusuario(view: View) {
        val i = Intent(this, activity_usuario::class.java).apply { }
        startActivity(i)
    }

    fun addBlog(view: View) {
        val i = Intent(this, activity_agregar_blog::class.java).apply { }
        i.putExtras(bundle)
        startActivity(i)
    }

    fun favoritos(view: View?) {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_favoritos)
    }

    /*conexion overflow*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.leftoverflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    /* Menu lateral */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item1 -> {
                val navController =
                    Navigation.findNavController(this, R.id.nav_host_fragment_container)
                navController.navigate(R.id.fragment_nosotros)
            }
            R.id.item2 -> favoritos(null)

            R.id.item3 -> {
                val navController =
                    Navigation.findNavController(this, R.id.nav_host_fragment_container)
                navController.navigate(R.id.fragment_carrito)
            }
            R.id.item4 -> {
                val navController =
                    Navigation.findNavController(this, R.id.nav_host_fragment_container)
                navController.navigate(R.id.fragment_repartidor)
            }

            R.id.item4 -> {
                val navController =
                    Navigation.findNavController(this, R.id.nav_host_fragment_container)
                navController.navigate(R.id.fragment_repartidor)
            }

            R.id.item5 -> {
                val navController =
                    Navigation.findNavController(this, R.id.nav_host_fragment_container)
                navController.navigate(R.id.fragment_gestionar)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}

