package Lab3;

import java.util.Arrays;

public class PageRank {

    public static void pageRank() {
        final int n = 7;
        int[][] graph = {
                {0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 1, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 1},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 1, 0}
        };

        double alpha = 0.85;

        double[][] P = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int si = 0;
                for (int k = 0; k < n; k++)
                    si += graph[i][k];

                double rij = graph[i][j] / (si * 1.0);
                P[i][j] = (1 - alpha) / n + alpha * rij;
            }
        }


        double[] v = {1, 1, 1, 1, 1, 1, 1};
        double eps =1e-20;

        double[] vNext;
        while (true)
        {
            vNext = mulToMatrix(v, P);
            if (allClose(v, vNext, eps)) {
                break;
            }
            v = vNext;
        }

        double sum = sum(vNext);
        for (int i = 0; i < v.length; i++) {
            vNext[i] /= sum;
        }

        System.out.println(Arrays.toString(vNext));
    }

    private static double[] mulToMatrix(double[] v, double[][] P) {
        double[] res = new double[v.length];
        for (int j = 0; j < v.length; j++) {
            for (int i = 0; i < v.length; i++) {
                res[j] += v[i] * P[i][j];
            }
        }

        return res;
    }

    private static boolean allClose(double[] v1, double[] v2, double eps) {
        for (int i = 0; i < v1.length; i++) {
            if (Math.abs(v1[i]-v2[i]) > eps) {
                return false;
            }
        }

        return true;
    }

    private static double sum(double[] ar) {
        double res = 0;
        for (double val : ar) {
            res += val;
        }

        return res;
    }

}
