package code

import java.sql.Timestamp
import org.squeryl._
import adapters._
import org.squeryl.PrimitiveTypeMode._

object Main {
  def main(args: Array[String]) {
    Class.forName("org.h2.Driver")
    SessionFactory.concreteFactory = Some(() =>
      Session.create(
        java.sql.DriverManager.getConnection("jdbc:h2:~/test", "sa", ""),
        new H2Adapter)
    )

    inTransaction {
      import Library._
      drop // Bad idea in production application!!!!
      create
      printDdl

      books.insert(new Book("The Lord of the Rings", 1, None))
      books.insert(new Book("Pride and Prejudice", 2, None))
      books.insert(new Book("His Dark Materials", 3, None))

      val q = from(Library.books)(b =>
        select(b)
      )

      for (book <- q) {
        println(" wrote " + book.title)
      }
    }
  }
}

class MusicDbObject extends KeyedEntity[Long] {
  val id: Long = 0L
  var timeOfLastUpdate = new Timestamp(System.currentTimeMillis)
}

class Book(var title: String,
           var authorId: Long,
           val coAuthorId: Option[Long]) extends MusicDbObject {

  def this() = this("default title", 0L, None)
}

object Library extends Schema {
  val books = table[Book]
}
