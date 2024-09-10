package com.zjyun.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("order_1")
public class Order {
    @TableId("order_id")
    private String orderId;
    private Integer customerId;
    private LocalDateTime orderDate;
    private LocalDateTime deliveryDate;
    private Integer status;
    private Integer paymentStatus;
    private Integer paymentMethod;
    private BigDecimal totalAmount;
    private BigDecimal discount;
    private String shippingAddress;
    private String billingAddress;
    private String contactPhone;
    private String note;
}