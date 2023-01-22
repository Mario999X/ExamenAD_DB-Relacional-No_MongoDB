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
        batallaRepository.save(batalla)
        return batalla
    }

    fun getBatallaById(id: String): Batalla? {
        log.info("getBatallaById: $id")
        return batallaRepository.findById(id)
    }

    fun deleteBatalla(it: Batalla): Boolean {
        log.info("deleteBatalla")
        return batallaRepository.delete(it)
    }
}