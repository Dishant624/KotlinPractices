package com.dishant.kotlinpractices.coreKotlin.delegate.explicitDelegateInJava;

public class DelegationExample2 {

    public static void main(String[] args){

        View1 view1 = new View1();
        CustomView1 customView1 = new CustomView1();

        Screen1 screen1 = new Screen1(view1);
        Screen1 screen2 = new Screen1(customView1);

        screen1.show();
        screen2.show();

    }
}

interface Showable{
    void show();
}

class View1 implements Showable{

    @Override
    public void show() {
        System.out.println("View1.Show()");
    }
}

class CustomView1 implements Showable{

    @Override
    public void show() {
        System.out.println("CustomView1.show()");
    }
}

class Screen1 implements Showable{

    private final Showable showable;

    public Screen1(Showable showable) {
        this.showable = showable;
    }

    @Override
    public void show() {
        showable.show();
    }
}
