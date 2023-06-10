package com.example.ecogaia.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.ecogaia.Models.tips
import com.example.ecogaia.R
import org.json.JSONObject


class fragment_detalle_repartidor : DialogFragment() {

    private lateinit var  detalle_rep_usu: TextView
    private lateinit var detale_rep_direccion: TextView
    private lateinit var detalle_rep_telefono: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_detalle_repartidor, container, false)

        this.detalle_rep_usu = ll.findViewById(R.id.detalle_rep_usu)
        this.detale_rep_direccion = ll.findViewById(R.id.detale_rep_direccion)
        this.detalle_rep_telefono = ll.findViewById(R.id.detalle_rep_telefono)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dis = JSONObject(arguments?.getString("dis"))

        this.detalle_rep_usu.text = dis.getString("usu_nombre")
        this.detale_rep_direccion.text = dis.getString("usu_direccion")
        this.detalle_rep_telefono.text = dis.getString("usu_telefono")

    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
    }
}