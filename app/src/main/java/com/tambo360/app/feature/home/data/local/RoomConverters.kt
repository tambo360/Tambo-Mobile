package com.tambo360.app.feature.home.data.local

import androidx.room.TypeConverter
import com.tambo360.app.feature.home.data.local.entity.LoteCostoDirectoRaw
import com.tambo360.app.feature.home.data.local.entity.LoteMermaRaw
import com.tambo360.app.feature.home.data.local.entity.LoteProduccionIndividualRaw
import org.json.JSONArray
import org.json.JSONObject

class RoomConverters {

    @TypeConverter
    fun fromStringList(value: List<String>): String = JSONArray(value).toString()

    @TypeConverter
    fun toStringList(value: String): List<String> {
        if (value.isBlank()) return emptyList()
        val array = JSONArray(value)
        return List(array.length()) { array.getString(it) }
    }

    @TypeConverter
    fun fromStringDoubleMap(value: Map<String, Double>): String {
        val obj = JSONObject()
        value.forEach { (k, v) -> obj.put(k, v) }
        return obj.toString()
    }

    @TypeConverter
    fun toStringDoubleMap(value: String): Map<String, Double> {
        if (value.isBlank()) return emptyMap()
        val obj = JSONObject(value)
        val map = mutableMapOf<String, Double>()
        val keys = obj.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            map[key] = obj.getDouble(key)
        }
        return map
    }

    @TypeConverter
    fun fromLoteMermaList(value: List<LoteMermaRaw>): String {
        val array = JSONArray()
        value.forEach { item ->
            array.put(
                JSONObject().apply {
                    put("idMerma", item.idMerma)
                    put("tipo", item.tipo)
                    put("cantidad", item.cantidad)
                    put("fechaCreacion", item.fechaCreacion)
                }
            )
        }
        return array.toString()
    }

    @TypeConverter
    fun toLoteMermaList(value: String): List<LoteMermaRaw> {
        if (value.isBlank()) return emptyList()
        val array = JSONArray(value)
        return List(array.length()) { i ->
            val obj = array.getJSONObject(i)
            LoteMermaRaw(
                idMerma = obj.getString("idMerma"),
                tipo = obj.getString("tipo"),
                cantidad = obj.getDouble("cantidad"),
                fechaCreacion = obj.getString("fechaCreacion")
            )
        }
    }

    @TypeConverter
    fun fromLoteCostoDirectoList(value: List<LoteCostoDirectoRaw>): String {
        val array = JSONArray()
        value.forEach { item ->
            array.put(
                JSONObject().apply {
                    put("idCostoDirecto", item.idCostoDirecto)
                    put("concepto", item.concepto)
                    put("monto", item.monto)
                    put("observaciones", item.observaciones ?: JSONObject.NULL)
                    put("fechaCreacion", item.fechaCreacion)
                }
            )
        }
        return array.toString()
    }

    @TypeConverter
    fun toLoteCostoDirectoList(value: String): List<LoteCostoDirectoRaw> {
        if (value.isBlank()) return emptyList()
        val array = JSONArray(value)
        return List(array.length()) { i ->
            val obj = array.getJSONObject(i)
            LoteCostoDirectoRaw(
                idCostoDirecto = obj.getString("idCostoDirecto"),
                concepto = obj.getString("concepto"),
                monto = obj.getDouble("monto"),
                observaciones = if (obj.isNull("observaciones")) null else obj.getString("observaciones"),
                fechaCreacion = obj.getString("fechaCreacion")
            )
        }
    }

    @TypeConverter
    fun fromLoteProduccionIndividualList(value: List<LoteProduccionIndividualRaw>): String {
        val array = JSONArray()
        value.forEach { item ->
            array.put(
                JSONObject().apply {
                    put("idProduccionAnimal", item.idProduccionAnimal)
                    put("idAnimal", item.idAnimal)
                    put("litros", item.litros)
                    put("estado", item.estado)
                }
            )
        }
        return array.toString()
    }

    @TypeConverter
    fun toLoteProduccionIndividualList(value: String): List<LoteProduccionIndividualRaw> {
        if (value.isBlank()) return emptyList()
        val array = JSONArray(value)
        return List(array.length()) { i ->
            val obj = array.getJSONObject(i)
            LoteProduccionIndividualRaw(
                idProduccionAnimal = obj.getString("idProduccionAnimal"),
                idAnimal = obj.getString("idAnimal"),
                litros = obj.getDouble("litros"),
                estado = obj.getString("estado")
            )
        }
    }
}
