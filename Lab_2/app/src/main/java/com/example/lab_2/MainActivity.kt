package com.example.lab_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val operations = listOf<Button>(plus_button, minus_button, division_button, multiply_button)

        for (button in operations) {
            button.setOnClickListener { v ->
                when {
                    first_number.text.isEmpty() -> Snackbar.make(v, R.string.first_empty_view, Snackbar.LENGTH_LONG).show()
                    second_number.text.isEmpty() -> Snackbar.make(v, R.string.second_empty_view, Snackbar.LENGTH_LONG).show()
                    else -> try {
                        val number1 = first_number.text.toString().toDouble()
                        val number2 = second_number.text.toString().toDouble()

                        when (button) {
                            plus_button -> answer_text.text = (number1 + number2).toString()
                            minus_button -> answer_text.text = (number1 - number2).toString()
                            multiply_button -> answer_text.text = (number1 * number2).toString()
                            division_button -> if (number2 != 0.0) {
                                answer_text.text = (number1 / number2).toString()
                            } else {
                                answer_text.text = getString(R.string.infinity_text)
                            }
                        }
                    } catch (e: Exception){
                        Snackbar.make(v, R.string.too_big_numbers, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        clear_button.setOnClickListener { v ->
            try {
                when {
                    first_number.text.isEmpty() && second_number.text.isEmpty() -> Snackbar.make(v, R.string.views_are_empty, Snackbar.LENGTH_LONG).show()
                }
                when (v) {
                    clear_button -> {
                        first_number.text.clear()
                        second_number.text.clear()
                    }
                }
            } catch (e: Exception){
                Snackbar.make(v, R.string.views_are_empty, Snackbar.LENGTH_LONG).show()
            }
        }
    }
}
