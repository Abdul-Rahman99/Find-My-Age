package com.example.findmyage


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*

abstract class MainActivity : AppCompatActivity() {

    private var ageInDays: Int = 0
    private var ageInMonth: Int = 0
    private var ageInYears: Int = 0
    private lateinit var etDate: TextView
    private lateinit var btnDate: Button
    private lateinit var btnAge: Button

    @Override
     public fun onViewCreated(view : View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        setContentView(R.layout.activity_main)


        etDate = findViewById(R.id.tvShowAge)
        btnAge = findViewById(R.id.buGetAge)
        btnDate = findViewById(R.id.btnUserDate)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val userDay: Int = dayOfMonth.toString().toInt()
            val userMonth: Int = month.toString().toInt()
            val userYear: Int = year.toString().toInt()

            val calenderDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            val calenderMonth = Calendar.getInstance().get(Calendar.MONTH)
            val calenderYear = Calendar.getInstance().get(Calendar.YEAR)

            ageInDays = userDay - calenderDay
            ageInMonth = calenderMonth - userMonth
            ageInYears = calenderYear - userYear

            val intent = Intent(this, btnAge::class.java)
            intent.putExtra("YEARS", ageInYears)
            intent.putExtra("MONTHS", ageInMonth)
            intent.putExtra("DAYS", ageInDays)
            startActivity(intent)
        }

        btnDate.setOnClickListener {
            DatePickerDialog(
                this,
                datePicker,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btnAge.setOnClickListener {
            if (intent != null) {
                val year = intent.extras!!.get("YEARS")
                val month = intent.extras!!.get("MONTHS")
                val day = intent.extras!!.get("DAYS")

                etDate.text = "You are $year Years, $month Months, $day Days."
            }
        }
    }


}
//
//private fun updateLabel(a:Int , b:Int , c:Int) {
//    val myFormat = "dd-MM-yyyy"
//    val sdf = SimpleDateFormat(myFormat, Locale.US)
//
//
//
//    //etDate.text = sdf.format(myCalendar.time)
//}

//   button.setOnClickListener {
//
//            val userDay: Int = day.text.toString().toInt()
//            val userMonth: Int = month.text.toString().toInt()
//            val userYear: Int = year.text.toString().toInt()
//
//
//            val calenderDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//            val calenderMonth = Calendar.getInstance().get(Calendar.MONTH)
//            val calenderYear = Calendar.getInstance().get(Calendar.YEAR)
//
//
//
//
//            showAge.text =
//                "You are $ageInYears Years, $ageInMonths Months, $ageInDays Days."
//        }


