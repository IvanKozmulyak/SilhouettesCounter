package BFS;

import java.awt.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    private static final String PATH = "assets/";

    public static void main(String[] args) {
        checkCase(new String[]{PATH + "0.png"},            "4");
        checkCase(new String[]{PATH + "1.png"},                     "36");
        checkCase(new String[]{PATH + "2.png"},                   "9");
        checkCase(new String[]{PATH + "3.png"},                 "5");
        checkCase(new String[]{PATH + "4.png"},                 "17");
        checkCase(new String[]{PATH + "5.png"},                 "36");
        checkCase(new String[]{PATH + "6.jpg"},                 "77");
        checkCase(new String[]{PATH + "test.jpg"},                "4");
    }

    private static void checkCase(String[] args, String expected) {
        System.out.println(args[0] + " Expected: " + expected);
        System.out.print(args[0] + "   Actual: ");
        SilhouettesCounter.main(args);
        System.out.println();
    }
}