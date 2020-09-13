package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var currentState: Output = EmptyOutput("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //C
        emptyTextView.setOnClickListener {
            val empty = EmptyOutput("")
            result_textView.text = empty.output(currentState)
            currentState = empty
        }

        //=
        equalTextView.setOnClickListener {
            val result = EqualToEvaluate("=")
            result_textView.text = result.output(currentState)
            currentState = result
        }

        //+
        additionTextView.setOnClickListener {
            val add = Add("+")
            result_textView.text = add.output(currentState)
            currentState = add
        }

        //-
        subtractTextView.setOnClickListener {
            val subtract = Subtract("-")
            result_textView.text = subtract.output(currentState)
            currentState = subtract
        }

        //*
        multipleTextView.setOnClickListener {
            val multiple = Multiple("*")
            result_textView.text = multiple.output(currentState)
            currentState = multiple
        }

        // ./.
        divideTextView.setOnClickListener {
            val divide = Divide("/")
            result_textView.text = divide.output(currentState)
            currentState = divide
        }

        zeroTextView.setOnClickListener {
            val zero = Number("0")
            result_textView.text = zero.output(currentState)
            currentState = zero
        }

        oneTextView.setOnClickListener{
            val one = Number("1")
            result_textView.text = one.output(currentState)
            currentState = one
        }

        twoTextView.setOnClickListener {
            val two = Number("2")
            result_textView.text = two.output(currentState)
            currentState = two
        }

        threeTextView.setOnClickListener {
            val three = Number("3")
            result_textView.text = three.output(currentState)
            currentState = three
        }

        fourTextView.setOnClickListener {
            val four = Number("4")
            result_textView.text = four.output(currentState)
            currentState = four
        }

        fiveTextView.setOnClickListener {
            val five = Number("5")
            result_textView.text = five.output(currentState)
            currentState = five
        }

        sixTextView.setOnClickListener {
            val six = Number("6")
            result_textView.text = six.output(currentState)
            currentState = six
        }

        sevenTextView.setOnClickListener {
            val seven = Number("7")
            result_textView.text = seven.output(currentState)
            currentState = seven
        }

        eightTextView.setOnClickListener {
            val eight = Number("8")
            result_textView.text = eight.output(currentState)
            currentState = eight
        }

        nineTextView.setOnClickListener {
            val nine = Number("9")
            result_textView.text = nine.output(currentState)
            currentState = nine
        }
    }

}