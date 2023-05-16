package com.example.ecogaia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.android.volley.toolbox.StringRequest
import org.json.JSONArray


class fragment_login : Fragment() {

     var confirUsuario: String? = null
     var confirContraseña: String? = null
     var conUsuario: EditText? = null
     var conContraseña: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_login, container, false)
    }


}