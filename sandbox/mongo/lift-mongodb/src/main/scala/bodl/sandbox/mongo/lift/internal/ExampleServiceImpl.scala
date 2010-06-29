package bodl.sandbox.mongo.lift.internal

import bodl.sandbox.mongo.lift.ExampleService
import java.util.Date
import org.bson.types.ObjectId
import net.liftweb.mongodb._

case class Address(street: String, city: String)
case class Child(name: String, age: Int, birthdate: Option[Date])
case class Person(_id: String, name: String, age: Int, address: Address, children: List[Child])
    extends MongoDocument[Person] {
  def meta = Person
}
object Person extends MongoDocumentMeta[Person] {
  
  override def mongoIdentifier = DefaultMongoIdentifier

  override def collectionName = "mypersons"
}

class ExampleServiceImpl
    extends ExampleService
{
  def checkMongoIsRunning {
    import com.mongodb.MongoException
    try { MongoDB.use(DefaultMongoIdentifier) ( db => { db.getLastError } ) }
    catch { case e: Exception => throw new RuntimeException("MongoDB is not running")}
    return true
  }

  def date(s: String) = Person.formats.dateFormat.parse(s).get


  def tryIt() = {
    // create a Mongo instance
    val mongoHost = MongoHost("localhost", 27017)
    // define the dbs
    MongoDB.defineDb(DefaultMongoIdentifier, MongoAddress(mongoHost, "test_document"))

    checkMongoIsRunning

    val p = Person(
      ObjectId.get.toString,
      "joe",
      27,
      Address("Bulevard", "Helsinki"),
      List(Child("Mary", 5, Some(date("2004-09-04T18:06:22.000Z"))), Child("Mazy", 3, None))
      )

    println("Created person: " + p)
    
    p.save

    val pFromDb = Person.find(p._id)

    println("Found person: " + pFromDb)

    p.delete

  }

}