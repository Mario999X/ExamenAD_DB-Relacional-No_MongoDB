package models

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.time.LocalDate

data class Nave(
    @BsonId
    val id: String = newId<Nave>().toString(),
    val tipoNave: TipoNave,
    val fechaAlta: LocalDate,
    val misilesProtonicos: Int,
    val saltoHiperEspacio: Boolean
){
    enum class TipoNave{ W_WIND, T_FIGHTER }

    override fun toString(): String {
        return "Nave(id='$id', tipoNave=$tipoNave, fechaAlta=$fechaAlta, misilesProtonicos=$misilesProtonicos, saltoHiperEspacio=$saltoHiperEspacio)"
    }

}
