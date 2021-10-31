public class main
{
    public static void main(String[] args)
    {
        B b = new B();
        //b.a2();
        A a = b;
        //a.a1();
        //C c = new C();
        
        
        C cd = new D();
        D dd = (D)cd;
        // dd
        dd.foo(dd);
        
        // dc
        dd.foo(cd);
        
        // dd
        cd.foo(dd);
        
        // compile error
        //cd.foo(cd);
    }
    
    
}
class A
{
    public int _x = 100;
    public void a1()
    {
        System.out.println("A");
    }
    public void a2()
    {
        a1();
    }
    private void f()
    {}
}
class B extends A
{
    public void a1()
    {
        System.out.println("B");
    }
    protected void f()
    {}
}
class C2 extends B
{
    public C2()
    {
        System.out.println(this._x);
    }
}
class Overloading{
    public void myFunc(int x, double y)
    {
    }
}
class OverloadingEx extends Overloading
{
    public void myFunc(double y, int x)
    {
    }
    public int myFunc(int x)
    {
    }
    public int myFunc(int x, double y)
    {
    }
    public void myFunc()
    {
    }
}


