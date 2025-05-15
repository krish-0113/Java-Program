package krishna;

class PrivateAccess {
    private void show() {
        System.out.println("This is a private space");
    }

    
}
class A{
    public static void main(String args[]) {
        PrivateAccess ob = new PrivateAccess();
        ob.show(); 
    }
}
