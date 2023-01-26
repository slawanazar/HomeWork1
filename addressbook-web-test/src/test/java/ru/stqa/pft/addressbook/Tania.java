package ru.stqa.pft.addressbook;

import java.io.File;
import java.lang.reflect.Array;

public class Tania {
    public static void main(String[] args) {
        double t = 5;
        System.out.println("Площадь квадрата со стороной " + t + " = " + area(t));

        double a = 4;
        double b = 6;
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b +" = "+ area(a,b));
    }


    public static double area(double len) {
        return len * len;
    }

    public static double area(double a, double b) {
        return a*b;
    }
}
