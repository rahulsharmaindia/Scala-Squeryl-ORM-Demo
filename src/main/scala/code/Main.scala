package code

import java.sql.Timestamp

/*import org.squeryl._
import PrimitiveTypeMode.{inTransaction, _}
import org.squeryl.adapters.PostgreSqlAdapter*/
import org.squeryl._
import adapters._
import dsl._
import org.squeryl.PrimitiveTypeMode._
import java.util.Calendar
import org.scalatest.Matchers



object Main {
  def main(args: Array[String]) {
    Class.forName("org.postgresql.Driver");
    SessionFactory.concreteFactory = Some(()=>
      Session.create(
        java.sql.DriverManager.getConnection("jdbc:postgresql://localhost:5432/orders", "pos", "pos"),
        new PostgreSqlAdapter)
    )

    inTransaction {
      import Library._

      drop // Bad idea in production application!!!!
      create
      printDdl

      authors.insert(new Author(1, "JRR", "Tolkien", None))
      authors.insert(new Author(2, "Jane", "Austen", None))
      authors.insert(new Author(3, "Philip", "Pullman", None))
      books.insert(new Book( "The Lord of the Rings", 1, None))
      books.insert(new Book( "Pride and Prejudice", 2, None))
      books.insert(new Book( "His Dark Materials", 3, None))

      val q = from(Library.books)((b) =>
        select(b)
      )

      for ((book) <- q) {
        println(" wrote " + book.title)
      }
    }
  }
}

class Author(val id: Long,
             val firstName: String,
		         val lastName: String,
		         val email: Option[String]) extends KeyedEntity[Long]

class MusicDbObject extends KeyedEntity[Long] {
  val id: Long = 0
  var timeOfLastUpdate = new Timestamp(System.currentTimeMillis)
}
@SerialVersionUID(7397250366604823353L)
class Book(var title: String,
           var authorId: Long,
           val coAuthorId: Option[Long]) extends MusicDbObject with java.io.Serializable {

  def this() = this("default title",0L,None)
}

object Library extends Schema {
  val authors = table[Author]
  val books = table[Book]
}
