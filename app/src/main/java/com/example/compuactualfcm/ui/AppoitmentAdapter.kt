package com.example.compuactualfcm.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.compuactualfcm.R
import com.example.compuactualfcm.model.Appoiment

class AppoitmentAdapter
    : RecyclerView.Adapter<AppoitmentAdapter.ViewHolder>() {

    var appointments = ArrayList<Appoiment>()
    class ViewHolder(itentView: View) : RecyclerView.ViewHolder(itentView){
        val twAppoimentId = itentView.findViewById<TextView>(R.id.twAppoiment)
        val twmedicoId = itentView.findViewById<TextView>(R.id.twmedicoId)
        val twfechaprog = itentView.findViewById<TextView>(R.id.twfechaprog)
        val twhora = itentView.findViewById<TextView>(R.id.twhora)

    }
    //inflates xml items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_appoiment, parent, false)
        )
    }
    //Devolver cantidad de elementos
    override fun getItemCount() = appointments.size

    //number of elements
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appoiment = appointments[position]

        holder.twAppoimentId.text = "ID de su equipo #${appoiment.id}"
        holder.twmedicoId.text = "El folio de su equipo: ${appoiment.folio}"
        holder.twfechaprog.text = "El tipo se servicio: ${appoiment.servicio}"
        holder.twhora.text = "Problema: ${appoiment.problema}"
    }

}