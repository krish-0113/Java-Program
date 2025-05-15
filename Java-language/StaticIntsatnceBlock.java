class StaticInstanceBlock {
    int a = 10, b = 20;  // Instance variables

    {
        System.out.println(a + "" + b);  // Instance initialization block
    }

    public static void main(String args[]) {
        StaticInstanceBlock ob = new StaticInstanceBlock();  // Object creation
    }
}
