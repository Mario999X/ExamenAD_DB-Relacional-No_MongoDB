package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.time.LocalDate

data class Batalla(
    @BsonId
    val id: String = newId<Batalla>().toString(),
    val planeta: String,
    val fecha: LocalDate,
    var pilotosInvolucrados: MutableList<Piloto> = mutableListOf()
) {
    override fun toString(): String {
        return "Batalla(id='$id', planeta='$planeta', fecha=$fecha, pilotosInvolucrados=\n$pilotosInvolucrados)"
    }
}