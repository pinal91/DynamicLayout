package com.geeksforgeeks.dynamiclayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geeksforgeeks.dynamiclayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private var languageList = ArrayList<Language>()
    private var languageListTuesday = ArrayList<Language>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addNewView()
        addTuesView()

        binding.buttonSubmitList.setOnClickListener {
            saveData()
            saveTuesday()
        }

        binding.buttonShowList.setOnClickListener {
            showData()
            showDataTuesday()
        }

    }

    private fun showData() {
        val count = binding.parentLinearLayout.childCount
        var text=""
        for (i in 0 until count) {
            Toast.makeText(this, "Language at $i is ${languageList[i].from} and experience is ${languageList[i].exp} ", Toast.LENGTH_SHORT).show()
            text=text+" "+"Language at $i is ${languageList[i].from} and experience is ${languageList[i].exp} "
        }

        binding.txtSelected.text=text
    }
    private fun showDataTuesday() {
        val count = binding.tuesdayLinearLayout.childCount
        var text=""
        for (i in 0 until count) {
            Toast.makeText(this, "Language at $i is ${languageListTuesday[i].from} and experience is ${languageListTuesday[i].exp} ", Toast.LENGTH_SHORT).show()
            text=text+" "+"Language at $i is ${languageListTuesday[i].from} and experience is ${languageListTuesday[i].exp} "
        }

        binding.txtSelectedTuesday.text=text
    }


     fun addNewView() {
        val inflater = LayoutInflater.from(this).inflate(R.layout.row_add_lamguage, null)
        binding.parentLinearLayout.addView(inflater, binding.parentLinearLayout.childCount)
         val count = binding.parentLinearLayout.childCount
         var v: View? = null

         for (i in 0 until count) {
             v = binding.parentLinearLayout.getChildAt(i)
             if (i==0){
                 var btn=v.findViewById<Button>(R.id.btnDelete)
                 btn.text="ADD TIME"

                 btn.setOnClickListener {
                     addNewView()
                 }
             }else{
                 var btn=v.findViewById<Button>(R.id.btnDelete)
                 btn.text="REMOVE TIME"
             }
         }


    }


    fun addTuesView() {


        val inflater = LayoutInflater.from(this).inflate(R.layout.row_add_tuesday, null)
        binding.tuesdayLinearLayout.addView(inflater, binding.tuesdayLinearLayout.childCount)
        val count = binding.tuesdayLinearLayout.childCount
        var v: View? = null

        for (i in 0 until count) {
            v = binding.tuesdayLinearLayout.getChildAt(i)
            if (i==0){
                var btn=v.findViewById<Button>(R.id.btnDelete)
                btn.text="ADD TIME"

                btn.setOnClickListener {
                    addTuesView()
                }
            }else{
                var btn=v.findViewById<Button>(R.id.btnDelete)
                btn.text="REMOVE TIME"
            }
        }


    }

    fun onDelete(v: View) {
        binding.parentLinearLayout.removeView(v.parent as View)
    }
    fun onDeleteTuesday(v: View) {
        binding.tuesdayLinearLayout.removeView(v.parent as View)
    }

    private fun saveData() {
        languageList.clear()
        val count = binding.parentLinearLayout.childCount
        var v: View? = null

        for (i in 0 until count) {
            v = binding.parentLinearLayout.getChildAt(i)

            val from_time: Spinner = v.findViewById(R.id.exp_spinner_from)
            val experience: Spinner = v.findViewById(R.id.exp_spinner_to)

            val language = Language()
            var s1= from_time.selectedItem as String
            var s2 = experience.selectedItem as String
            if (s1.equals("From Time")||s2.equals("To Time")){
                Toast.makeText(this, "Please select all time first", Toast.LENGTH_SHORT).show()

            }else {
                language.from = from_time.selectedItem as String
                language.exp = experience.selectedItem as String
            }
            languageList.add(language)


        }



    }
    private fun saveTuesday()
    {

        languageListTuesday.clear()
        val count = binding.tuesdayLinearLayout.childCount
        var v: View? = null

        for (i in 0 until count) {
            v = binding.tuesdayLinearLayout.getChildAt(i)

            val from_time: Spinner = v.findViewById(R.id.exp_spinner_from)
            val experience: Spinner = v.findViewById(R.id.exp_spinner_to)

            val language = Language()
            language.from = from_time.selectedItem as String
            language.exp = experience.selectedItem as String

            languageListTuesday.add(language)


        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}