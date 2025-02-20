package com.example.java.lang;

import org.junit.Test;

public class StringBufferAndStringBuilder {
    private static final int N = 1000000;

    @Test
    public void test() {
        long t;

        {
            StringBuffer sb = new StringBuffer();
            t = System.currentTimeMillis();
            for (int i = N; i-- > 0; ) {
                sb.append("");
            }
            System.out.println(System.currentTimeMillis() - t);
        }

        {
            StringBuilder sb = new StringBuilder();
            t = System.currentTimeMillis();
            for (int i = N; i-- > 0; ) {
                sb.append("");
            }
            System.out.println(System.currentTimeMillis() - t);
        }
    }
}