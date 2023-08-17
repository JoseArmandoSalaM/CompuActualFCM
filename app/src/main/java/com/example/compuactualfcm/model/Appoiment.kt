package com.example.compuactualfcm.model

import com.google.gson.annotations.SerializedName

/*
        "id": 3,
        "folio": "CP-08-23-00002",
        "costo_final": null,
        "costo_aprox": "10.00",
        "dinero_acuenta": "5.00",
        "fecha_recibido": "2023-08-02",
        "fecha_entregado": null,
        "descripcion_problema": "SADASD",
        "tipo_servicio": "Hardware",
        "trabajador_id": 1,
        "cliente_id": 2,
        "etapa_id": 2,
 */

data class Appoiment(
    val id: Int,
    val folio: String,
   @SerializedName("tipo_servicio") val servicio: String,
    @SerializedName("descripcion_problema") val problema: String,
                     )