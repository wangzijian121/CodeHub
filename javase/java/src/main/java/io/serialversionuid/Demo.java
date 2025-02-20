package io.serialversionuid;

import java.io.Serializable;

/**
 * @author zijian Wang
 */
public class Demo implements Serializable {

    private final static long serialVersionUID = 42L;

    public static void main(String[] args) {
        Demo demo =new Demo();
        try {
            Thread.sleep(10000000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
