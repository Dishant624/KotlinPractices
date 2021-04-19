package com.example.kotlinpractices.coreKotlin.Inheritance;

public class Inheritancejava {
    public static void main(String[] args) {

    }

    class Base {
        int x;

        public Base(int x) {
            this.x = x;
        }
    }

    class Derived extends Base{

        public Derived(int x) {
            super(x);
        }
    }
}
