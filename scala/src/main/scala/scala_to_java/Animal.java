package scala_to_java;

// java
interface Animal {
  void speak();
}

interface Wagging {
  void wag();
}

interface Running {
  // an implemented method
  default void run() {
    System.out.println("I’m running");
  }
}