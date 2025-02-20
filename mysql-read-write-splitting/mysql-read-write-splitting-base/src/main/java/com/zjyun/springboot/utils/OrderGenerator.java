package com.zjyun.springboot.util;

import com.zjyun.springboot.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

public class OrderGenerator {

    private static final Random random = new Random();

    public static Order generateRandomOrder() {
        Order order = new Order();

        order.setOrderId(generateOrderId());
        order.setCustomerId(generateCustomerId());
        order.setOrderDate(generateRandomDateTime());
        order.setDeliveryDate(generateRandomDateTime());
        order.setStatus(generateRandomStatus());
        order.setPaymentStatus(generateRandomPaymentStatus());
        order.setPaymentMethod(generateRandomPaymentMethod());
        order.setTotalAmount(generateRandomTotalAmount());
        order.setDiscount(generateRandomDiscount());
        order.setShippingAddress(generateRandomAddress());
        order.setBillingAddress(generateRandomAddress());
        order.setContactPhone(generateRandomPhoneNumber());
        order.setNote(generateRandomNote());

        return order;
    }

    private static String generateOrderId() {
        return UUID.randomUUID().toString();
    }

    private static Integer generateCustomerId() {
        return random.nextInt(1000); // 随机生成0到999之间的整数作为客户ID
    }

    private static LocalDateTime generateRandomDateTime() {
        return LocalDateTime.now().minusDays(random.nextInt(30)); // 在当前时间的前30天内随机生成日期时间
    }

    private static Integer generateRandomStatus() {
        return random.nextInt(6); // 假设有6种订单状态
    }

    private static Integer generateRandomPaymentStatus() {
        return random.nextInt(3); // 假设有3种支付状态
    }

    private static Integer generateRandomPaymentMethod() {
        return random.nextInt(4); // 假设有4种支付方式
    }

    private static BigDecimal generateRandomTotalAmount() {
        return BigDecimal.valueOf(1 + (9999 * random.nextDouble())).setScale(2, BigDecimal.ROUND_HALF_UP); // 随机生成1到10000的金额，保留两位小数
    }

    private static BigDecimal generateRandomDiscount() {
        return BigDecimal.valueOf(random.nextDouble() * 100).setScale(2, BigDecimal.ROUND_HALF_UP); // 随机生成0到100的折扣，保留两位小数
    }

    private static String generateRandomAddress() {
        return "Address-" + random.nextInt(1000); // 随机生成地址
    }

    private static String generateRandomPhoneNumber() {
        return "138" + (random.nextInt(90000000) + 10000000); // 随机生成中国手机号码
    }

    private static String generateRandomNote() {
        return "Note-" + random.nextInt(100); // 随机生成备注信息
    }

}
