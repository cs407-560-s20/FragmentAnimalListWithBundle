package com.example.fragmentanimallistwithinterface


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_animal_details.*

/**
 * A simple [Fragment] subclass.
 */
class AnimalDetailsFragment : Fragment() {

    private val TAG = "AnimalDetailsFragment"

    private val ANIMAL_NAME_LIST = listOf("Dog", "Cat", "Bear", "Rabbit")

    // Source: wikipedia
    private val ANIMAL_DETAILS = listOf(

        "Dogs' typical lifespan varies widely among breeds, but for most the median longevity, " +
                "the age at which half the dogs in a population have died and half are still alive, " +
                "ranges from 10 to 13 years. Individual dogs may live well beyond the median of their breed.",

        "Cats' average lifespan of has risen in recent decades. In the early 1980s, " +
                "it was about seven years, rising to 9.4 years in 1995 15.1 years in 2018. " +
                "Some cats have been reported as surviving into their 30s, with the oldest known cat, " +
                "Creme Puff, dying at a verified age of 38",

        "Bears can live up to 25 years of age in the wild. The oldest wild brown bear on record was " +
                "nearly 37 years old.The oldest recorded female in captivity was nearly 40 years old, " +
                "while males in captivity have been verified to live up to 47 years, with one " +
                "captive male possibly attaining 50 years of age.",

        "Rabbits (Spayed or neutered) kept indoors with proper care may have a lifespan of 8 to 12 " +
                "years, with mixed-breed rabbits typically living longer than purebred specimens, " +
                "and dwarf breeds having longer average lifespans than larger breeds." +
                "The world record for longest-lived rabbit is 18 years."
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animal_details, container, false)
    }

    override fun onStart() {
        super.onStart()

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the details.

        Log.d(TAG, "onStart")

        if (arguments != null) {
            // Set details based on argument (position) passed in
            val position = arguments?.getInt("position") ?: 0
            updateDetails(position)
        }
        else{
            // Load the default position
            updateDetails(0)
        }
    }


    private fun updateDetails(position: Int) {

        //Log.d(TAG, "Received data....$position")

        // Set the text to tetviews after getting the selected position
        animal_name.text = ANIMAL_NAME_LIST[position]
        animal_details.text = ANIMAL_DETAILS[position]

        // Based on the index of position selected, set the corresponding image
        val imageId = when(position){
            0 -> R.drawable.dog
            1 -> R.drawable.cat
            2 -> R.drawable.bear
            else -> R.drawable.rabbit
        }
        animal_image.setImageResource(imageId)
    }

}
