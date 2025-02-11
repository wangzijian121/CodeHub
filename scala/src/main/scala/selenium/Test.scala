package selenium

import org.openqa.selenium.{By, WebElement}
import org.openqa.selenium.chrome.ChromeDriver

import java.text.SimpleDateFormat
import java.time.Duration
import java.util.Date
import scala.concurrent.duration._
import scala.concurrent.blocking

object Test {

  def main(args: Array[String]): Unit = {
    val driver = new ChromeDriver()
    //    driver.get("https://www.selenium.dev/selenium/web/web-form.html")
    driver.get("https://quote.eastmoney.com/gb/zsNDX.html")
    println(driver.getTitle)
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500))
    //    val textBox = driver.findElement(By.name("my-text"))
    while(true){
      var now: Date = new Date()
      var dateFormat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
      var time = dateFormat.format(now)

      val webElement = driver.findElement(By.className("zxj")).findElements(By.tagName("span"))
      println("【"+webElement.get(0).getText+"】 "+time)
      Thread.sleep(1.seconds.toMillis)
    }

    //    println(webElement)
    //    val submitButton = driver.findElement(By.cssSelector("button"))
    //    textBox.sendKeys("你好我叫王子健！");
    //    submitButton.click()
    driver.quit()


  }
}
