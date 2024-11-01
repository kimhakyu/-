package com.example.calculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var currentInput = ""
    private var result = 0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val numberButtons = listOf(binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9)

        val operatorButtons = listOf(binding.btnAdd, binding.btnSub, binding.btnMult, binding.btnDiv)

        numberButtons.forEach { button ->
            button.setOnClickListener {
                onNumberButtonClik(button.text.toString())
            }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener {
                onOperatorButtonClick(button.text.toString())
            }
        }
    }

    private fun onNumberButtonClik(number: String) {
        currentInput += number
        binding.modyfiyTextView.text = currentInput
    }

    private fun onOperatorButtonClick(op: String) {
        if(currentInput.isNotEmpty()) {
            val inputNumber = currentInput.toInt()
            if(operator.isNotEmpty()) {
                result
                result = numberCalulator(result!!, inputNumber, operator)
            }
            binding.resultTextView.text = result.toString()
            binding.modyfiyTextView.append("$result $op ")
            //currentInput = ""
            operator = op
        } else if (operator.isNotEmpty()) {
            operator = op
            updateLastOperatorInView(op)
        }
    }
    private fun updateLastOperatorInView(newOperator: String) {
        val currentText = binding.modyfiyTextView.text.toString()
        val updatedText = currentText.dropLast(2) + " $newOperator "
        binding.modyfiyTextView.append(updatedText)
    }

    private fun numberCalulator(num1: Int, num2: Int, op: String): Int {
        return when(op) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "x" -> num1 * num2
            "%" -> num1 / num2
            else -> 0
        }
    }


}