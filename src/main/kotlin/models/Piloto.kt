package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.time.LocalDate

data class Piloto(
    @BsonId
    val id: String = newId<Piloto>().toString(),
    var nombre: String,
    var planetaOrigen: String,
    val fechaIncorporacion: LocalDate,
    val experiencia: Int = LocalDate.now().year.minus(fechaIncorporacion.year),
    var navePilotada: Nave,
    val capitan: Boolean
) {
    override fun toString(): String {
        return "Piloto(id='$id', nombre='$nombre', planetaOrigen='$planetaOrigen', fechaIncorporacion=$fechaIncorporacion, experiencia=$experiencia, navePilotada=$navePilotada, capitan=$capitan)"
    }
}