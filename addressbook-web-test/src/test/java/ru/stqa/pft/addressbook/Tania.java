package ru.stqa.pft.addressbook;

import java.io.File;
import java.lang.reflect.Array;

public class Tania {
    public static void main(String[] args) {
        double t = 5;
        System.out.println("������� �������� �� �������� " + t + " = " + area(t));

        double a = 4;
        double b = 6;
        System.out.println("������� �������������� �� ��������� " + a + " � " + b +" = "+ area(a,b));
    }


    public static double area(double len) {
        return len * len;
    }

    public static double area(double a, double b) {
        return a*b;
    }
}
