package com.example.ecogaia.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.example.ecogaia.R
import org.json.JSONObject


class fragment_detalle_blog: DialogFragment() {
    private lateinit var tbBlogDets : Toolbar
    private lateinit var comp_usu : TextView
    private lateinit var titulo : TextView
    private lateinit var cuerpo : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val ll = inflater.inflate(R.layout.fragment_detalle_blog, container, false)
        this.tbBlogDets = ll.findViewById(R.id.tbBlogDets)

        this.comp_usu = ll.findViewById(R.id.nombre_tip)
        this.titulo = ll.findViewById(R.id.titulo)
        this.cuerpo = ll.findViewById(R.id.descripcion_tip)
        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.tbBlogDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)


        val tips = JSONObject(arguments?.getString("tips"))

        this.titulo.text = tips.getString("titulo")
        this.comp_usu.text = tips.getString("comp_usuario")
        this.cuerpo.text = tips.getString("cuerpo")

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

}