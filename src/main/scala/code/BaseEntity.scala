package code

import java.sql.Timestamp
import org.squeryl.KeyedEntity

class BaseEntity(val id: Long) extends KeyedEntity[Long] {

  def this() = this(0);

  var lastModified = new Timestamp(System.currentTimeMillis)

}
