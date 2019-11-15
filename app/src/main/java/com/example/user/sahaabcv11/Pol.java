package com.example.user.sahaabcv11;

import java.util.Random;

public class Pol {
    int[] x;
    int[] y;

    Pol(int n, int a, int b){
        Random random = new Random();
        x=new int[n];
        y=new int[n];
        for(int i=0;i<n;i++) {
            int x1 = random.nextInt(8);
            int y1 = random.nextInt(8);
            boolean r = true;
            while (r) {
                for (int j = 0; j <= i; j++) {
                    if (Math.abs(8 - x1) < a || (Math.abs(x1 - x[j]) < a && Math.abs(y1 - y[j]) < b)) {
                        x1 = random.nextInt(8);
                        y1 = random.nextInt(8);
                        break;
                    }
                    if (i == j) {
                        r = false;
                        x[i] = x1;
                        y[i] = y1;
                    }
                }
            }
        }
    }

}
