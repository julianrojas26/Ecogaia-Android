package com.example.ecogaia.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.ecogaia.R
import com.example.ecogaia.adapter.GestionarProdsAdaptador
import com.example.ecogaia.adapter.GestionarProdsListener
import com.example.ecogaia.adapter.GestionarUsersAdaptor
import com.example.ecogaia.adapter.GestionarUsersListener
import com.example.ecogaia.fragment_actualizar_producto
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class GestionarUsuariosFragment : Fragment(),  GestionarUsersListener {
    private lateinit var recycler_usuarios: RecyclerView
    private lateinit var viewAlpha: View
    private lateinit var pgbar: ProgressBar
    private lateinit var rlGestList: RelativeLayout
    private lateinit var gestionarUsers: ArrayList<JSONObject>
    private var userId: String? = null
    private var userEmail: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val bundle = activity?.intent?.extras

        val ll = inflater.inflate(R.layout.fragment_gestionar_usuarios, container, false)

        val ip = bundle!!.getString("url")
        val queue = Volley.newRequestQueue(this.context)

        var url = ip + "listarUsuario"

        listarUsuario(queue, url)

        val delete_user = ll.findViewById<Button>(R.id.DeleteUser)

        delete_user.setOnClickListener() {
            if (userId == null) {
                Toast.makeText(this.context, "Debes seleccionar un usuario", Toast.LENGTH_LONG)
                    .show()
            } else {
                url = ip + "eliminarUsuario/" + this.userId

                val deleteRequest = StringRequest(Request.Method.DELETE, url, { response ->
                    Toast.makeText(this.context, response, Toast.LENGTH_LONG).show()
                    listarUsuario(queue, ip + "listarUsuario")
                }, { error ->
                    Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
                })
                queue.add(deleteRequest)

            }
        }

        val act_user = ll.findViewById<Button>(R.id.editarUsuario)

        act_user.setOnClickListener() {
            if (userId == null) {
                Toast.makeText(this.context, "Debes seleccionar un usuario", Toast.LENGTH_LONG)
                    .show()
            } else {
                val bundle = Bundle()
                bundle.putString("email", this.userEmail)
                findNavController().navigate(R.id.fragment_actualizar_usuario, bundle)
            }
        }

        this.recycler_usuarios = ll.findViewById(R.id.recycler_gestionar_usuarios)
        this.viewAlpha = ll.findViewById(R.id.view_gestionarUsuariosList)
        this.pgbar = ll.findViewById(R.id.pgbar_gestionarUsuariosList)
        this.rlGestList = ll.findViewById(R.id.rl_GestionarUsuariosList)
        return ll
    }

    fun listarUsuario(queue: RequestQueue, url: String) {
        val userRequest = StringRequest(Request.Method.GET, url, { response ->
            val jsonArray = JSONArray(response)
            this.gestionarUsers = ArrayList()
            try {
                var i = 0
                val l = jsonArray.length()
                while (i < l) {
                    gestionarUsers.add(jsonArray[i] as JSONObject)
                    i++
                }
                Log.d("REPARTIDOR", this.gestionarUsers.toString())
                this.recycler_usuarios.adapter = GestionarUsersAdaptor(this.gestionarUsers, this)
                this.viewAlpha.visibility = View.INVISIBLE
                this.pgbar.visibility = View.INVISIBLE
            } catch (e: JSONException) {
                Log.w("ERROR", e)
            }
        }, { error ->
            Toast.makeText(this.context, error.toString(), Toast.LENGTH_LONG).show()
        })

        queue.add(userRequest)
    }

    override fun onUserClicked(user: JSONObject, position: Int) {
        this.userId = user.getString("id_Usuario")
        this.userEmail = user.getString("usu_correo")
    }
}