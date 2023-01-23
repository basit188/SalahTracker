package com.kodaish.salahtracker

import android.content.Context
import android.icu.util.IslamicCalendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date

//import com.kodaish.salahtracker.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtTime = findViewById<TextView>(R.id.txtTime)
        val txtDate = findViewById<TextView>(R.id.txtDate)
        txtTime.text = SimpleDateFormat("hh:mm a").format(Date()).uppercase()
        txtDate.text = SimpleDateFormat("dd-MMMM-yyyy").format(Date()).uppercase()

//        val islamicCalendar = IslamicCalendar()

        val btnDecFajr = findViewById<ImageButton>(R.id.btnDecFajr)
        val btnIncFajr = findViewById<ImageButton>(R.id.btnIncFajr)
        val txtFajr = findViewById<TextView>(R.id.txtFajr)

        val btnDecDhuhr = findViewById<ImageButton>(R.id.btnDecDhuhr)
        val btnIncDhuhr = findViewById<ImageButton>(R.id.btnIncDhuhr)
        val txtDhuhr = findViewById<TextView>(R.id.txtDhuhr)

        val btnDecAsr = findViewById<ImageButton>(R.id.btnDecAsr)
        val btnIncAsr = findViewById<ImageButton>(R.id.btnIncAsr)
        val txtAsr = findViewById<TextView>(R.id.txtAsr)

        val btnDecMagrib = findViewById<ImageButton>(R.id.btnDecMagrib)
        val btnIncMagrib = findViewById<ImageButton>(R.id.btnIncMagrib)
        val txtMagrib = findViewById<TextView>(R.id.txtMagrib)

        val btnDecIsha = findViewById<ImageButton>(R.id.btnDecIsha)
        val btnIncIsha = findViewById<ImageButton>(R.id.btnIncIsha)
        val txtIsha = findViewById<TextView>(R.id.txtIsha)

        val btnDecFast = findViewById<ImageButton>(R.id.btnDecFast)
        val btnIncFast = findViewById<ImageButton>(R.id.btnIncFast)
        val txtFast = findViewById<TextView>(R.id.txtFast)


        val sharedPref = getSharedPreferences("salah_pref",Context.MODE_PRIVATE)
        val valFajr = sharedPref.getString("valFajr","0")
        val valDhuhr = sharedPref.getString("valDhuhr","0")
        val valAsr = sharedPref.getString("valAsr","0")
        val valMagrib = sharedPref.getString("valMagrib","0")
        val valIsha = sharedPref.getString("valIsha","0")
        val valFast = sharedPref.getString("valFast","0")

        txtFajr.text = valFajr.toString()
        txtDhuhr.text = valDhuhr.toString()
        txtAsr.text = valAsr.toString()
        txtMagrib.text = valMagrib.toString()
        txtIsha.text = valIsha.toString()
        txtFast.text = valFast.toString()

        btnIncFajr.setOnClickListener(MyIncListener(txtFajr,true,"valFajr"))
        btnDecFajr.setOnClickListener(MyIncListener(txtFajr,false,"valFajr"))

        btnIncDhuhr.setOnClickListener(MyIncListener(txtDhuhr,true,"valDhuhr"))
        btnDecDhuhr.setOnClickListener(MyIncListener(txtDhuhr,false,"valDhuhr"))

        btnIncAsr.setOnClickListener(MyIncListener(txtAsr,true,"valAsr"))
        btnDecAsr.setOnClickListener(MyIncListener(txtAsr,false,"valAsr"))

        btnIncMagrib.setOnClickListener(MyIncListener(txtMagrib,true,"valMagrib"))
        btnDecMagrib.setOnClickListener(MyIncListener(txtMagrib,false,"valMagrib"))

        btnIncIsha.setOnClickListener(MyIncListener(txtIsha,true,"valIsha"))
        btnDecIsha.setOnClickListener(MyIncListener(txtIsha,false,"valIsha"))

        btnIncFast.setOnClickListener(MyIncListener(txtFast,true,"valFast"))
        btnDecFast.setOnClickListener(MyIncListener(txtFast,false,"valFast"))
    }


}

class MyIncListener (private var txtCounter : TextView,
                     private var inc:Boolean,
                     private var prefName:String) : OnClickListener {
    override fun onClick(p0: View?) {
        var num = txtCounter.text.toString().toInt()
        if(inc) num++ else num--
        num = if(num<0) 0 else num
        txtCounter.text = num.toString()
        val sharedPref = p0?.context?.getSharedPreferences("salah_pref",Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(prefName, num.toString())
            apply()
        }

    }
}