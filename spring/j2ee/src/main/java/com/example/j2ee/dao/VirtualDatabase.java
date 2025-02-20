package com.example.j2ee.dao;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zijian Wang
 */
public class VirtualDatabase {

    public final static Map<String, HttpSession> map = new HashMap<>();
}
