package com.aims.entity.Product;

import com.aims.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.logging.Logger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {
    private static Logger LOGGER = Utils.getLogger(Product.class.getName());
    @Id
    protected String id;
    protected String title;
    protected String category;
    protected double importPrice; // the real price of product (eg: 450)
    protected Float price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String image;
    private boolean rushDeliverySupport;
}

