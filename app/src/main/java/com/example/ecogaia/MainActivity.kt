package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.UI.fragment_favoritos
import com.example.ecogaia.UI.fragment_productos
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var appBarCondiguration: AppBarConfiguration

    var txtCod_productos : TextView?= null
    var txtId_usuario : TextView? = null

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


    /*conexion overflow*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.rightverflow, menu)
        menuInflater.inflate(R.menu.leftoverflow,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> login()
            R.id.item2 -> registro()
            R.id.item3 -> nosotros()
            R.id.item4 -> Toast.makeText(this, "Favoritos", Toast.LENGTH_SHORT).show()
            R.id.item5 -> categorias()
            R.id.item6 -> carrito()

        }
        return super.onOptionsItemSelected(item)
    }

    fun addfavoritos(view: View){
        val url="http://192.168.160.2:8080/IngresarFavoritos"
        val queue = Volley.newRequestQueue(this)
        val resultadoPost = object : StringRequest(Request.Method.POST, url,
            Response.Listener<String> { response->
                Toast.makeText(this, "Favorito Agredao exitosamente", Toast.LENGTH_LONG).show()
            }, Response.ErrorListener{ error ->
                Toast.makeText(this, "Favorito No Creado $error", Toast.LENGTH_LONG).show()
            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()

                params.put("codigo_prod",txtCod_productos?.text.toString())
                params.put("id_usuario",txtId_usuario?.text.toString())
                return params
                Log.e("params","$params")

            }
        }
        val con = resultadoPost.bodyContentType
        Log.e("a","$con")
        queue.add(resultadoPost)
    }

    fun registro() {
        val i = Intent(this, activity_usuario::class.java).apply {  }
        startActivity(i)
    }

    fun login () {
        val navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)
        navController.navigate(R.id.fragment_login)
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

