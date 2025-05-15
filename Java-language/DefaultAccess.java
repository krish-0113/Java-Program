package krishna;

class DefaultAccess {
     void show() {
        System.out.println("This is a Default Access");
    }

    
}
class A{
    public static void main(String args[]) {
        DefaultAccess ob = new DefaultAccess();
        ob.show(); 
    }
}
