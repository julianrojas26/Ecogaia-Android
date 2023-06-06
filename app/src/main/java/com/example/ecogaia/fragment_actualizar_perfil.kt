package com.example.ecogaia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class fragment_actualizar_perfil : DialogFragment() {
    private lateinit var txtNombre : TextView
    private lateinit var txtCorreo : TextView
    private lateinit var txtDireccion : TextView
    private lateinit var txtContra : TextView
    private lateinit var txtTelefono : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL,R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_perfil, container, false )
        this.txtNombre = ll.findViewById(R.id.txtNombre)
        this.txtCorreo = ll.findViewById(R.id.txtCorreo)
        this.txtDireccion = ll.findViewById(R.id.txtDireccion)
        this.txtContra = ll.findViewById(R.id.txtContra)
        this.txtTelefono = ll.findViewById(R.id.txtTelefono)
        return ll
    }

}