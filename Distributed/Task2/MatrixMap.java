package org.myorg;

import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MatrixMap {
    public static void main(String[] args) throws IOException, IllegalFormatException {
        try {
            map();
        } catch (Throwable throwable) {
            System.out.println("-1\t" + throwable.toString().replace('\t', ' ').replace("\n", "\\n"));
        }
    }

    public static void map() {
        String line;
        Scanner scanner = new Scanner(System.in);

        boolean run = true;
 
        while (run) {
            try {
                line = scanner.nextLine();
            } catch (NoSuchElementException e) {
                run = false;
                break;
            }
            String[] tokens = line.split(", | |,|A, ", 0);
            if (tokens.length != 3) {
                continue;
            }
            if (tokens[0].length() == 0) {
                continue;
            }
            int row = Integer.parseInt(tokens[0]);
            int column = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            System.out.println(row + "\t" + column + "," + value);
        }
        System.out.flush();
    }
}
