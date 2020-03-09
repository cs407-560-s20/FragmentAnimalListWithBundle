package com.example.fragmentanimallistwithinterface


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_animal_list.view.*

/**
 * A simple [Fragment] subclass.
 */
class AnimalListFragment : Fragment() {

    private val TAG = "AnimalListFragment"

    // Create a list of some strings that will be shown in the listview
    private val animalList = listOf("dog", "cat", "bear", "rabbit")



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_animal_list, container, false)

        Log.d(TAG, "onCreateView after inflation")

        // Create an adapter with 3 parameters: activity (this), layout, list
        val myAdapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, animalList)

        // set the adapter to listview -- MUST USE view in the beginning, otherwise expect a crash!!!
        view.animal_list.adapter = myAdapter


        // Registering setOnItemClickListener that listens item click events in the listview
        view.animal_list.setOnItemClickListener { list, item, position, id ->

            // Determine which item in the list is selected
            val selectedItem = list.getItemAtPosition(position).toString()
            Log.d(TAG, selectedItem)


            // Create an instance of the target fragment
            val myAnimalDetailsFragment = AnimalDetailsFragment()

            // Put the position data into the bundle with a key, then set the bundle to arguments
            val bundle = Bundle()
            bundle.putInt("position", position)
            myAnimalDetailsFragment.arguments = bundle


            // Check the orientation to inflate the details fragment in an fragment appropriate container
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, myAnimalDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
            else{
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.details_container, myAnimalDetailsFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }


}