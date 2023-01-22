import controller.ExamenController
import db.MongoDbManager
import db.getBatallasInit
import db.getNavesInit
import db.getPilotosInit
import models.Batalla
import models.Piloto
import org.litote.kmongo.getCollection
import repositories.batalla.BatallaRepository
import repositories.piloto.PilotoRepository

// -- Version Embebida --

/*
-- ENUNCIADO (segun recuerdo) --
R2D2 crea una BBDD usando JPA + Hibernate y en ella almacena tres entidades: naves, pilotos y batallas
    - Naves: identificador, tipo de nave (X-WIND, T-FIGHTER), fecha de alta, misiles protonicos y salto hiper espacial.
    - Piloto: Identificador, nombre, planeta de origen, fecha incorporacion, experiencia.
    - Batalla: Identificador, planeta, fecha
Teniendo en cuenta lo anterior, se agregan los siguientes datos:
Una nave solo puede pertenecer a un piloto y un piloto solo puede tener una nave.
En una batalla pueden participar muchos pilotos, y ellos solo podian participar en 1, y como maximo uno de ellos puede ser capitan
Se pedia:
    - Plantear las relaciones entre cada entidad, preparar los modelos con las notaciones respectivas.
    - Realizar las operaciones CRUD de la BBDD (repositorios, controlador y main); solo 1 controlador.
    - Realizar los test del controlador y repositorios

    -- Version parecida con Mongo -- Usando Docker

    En este caso, voy a embeber las naves en la coleccion de pilotos, a su vez, voy a embeber los pilotos en las batallas.

Tiempo para resolverlo: 4h:30min
 */
fun main() {
    limpiarDatos()

    // Controllador
    val controller = ExamenController(PilotoRepository(), BatallaRepository())

    // Insercion de datos
    val navesInit = getNavesInit()
    val pilotosInit = getPilotosInit()
    val batallasInit = getBatallasInit()
    val pilotos = mutableListOf<Piloto>()

    pilotosInit.forEachIndexed { index, it ->
        it.navePilotada = navesInit[index]
        pilotos.add(it)
    }

    batallasInit.forEach {
        it.pilotosInvolucrados = pilotos
    }

    // Insertamos a los pilotos
    pilotos.forEach {
        controller.createPiloto(it)
    }

    // Insertamos las batallas
    batallasInit.forEach {
        controller.createBatalla(it)
    }

    // Obtenemos a todos los pilotos
    val pilotosExistentes = controller.getPilotos()
    pilotosExistentes.forEach {
        println(it)
    }

    // Obtenemos las batallas
    val batallasExistentes = controller.getBatallas()
    println(batallasExistentes)

    // Obtengo a un piloto en especifico
    println(controller.getPilotoById(pilotosExistentes[0].id))

    // Elimino a un piloto
    println(controller.deletePiloto(pilotosExistentes[0]))

    // Vuelvo a mostrar los pilotos ordenados por experiencia
    println(controller.getPilotos().sortedByDescending { it.experiencia })

}

private fun limpiarDatos() {
    if (MongoDbManager.database.getCollection<Piloto>().countDocuments() > 0) {
        MongoDbManager.database.getCollection<Piloto>().drop()
    }
    if (MongoDbManager.database.getCollection<Batalla>().countDocuments() > 0) {
        MongoDbManager.database.getCollection<Batalla>().drop()
    }
}