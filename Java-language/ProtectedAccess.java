package krishna;

 public class ProtectedAccess {
    public void show() {
        System.out.println("This is a Protected Access outside");
    }

    
}
class A extends ProtectedAccess{
    public static void main(String args[]) {
        // ProtectedAccess ob = new ProtectedAccess();

        //Now you can acces by own class obbj
       A ob = new A();
        ob.show(); 
    }
}
