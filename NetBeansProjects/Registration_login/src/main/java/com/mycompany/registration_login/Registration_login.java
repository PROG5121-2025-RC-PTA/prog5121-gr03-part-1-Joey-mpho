/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.registration_login;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Registration_login {

    public static void main(String[] args) 
    {
        Scanner loginDetail = new Scanner(System.in);
        
        System.out.println("Enter your first name");
        String firstName = loginDetail.nextLine();
        System.out.println("Enter your last name");
        String lastName = loginDetail.nextLine(); 
        System.out.println("Enter username");
        String userName = loginDetail.nextLine();
        System.out.println("Enter password");
        String password = loginDetail.nextLine();
        System.out.println("Enter cell phone number");
        String cellPhoneNumber = loginDetail.nextLine();
        
        registerUser(userName, password, cellPhoneNumber );
        returnLoginStatus(userName,password,firstName, lastName);
   }
   public static boolean checkUserName(String userName)
   {
      if(userName.matches("^(?=.*_)[A-Za-z0-9_]{1,5}$"))
        {
            System.out.println("Username successfully captured.");
          return true; 
        }
      else
        {
           System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
           return false;

        }
    }
   public static boolean checkPasswordCompelexity(String password)
   {
       if(password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$"))//OpenAI.(2025).ChatGPT(GPT-4)[Large language model].  https://chat.openai.com/"
       {
           System.out.println("Password successfully captured");
          return true;
        }
       else
       {
           System.out.println("Password is not correctly formatted, please ensure that password contains at least eight characters, a capital letter,a number and a special character.");
           return false;
               
        }
   }
      public static boolean checkCellPhoneNumber(String phone)
      {
          if(phone.matches("^(?:\\+27)(6|7|8)[0-9]{8}$"))//OpenAI.(2025).ChatGPT(GPT-4)[Large language model].  https://chat.openai.com/"
          {
              System.out.println("Cell phone number successfully added.");
              return true;
          }
          else
          {
             System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
          return false;
          }
      
   }
      public static void registerUser(String userName, String password, String phone)
      {
        checkUserName(userName);
        checkPasswordCompelexity(password);
        checkCellPhoneNumber(phone);
      
      }
      
      
      public static boolean loginUser(String userName, String password)
      {
        boolean validateUser = checkUserName(userName);
        boolean validatePassword = checkPasswordCompelexity(password);
       
        return validateUser ==true && validatePassword ==true;
      }
      
      public static void returnLoginStatus(String userName,String password, String firstName, String lastName)
      {
        if(loginUser(userName, password))
        {
           System.out.println("Welcome "+ firstName +" "+ lastName + " it is great to see you.");
           
        }
        else
        {
            System.out.println("Username or password is incorrect, please try again.");
            
        }
      }
}
            
      
      

      
      

    

    

