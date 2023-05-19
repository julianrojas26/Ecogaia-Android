package com.example.ecogaia.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.ecogaia.R
import org.json.JSONObject

class fragment_detalleProductos : DialogFragment() {
    private lateinit var tbProdDets : Toolbar
    private lateinit var nombre_prod : TextView
    private lateinit var categoria_prod: TextView
    private lateinit var precio_prod: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ll = inflater.inflate(R.layout.fragment_detalle_productos, container, false)
        this.tbProdDets = ll.findViewById(R.id.tbProdDets)
        this.tbProdDets.setNavigationOnClickListener{
            dismiss()
        }

        this.nombre_prod = ll.findViewById(R.id.nombre_produ)
        this.categoria_prod = ll.findViewById(R.id.categoria_produ)
        this.precio_prod = ll.findViewById(R.id.precio_produ)

        return ll
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.tbProdDets.navigationIcon = ContextCompat.getDrawable(view.context, R.drawable.close)

        val tips = JSONObject(arguments?.getString("productos"))

        this.nombre_prod.text = tips.getString("prod_Nombre")
        this.categoria_prod.text = tips.getString("prod_Categoria")
        this.precio_prod.text = tips.getString("favoritos")
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }
}