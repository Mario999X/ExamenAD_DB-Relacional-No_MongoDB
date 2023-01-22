package repositories.piloto

import db.MongoDbManager
import models.Piloto
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import repositories.CrudRepository
import org.litote.kmongo.getCollection
import org.litote.kmongo.save

class PilotoRepository : CrudRepository<Piloto, String> {

    override fun findAll(): List<Piloto> {
        println("findAll")
        return MongoDbManager.database.getCollection<Piloto>().find().toList()
    }

    override fun delete(entity: Piloto): Boolean {
        println("delete $entity")
        return MongoDbManager.database.getCollection<Piloto>().deleteOneById(entity.id).wasAcknowledged()
    }

    override fun save(entity: Piloto): Piloto {
        println("save $entity")
        MongoDbManager.database.getCollection<Piloto>().save(entity)
        return entity
    }

    override fun findById(id: String): Piloto? {
        println("find $id")
        return MongoDbManager.database.getCollection<Piloto>().findOneById(id)
    }

}