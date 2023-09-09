package com.example.bmicalculator

import android.app.Activity
import android.media.AudioManager
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.RadioGroup
import com.example.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val soundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0)
        val soundId = soundPool.load(this, R.raw.sound, 1)

        binding.WeightPicker.minValue = 5
        binding.WeightPicker.maxValue = 200

        binding.HeightPicker.minValue = 50
        binding.HeightPicker.maxValue = 250

//        binding.AgePicker.minValue = 0
//        binding.AgePicker.maxValue = 100


        binding.WeightPicker.setOnValueChangedListener{_,_,_ ->
            soundPool.play(soundId, 1F, 1F, 0, 0, 1F)
            calculateBMI()
        }

        binding.HeightPicker.setOnValueChangedListener{_,_,_ ->
            soundPool.play(soundId, 1F, 1F, 0, 0, 1F)
            calculateBMI()
        }
    }

    private fun calculateBMI(){


        val height = binding.HeightPicker.value
        val doubleHeight = height.toDouble() /100

        val weight = binding.WeightPicker.value

        val radioGroup = findViewById<RadioGroup>(R.id.age_group)

        val bmi = weight.toDouble() / (doubleHeight * doubleHeight)

        binding.result.text = String.format("Your BMI is: %.2f", bmi)

        binding.healthyornot.text = String.format("Considered as : %s", healthymessege(bmi))



//        radioGroup.setOnCheckedChangeListener { _, checkedId ->
//            // Handle checked radio button
//
//            when (checkedId) {
//
//                R.id.under_18 -> {
//                    // Under 18 selected
//                    binding.healthyornot.text = String.format("Considered as : %s", healthymessege(bmi))
//                }
//
//                R.id.age_18_50 -> {
//                    // 18 to 50 selected
//                    binding.healthyornot.text = String.format("Considered as : %s", healthymessege(bmi))
//                }
//
//                R.id.over_50 -> {
//                    // Over 50 selected
//                    binding.healthyornot.text = String.format("Considered as : %s", healthymessege(bmi))
//                }
//
//            }
//
//        }

    }

    private fun healthymessege(bmi: Double): String {

        if (bmi < 18.0)
            return "Underweight"
        if (bmi>18 && bmi<25)
            return "Healthy"
        if (bmi>25 && bmi<30)
            return "Overweight"
        if (bmi>30)
            return "Obese"
        else
            return "Error Occured"


    }

}