package ru.stqa.pft.sandbox;

public class MyFirstProgram {
 public static void main(String[] args) {
 	System.out.println("Hello, World");
   System.out.println(2+2);

   Square s = new Square(5);
   System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

   Rectangle r = new Rectangle(4,6);

   System.out.println("Площадь прямоугольника со сторонами" + r.a + " и " + r.b + " = " + r.area());
 }
}

