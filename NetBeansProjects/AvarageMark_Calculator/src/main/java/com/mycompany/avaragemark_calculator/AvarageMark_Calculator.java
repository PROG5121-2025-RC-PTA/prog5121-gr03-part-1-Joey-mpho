/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.avaragemark_calculator;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class AvarageMark_Calculator {

    public static void main(String[] args) {
        Scanner myScanner=  new Scanner(System.in);
        System.out.println("Enter first mark");
        int mark1= myScanner.nextInt();
        System.out.println("Enter second mark");
        int mark2= myScanner.nextInt();
        System.out.println("Enter third mark");
        int mark3 = myScanner.nextInt();
        double avarage = AvarageCalculator( mark1, mark2, mark3);
        CheckPassMark(avarage);
    }
    public static double AvarageCalculator(int num1, int num2, int num3){
    double avarage= (num1 + num2 + num3)/3;//calculate the avarage
        System.out.println("The avarage mark is " + avarage);
        return avarage;// you return the avarage
    }
    public static void CheckPassMark(double totalAvarage){ //Method inside the main method to check the passmark or avarage 
    if (totalAvarage>=50)
        
            System.out.println("Student passed");
    else
        System.out.println("Student failed, pass mark not reached");
    }
    
}
