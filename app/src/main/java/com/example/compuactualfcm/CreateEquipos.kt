package com.example.compuactualfcm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.compuactualfcm.model.Appoiment

class CreateEquipos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_equipos)
        //Obtener RecyclerView del xml
        val rvapooint = findViewById<RecyclerView>(R.id.rviewappoit)

        val appoimetn = ArrayList<Appoiment>()
        appoimetn.add(
            Appoiment(1,"Armando", "Reparacion","100")
        )
        appoimetn.add(
            Appoiment(2,"Gaby", "Entrega","200")
        )
        appoimetn.add(
            Appoiment(3,"Fany", "Validacion","0")
        )
        //Asignar la funcion liner que nos permitira utilizar el Recycler a nuestra manera
        rvapooint.layoutManager = LinearLayoutManager(this) //GritayoutManager
        rvapooint.adapter = AppoitmentAdapter(appoimetn)
    }
}