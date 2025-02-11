/**
 * 狗叫！
 */
trait Speak {
  def sound: String

  def speakStart() = println(s"Speaking!! $sound")

  def speakStop() = println(s"Speak stopped $sound")
}

/**
 * 狗跑
 */
trait Running {
  def runStart(name: String) = println(s"$name 在跑!! ")

  def runStop(name: String) = println(s"$name 不跑了！")
}

class Dog(name: String, s: String = "汪汪叫！") extends Speak with Running {
  override def sound: String = s

  def runStart(): Unit = super.runStart(name)

  def runStop(): Unit = super.runStop(name)
}


object DogMain {
  def main(args: Array[String]): Unit = {
    val myDog = new Dog("大黄", "嗷嗷叫")
    myDog.speakStart()
    myDog.speakStop()
    println("----")
    
    myDog.runStart()
    myDog.runStop()

  }
}