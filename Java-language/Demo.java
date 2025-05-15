// class Mobile {
//     static String name;
//     String brand;
//     int price;

//     public static void show(Mobile ob) {
//         System.out.println(ob.name + "  : " + ob.brand + " : " + ob.price);
//     }
//     //  public void show1() {
//     //     System.out.println(name + "  : " + brand + " : " + price);
//     // }
// }

// public class Demo {
//     public static void main(String args[]) {
//         Mobile obj = new Mobile();
//         obj.name = "SmartPhone";
//         obj.brand = "Apple";
//         obj.price = 222;

//         Mobile obj1 = new Mobile();
//         obj1.name = "Android";
//         obj1.brand = "Samasung";
//         obj1.price = 111;

//         Mobile.name = "iphone";
//         obj.show();
//         // obj1.show1();
//         Mobile.show(obj1);
//     }
// }


 // Enccapsulation
// class Demo1{
//     private int a;// date hidding

//     public void setValue(int x){ // data abstraction 
//        a = x;
//     }
    
//     public int getValue(){
//        return a*a;
//     }
    
// }
// public class Demo{
//     public static void main(String args[]){
//       Demo1 obj = new Demo1();
//       obj.setValue(100);
//       System.out.println(obj.getValue());
//     }
// }


// // Abstract Class
// abstract class Animal {
  
//    abstract void show(); // we dont implemetnts thgis method beacisue of this method domt knwo for what imple for 
// //    public void info(){
// //     System.out.println("This is info to for all ");
// //    }

//    Animal(){
//     System.out.println("This is a constructor ");
//  }
// }

// class Dog extends Animal {
   
//     Dog(){
//         super();
//     }
//     void show() {
//         System.out.println("This is a Dog");
//     }
// }

// class Tiger extends Animal {
    
//     Tiger(){
//         super();
//     }
//     void show() {
//         System.out.println("This is a Tiger");
//     }
// }

// public class Demo {
//     public static void main(String args[]) {

//         Animal obj = new Dog();
//         obj.show(); 
//         // obj.info();

        
//         obj = new Tiger();
//         obj.show();  
//         // obj.info();
//     }
// }


//Abstract Methods

// 
// interface 
// import java.util.*;

// interface Client {
//     void input();
//     void output();
// }


// class Krishna implements Client {
//     int sal;
//     String name;

//     public void input() {
//         Scanner sc = new Scanner(System.in);
//         System.out.println("Enter User Name:");
//         name = sc.nextLine(); 

//         System.out.println("Enter Your Salary:");
//         sal = sc.nextInt();
//     }

//     public void output() {
//         System.out.println("Name: " + name + ", Salary: " + sal);
//     }

    
//     public static void main(String args[]) {
//         Client obj = new Krishna(); 
//         obj.input(); 
//         obj.output(); 
//     }
// }


//Multiple Inheritance using inteface 

// interface A{
//    void show();
// }

// interface B{
//    void display();
// }

// class Demo implements A,B{
//    public void show(){
//    System.out.println("interface A");
//    }
//    public void display(){
//    System.out.println("interface B");
//    }
//    public static void main(String args[]){
//      Demo ob = new Demo();
//      ob.show(); ob.display();
//    }
// }

//This keyword
// class Demo{
//    public  void show(){
//       System.out.println(this);
//    }

//    public static void main(String args[]){
//     Demo ob = new Demo();
//     System.out.println(ob);
//     ob.show();
//    }
// }

// class Demo{
//    int a =10;
//    Demo(int a){
//      this.a = a;
//    }

//    public static void main(String args[]){
//     Demo ob = new Demo(1333);
//     System.out.print(ob.a);
   
//    }
// // }
  

//  // call a default constrcuture
// class Demo{
//    int a =10;
//    Demo(){
//       System.out.println("This is not parameterrized coonstrcutor");
//    }
//    Demo(int a){
//       this();
//       System.out.println("This is parameterrized coonstrcutor");
//     }

//    public static void main(String args[]){
//     Demo ob = new Demo(1333);
//     System.out.print(ob.a);
   
//    }
// }


// 


import java.util.*;
import java.io.File;
import java.io.IOException;

class Demo {

   public static void main(String a[]) {

      try {
         
         File r = new File("C:\\Users\\ASUS\\OneDrive\\Documents\\Desktop\\fileReader.txt");
         Scanner sc = new Scanner(r);

         
         while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
         }

        

      } catch (IOException e) {
         System.out.println("Something went wrong: " + e.getMessage());
      }
   }
}
