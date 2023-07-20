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
    private lateinit var imageList: ArrayList<SlideModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel ("https://images.pexels.com/photos/17399258/pexels-photo-17399258.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"))
        imageList.add(SlideModel("https://images.pexels.com/photos/17399371/pexels-photo-17399371.jpeg?auto=compress&cs=tinysrgb&w=600&lazy=load"))
        imageList.add(SlideModel("https://images.pexels.com/photos/17399395/pexels-photo-17399395.png?auto=compress&cs=tinysrgb&w=600&lazy=load"))


        val ll = inflater.inflate(R.layout.fragment_home, container, false)
        val imageSlider = ll.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        val categoryMascotas = ll.findViewById<ImageButton>(R.id.btnMascotas)
        val categoryMaquillaje = ll.findViewById<ImageButton>(R.id.btnMaquillaje)
        val categoryHogar = ll.findViewById<ImageButton>(R.id.btnHogar)
        val categoryPersonal = ll.findViewById<ImageButton>(R.id.btnPersonal)


        categoryMascotas.setOnClickListener() {
            val bundle = bundleOf("Categoria" to "Mascotas")
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