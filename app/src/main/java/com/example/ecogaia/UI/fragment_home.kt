package com.example.ecogaia

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_home : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2017/03/27/14/09/black-cat-2178983_1280.jpg", "Gato negro"))
        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2017/09/15/19/49/dog-2753369_1280.jpg", "Golden"))
        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2014/05/18/19/10/capybara-347253_1280.jpg", "Capibara"))
        imageList.add(SlideModel("https://cdn.pixabay.com/photo/2019/07/19/19/41/meerkat-4349513_1280.jpg", "Suricata "))

        val ll = inflater.inflate(R.layout.fragment_home, container, false)
        val imageSlider = ll.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        return ll
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }
}