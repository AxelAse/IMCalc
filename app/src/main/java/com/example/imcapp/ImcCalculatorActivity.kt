package com.example.imcapp

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var numeroEdad:TextView
    private lateinit var btnSubtractAge:FloatingActionButton
    private lateinit var btnAddAge:FloatingActionButton
    private lateinit var numeroPeso:TextView
    private lateinit var btnSubtractWeight:FloatingActionButton
    private lateinit var btnAddWeight:FloatingActionButton
    private var isMaleselected = true
    private var edadIni=0
    private var pesoIni=0
    private fun getBackgroundColor(isComponentSelected: Boolean): Int
    {
        val colorReference= if(isComponentSelected)
        {
            R.color.bg_comp_sel
        }
        else
        {
            R.color.bg_comp
        }
        return ContextCompat.getColor(this, colorReference)
    }
    private fun setGenderColor()
    {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleselected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(!isMaleselected))
    }
    private fun setAge(nueva: Int)
    {
        edadIni=edadIni+nueva
        numeroEdad.text = edadIni.toString()
    }
    private fun setWeight(nueva: Int)
    {
        pesoIni=pesoIni+nueva
        numeroPeso.text=pesoIni.toString()
    }
    private fun initComponents()
    {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
    }
    private fun initListeners()
    {
        viewMale.setOnClickListener()
        {
            isMaleselected=true
            setGenderColor()
        }
        viewFemale.setOnClickListener()
        {
            isMaleselected=false
            setGenderColor()
        }
        rsHeight.addOnChangeListener{_, value,_ -> tvHeight.text=DecimalFormat("#.##").format(value) + " cm"}
        btnAddAge.setOnClickListener()
        {
            setAge(1)
        }
        btnSubtractAge.setOnClickListener()
        {
            setAge(-1)
        }
        btnAddWeight.setOnClickListener()
        {
            setWeight(1)
        }
        btnSubtractWeight.setOnClickListener()
        {
            setWeight(-1)
        }
    }
    private fun initUI()
    {
        setGenderColor()
        setAge(0)
        setWeight(0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)
        initComponents()
        initUI()
        initListeners()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}