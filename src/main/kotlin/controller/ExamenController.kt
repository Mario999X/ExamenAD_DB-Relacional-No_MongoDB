package controller

import models.Batalla
import models.Piloto
import mu.KotlinLogging
import repositories.batalla.BatallaRepository
import repositories.piloto.PilotoRepository

private val log = KotlinLogging.logger {}

class ExamenController(
    private val pilotoRepository: PilotoRepository,
    private val batallaRepository: BatallaRepository
) {
    // PILOTOS
    fun getPilotos(): List<Piloto> {
        log.info("getPilotos")
        return pilotoRepository.findAll()
    }

    fun createPiloto(piloto: Piloto): Piloto {
        log.info("createPiloto")
        pilotoRepository.save(piloto)
        return piloto
    }

    fun getPilotoById(id: String): Piloto? {
        log.info("getPilotoById: $id")
        return pilotoRepository.findById(id)
    }

    fun updatePiloto(piloto: Piloto) {
        println("updatePiloto: $piloto")
        pilotoRepository.save(piloto)
    }

    fun deletePiloto(it: Piloto): Boolean {
        log.info("deletePiloto")
        return pilotoRepository.delete(it)
    }

    // BATALLAS
    fun getBatallas(): List<Batalla> {
        log.info("getBatallas")
        return batallaRepository.findAll()
    }

    fun createBatalla(batalla: Batalla): Batalla {
        log.info("createBatalla")
        var capitan = 0
        batalla.pilotosInvolucrados.forEach {
            if (it.capitan) {
                capitan++
            }
        }
        if (capitan >= 2) {
            System.err.println("Error en la creacion de batalla ${batalla.id} | Exceso de capitanes($capitan)")
        } else batallaRepository.save(batalla)
        return batalla
    }

    fun getBatallaById(id: String): Batalla? {
        log.info("getBatallaById: $id")
        return batallaRepository.findById(id)
    }

    fun updateBatalla(batalla: Batalla): Batalla {
        log.info("updateBatalla: $batalla")
        var capitan = 0
        batalla.pilotosInvolucrados.forEach {
            if (it.capitan) {
                capitan++
            }
        }
        if (capitan >= 2) {
            System.err.println("Error en la actualizacion de batalla ${batalla.id} | Exceso de capitanes($capitan)")
        } else batallaRepository.save(batalla)
        return batalla
    }

    fun deleteBatalla(it: Batalla): Boolean {
        log.info("deleteBatalla")
        return batallaRepository.delete(it)
    }
}