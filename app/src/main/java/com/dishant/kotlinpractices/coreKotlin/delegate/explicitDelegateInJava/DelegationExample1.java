package com.dishant.kotlinpractices.coreKotlin.delegate.explicitDelegateInJava;

//website ref link : https://proandroiddev.com/delegation-in-kotlin-e1efb849641


public class DelegationExample1 {
    public static void main(String[] arg) {

        View view = new View();
        Screen screen = new Screen(view);
        screen.show();


        View customView = new CustomView();
        Screen screen1 = new Screen(customView);
        screen1.show();


    }
}
class View {
    void show() {
        System.out.println("View.Show()");
    }

}

class Screen {

    private final View view;

    public Screen(View view) {
        this.view = view;
    }

    void show() {
        view.show();
    }
}

class CustomView extends View{

    @Override
    void show() {
        super.show();
        System.out.println("CustomView show()");
    }
}



