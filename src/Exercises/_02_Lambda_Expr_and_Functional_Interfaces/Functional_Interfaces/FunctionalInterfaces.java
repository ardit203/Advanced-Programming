package Exercises._02_Lambda_Expr_and_Functional_Interfaces.Functional_Interfaces;

import java.util.function.*;

public class FunctionalInterfaces {

    public static Integer stringLenNormal(String str) {
        return str.length();
    }

    public static String concatNormal(String str1, String str2) {
        return str1 + str2;
    }

    static void main() {
        Function<String, Integer> stringLen = (str) -> str.length();

        System.out.printf("Normal: %d -------- Function: %d\n",
                stringLenNormal("Hello"),
                stringLen.apply("hello"));

        //---------------------------------------------------------------------------------

        BiFunction<String, String, String> concat = (str1, str2) -> str1 + str2;
        System.out.printf("Normal: %s -------- Function: %s\n",
                concatNormal("Hello", "World"),
                concat.apply("Hello", "World"));

        //---------------------------------------------------------------------------------

        Predicate<Integer> isEven = (x) -> x % 2 == 0;
        System.out.println("Is 5 even: " + isEven.test(5));
        System.out.println("Is 4 even: " + isEven.test(4));

        //---------------------------------------------------------------------------------
        Consumer<String> printString = str -> System.out.println("Printing: " + str);
        printString.accept("Hello, World!");

        // -------------------------------------------------------------------------------
        Supplier<Long> currentTimeMillis = () -> System.currentTimeMillis();
        System.out.println("Current time in milliseconds: " + currentTimeMillis.get());
    }

}
