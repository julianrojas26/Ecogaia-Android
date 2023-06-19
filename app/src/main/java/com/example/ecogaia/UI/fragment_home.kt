package com.example.ecogaia

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

class fragment_home : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel ("https://pixabay.com/illustrations/promo-sale-discount-8061855/",
            "Gato negro"))
        imageList.add(SlideModel(R.drawable.promo2,
            "Golden"))
        imageList.add(SlideModel(R.drawable.promo3,
            "Capibara"))


        val ll = inflater.inflate(R.layout.fragment_home, container, false)
        val imageSlider = ll.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        val categoryMascotas = ll.findViewById<ImageButton>(R.id.btnMascotas)
        val categoryMaquillaje = ll.findViewById<ImageButton>(R.id.btnMaquillaje)
        val categoryHogar = ll.findViewById<ImageButton>(R.id.btnHogar)
        val categoryPersonal = ll.findViewById<ImageButton>(R.id.btnPersonal)


        categoryMascotas.setOnClickListener() {
            val bundle = bundleOf("Categoria" to "Maternidad")
            val navController = Navigation.findNavController(ll)
            navController.navigate(R.id.fragment_categorias, bundle)
        }

        categoryMaquillaje.setOnClickListener() {
            val bundle = bundleOf("Categoria" to "Maquillaje")
            val navController = Navigation.findNavController(ll)
            navController.navigate(R.id.fragment_categorias, bundle)
        }

        categoryHogar.setOnClickListener() {
            val bundle = bundleOf("Categoria" to "Hogar")
            val navController = Navigation.findNavController(ll)
            navController.navigate(R.id.fragment_categorias, bundle)
        }

        categoryPersonal.setOnClickListener() {
            val bundle = bundleOf("Categoria" to "Personal")
            val navController = Navigation.findNavController(ll)
            navController.navigate(R.id.fragment_categorias, bundle)
        }

        return ll
    }


}