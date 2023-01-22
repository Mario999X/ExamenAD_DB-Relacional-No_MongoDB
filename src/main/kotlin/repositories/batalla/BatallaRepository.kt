package repositories.batalla

import db.MongoDbManager
import models.Batalla
import org.litote.kmongo.deleteOneById
import org.litote.kmongo.findOneById
import org.litote.kmongo.getCollection
import org.litote.kmongo.save
import repositories.CrudRepository

class BatallaRepository : CrudRepository<Batalla, String> {
    override fun findAll(): List<Batalla> {
        println("findAll")
        return MongoDbManager.database.getCollection<Batalla>().find().toList()
    }

    override fun update(entity: Batalla): Batalla {
        println("update")
        MongoDbManager.database.getCollection<Batalla>().save(entity)
        return entity
    }

    override fun delete(entity: Batalla): Boolean {
        println("delete $entity")
        return MongoDbManager.database.getCollection<Batalla>().deleteOneById(entity.id).wasAcknowledged()
    }

    override fun save(entity: Batalla): Batalla {
        println("save $entity")
        MongoDbManager.database.getCollection<Batalla>().save(entity)
        return entity
    }

    override fun findById(id: String): Batalla? {
        println("find $id")
        return MongoDbManager.database.getCollection<Batalla>().findOneById(id)
    }
}