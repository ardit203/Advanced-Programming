package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Anonymous_Classes;


interface Operation {
    int apply(int a, int b);
}

class Addition implements Operation {

    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}


interface MessageProvider {
    public String getMessage();
}

class StaticMessage implements MessageProvider {

    @Override
    public String getMessage() {
        return "Hello from regular class!";
    }
}

public class InterfaceDemo {
    static void main() {
        Operation op1 = new Addition();
        System.out.println("Addition = " + op1.apply(5, 6));

        //-------------------------------------
        Operation op2 = new Operation() {
            @Override
            public int apply(int a, int b) {
                return a * b;
            }
        };

        System.out.println("Multiplication = " + op2.apply(5, 6));

        //-------------------------------------

        Operation op3 = (a, b) -> a - b;

        System.out.println("Substraction = " + op3.apply(5, 6));

        //-------------------------------------

        MessageProvider ms1 = new StaticMessage();
        System.out.println(ms1.getMessage());

        //--------------------------------------

        MessageProvider ms2 = new MessageProvider() {
            @Override
            public String getMessage() {
                return "Hello from anonymous class!";
            }
        };
        System.out.println(ms2.getMessage());
        //--------------------------------------------

        MessageProvider ms3 = () -> "Hello from lambda!";
        System.out.println(ms3.getMessage());

    }
}
