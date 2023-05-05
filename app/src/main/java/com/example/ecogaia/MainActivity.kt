package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
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

    fun addproducto(view: View){
        val i= Intent(this, Activity_agregarProducto::class.java).apply {  }
        startActivity(i)
    }
    fun addusuario(view: View){
        val i= Intent(this, activity_usuario::class.java).apply {  }
        startActivity(i)
    }



    /*conexion overflow*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show()
            R.id.item2 -> Toast.makeText(this, "item1", Toast.LENGTH_SHORT).show()

        }
        return super.onOptionsItemSelected(item)
    }
}