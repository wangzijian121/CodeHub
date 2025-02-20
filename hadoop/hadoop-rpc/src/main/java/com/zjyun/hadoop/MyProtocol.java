package com.zjyun.hadoop;

import java.io.IOException;

/**
 * @author Wang ZiJian
 */
public interface MyProtocol {

    public static final long versionID = 1L;

    void mkdir(String path) throws IOException;
}
