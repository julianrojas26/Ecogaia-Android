package com.example.ecogaia

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ecogaia.adapter.CarritoListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var appBarCondiguration: AppBarConfiguration
    private lateinit var user: JSONObject
    private lateinit var rol: String
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

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        /// Session
        bundle = Bundle()

        this.user = JSONObject(intent.getStringExtra("user").toString())
        this.rol = user.getString("rol")

        this.url = "http://192.168.1.7:8080/"

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

    fun actualizar(view: View?) {
        val i = Intent(this, activity_actualizar_perfil::class.java).apply { }
        i.putExtras(bundle)
        startActivity(i)

    }

    fun addProduct(view: View?) {
        val i = Intent(this, activity_agregar_producto::class.java).apply { }
        i.putExtras(bundle)
        startActivity(i)
    }

    fun addusuario(view: View) {
        val i = Intent(this, activity_usuario::class.java).apply { }
        i.putExtras(bundle)
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

    fun endSession(view: View?) {
        val i = Intent(this, fragment_login::class.java).apply { }
        startActivity(i)
        finish()
    }

    /*conexion overflow*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.leftoverflow, menu)
        if (this.rol == "usuario") {
            val item4 = menu!!.findItem(R.id.item4)
            item4.isVisible = false
            val item5 = menu!!.findItem(R.id.item5)
            item5.isVisible = false
            val item6 = menu!!.findItem(R.id.item6)
            item6.isVisible = false
        } else if (this.rol == "repartidor") {
            val item5 = menu!!.findItem(R.id.item5)
            item5.isVisible = false
            val item6 = menu!!.findItem(R.id.item6)
            item6.isVisible = false
        }
        return super.onCreateOptionsMenu(menu)
    }

    ///boton back

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
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

                R.id.item5 -> {
                    val navController =
                        Navigation.findNavController(this, R.id.nav_host_fragment_container)
                    navController.navigate(R.id.fragment_gestionar)
                }

                R.id.item6 -> {
                    val navController =
                        Navigation.findNavController(this, R.id.nav_host_fragment_container)
                    navController.navigate(R.id.fragment_estadisticas)
                }

                R.id.item7 -> endSession(null)
        }
        return super.onOptionsItemSelected(item)
    }
}

