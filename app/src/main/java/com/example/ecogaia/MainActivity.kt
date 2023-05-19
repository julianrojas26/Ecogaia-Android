package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
<<<<<<< HEAD
import com.denzcoskun.imageslider.ImageSlider
import com.example.ecogaia.UI.fragment_favoritos
=======
>>>>>>> dae9558230c12e116e11156dad6a0ce86406908e
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var appBarCondiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment? ?: return
        val navController = host.navController
        appBarCondiguration = AppBarConfiguration(navController.graph)
        setupActionBar(navController, appBarCondiguration)
        setupBottonNavMenu(navController)
    }



    private fun setupActionBar(navController:NavController, appBarConfiguration: AppBarConfiguration){
        setupActionBarWithNavController(navController,appBarConfiguration)
    }
    private fun setupBottonNavMenu(navController: NavController){
        val bottonNav = findViewById<BottomNavigationView>(R.id.btnMenu)
        bottonNav.setupWithNavController(navController)
    }

    fun addProduct() {
        val i = Intent(this, Activity_agregarProducto::class.java).apply {  }
        startActivity(i)
    }
    fun addusuario(view: View){
        val i= Intent(this, activity_usuario::class.java).apply {  }
        startActivity(i)
    }

    fun addBlog(view: View) {
        val i = Intent(this,activity_agregar_blog::class.java).apply {  }
        startActivity(i)
    }


    /*conexion overflow*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.leftoverflow, menu)
        menuInflater.inflate(R.menu.rightverflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> login()
            R.id.item2 -> registro()
            R.id.item3 -> nosotros()
            R.id.item4 -> favoritos(null)
            R.id.item5 -> categorias()
            R.id.item6 -> carrito()
        }
        return super.onOptionsItemSelected(item)
    }

    fun registro() {
        val i = Intent(this, activity_usuario::class.java).apply {  }
        startActivity(i)
    }


    fun login () {
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_login)
    }

    fun favoritos(view: View?){
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_favoritos)
    }


    fun nosotros () {
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_nosotros)
    }

    fun categorias () {
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_categorias)
    }

    fun carrito () {
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_carrito)
    }
}

