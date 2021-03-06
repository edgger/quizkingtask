package com.github.edgarzed.kingtask;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        int size = readInt();
        long[][] sumMatrix = new long[size][size];
        fillMatrix(sumMatrix);

        int requestsAmt = readInt();
        if (requestsAmt == 0) {
            return;
        }

        for (int i = 0; i < requestsAmt; i++) {
            if (readInt() == 1) {
                System.out.println(calculateSum(sumMatrix, readInt(), readInt(), readInt(), readInt()));
            } else {
                modify(sumMatrix, readInt(), readInt(), readInt());
            }
        }
    }

    private static void modify(long[][] sumMatrix, int y, int x, int newValue) {
        long originalVal = y > 0 ? sumMatrix[x][y] - sumMatrix[x][y - 1] : sumMatrix[x][y];
        long diff = originalVal - newValue;

        for (int j = y; j < sumMatrix.length; j++) {
            if (j > 0) {
                sumMatrix[x][j] = sumMatrix[x][j] - diff;
            } else {
                sumMatrix[x][j] = newValue;
            }
        }
    }

    private static void fillMatrix(long[][] sumMatrix) throws IOException {
        for (int i = 0; i < sumMatrix.length; i++) {
            for (int j = 0; j < sumMatrix.length; j++) {
                int value = readInt();
                if (j > 0) {
                    sumMatrix[i][j] = sumMatrix[i][j - 1] + value;
                } else {
                    sumMatrix[i][j] = value;
                }
            }
        }
    }

    private static int readInt() throws IOException {
        int result = 0;
        boolean digital = false;
        boolean negative = false;
        for (int ch; (ch = System.in.read()) != -1; ) {
            if (ch >= '0' && ch <= '9') {
                digital = true;
                result = result * 10 + ch - '0';
            } else if (ch == '-') {
                negative = true;
            } else if (digital) {
                break;
            }
        }
        return negative ? result * -1 : result;
    }

    private static long calculateSum(long[][] sumMatrix, int y1, int x1, int y2, int x2) {
        long res = 0;
        for (int i = x1; i <= x2; i++) {
            if (y1 > 0) {
                res += sumMatrix[i][y2] - sumMatrix[i][y1 - 1];
            } else {
                res += sumMatrix[i][y2];
            }
        }
        return res;
    }
}