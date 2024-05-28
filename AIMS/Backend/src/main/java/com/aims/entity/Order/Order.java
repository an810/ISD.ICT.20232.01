package com.aims.entity.Order;

import com.aims.entity.DeliveryInfo.DeliveryInfo;
import com.aims.utils.Constants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Setter
@Getter
@Document(collection = "order")
public class Order {
    @Id
    private String orderId;
    private String cartId;
    private List<OrderItem> listOrderItem;
    private DeliveryInfo deliveryInfo;
    private double totalAmount;
}