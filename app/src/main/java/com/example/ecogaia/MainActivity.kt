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
        this.url = "http://192.168.140.131:8080/"
=======
<<<<<<< HEAD
>>>>>>> 46327b69e9d582aee1aac73a02f51ef264e5a854

        this.url = "http://192.168.1.78:8080/"

=======
        this.url = "http://192.168.43.209:8080/"
>>>>>>> 6c13fc623e83864ec339f958dd0ec9f17785bb50
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
<<<<<<< HEAD

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

=======
>>>>>>> 6c13fc623e83864ec339f958dd0ec9f17785bb50
        }
        return super.onOptionsItemSelected(item)
    }
}