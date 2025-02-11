import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Date
import scala.concurrent.duration.Duration.Zero.fromNow

object Utils {
  def mp[T](value: T): Unit = {
    println(s"$value - 类型: ${value.getClass.getSimpleName}")
  }

}
