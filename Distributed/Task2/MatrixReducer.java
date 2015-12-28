package org.myorg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class MatrixReducer {

    public static final String FILENAME = "matrixB";

    public static void main(String[] args) throws FileNotFoundException {
        try {
            reduce();
        } catch (Throwable throwable) {
            System.out.println("-1\t" + throwable.toString().replace('\t', ' ').replace("\n", "\\n"));
        }
    }

    private static void reduce() throws IOException {
        int bRowsCount = 3;
        int bColumnsCount = 4;
        Scanner scanner = new Scanner(System.in);

        int[][] matrixB = new int[bRowsCount][bColumnsCount];

        BufferedReader matrixReader = new BufferedReader(new FileReader(FILENAME));
        String line;
        boolean run = true;
        while ((line = matrixReader.readLine()) != null) {
            String[] tokens = line.split(", | |,", 0);
            if (tokens.length != 3) {
                continue;
            }
            int column = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            if (!tokens[0].equals("B")) {
                int row = Integer.parseInt(tokens[0]);
                matrixB[row][column] = value;
            } else {
                bRowsCount = column;
                bColumnsCount = value;
                matrixB = new int[bRowsCount][bColumnsCount];
            }

        }
        matrixReader.close();

        Map<Integer, int[]> rowsA = new TreeMap<Integer, int[]>();
        run = true;
        while (run) {
            try {
                line = scanner.nextLine();
            } catch (NoSuchElementException e) {
                run = false;
                break;
            }
            String[] tokens = line.split("[,\t]");
            if (tokens.length != 3) {
                continue;
            }
            int row = Integer.parseInt(tokens[0]);
            int column = Integer.parseInt(tokens[1]);
            int value = Integer.parseInt(tokens[2]);

            int[] array = rowsA.get(row);
            if (array == null) {
                array = new int[bRowsCount];
                rowsA.put(row, array);
            }
            array[column] = value;
        }

        for (Map.Entry<Integer, int[]> row : rowsA.entrySet()) {
            int[] data = row.getValue();
            Integer key = row.getKey();
            multiplyRow(bRowsCount, bColumnsCount, matrixB, data, key);
        }
        System.out.flush();
    }

    private static void multiplyRow(int bRowsCount, int bColumnsCount, int[][] matrixB, int[] data, Integer key) {
        System.out.print(key + "\t");
        for (int j = 0; j < bColumnsCount; ++j) {
            long sum = 0;
            for (int i = 0; i < bRowsCount; ++i) {
                sum += data[i] * matrixB[i][j];
            }
            if (j > 0) {
                System.out.print(",");
            }
            System.out.print(sum);
        }
        System.out.println();
        System.out.flush();
    }
}
