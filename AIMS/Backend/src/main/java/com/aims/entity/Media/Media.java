package com.aims.entity.Media;

import com.aims.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.logging.Logger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Media {
    private static Logger LOGGER = Utils.getLogger(Media.class.getName());
    @Id
    protected String id;
    protected String name;
    protected String cat;
    protected int value; // the real price of product (eg: 450)
    protected int price; // the price which will be displayed on browser (eg: 500)
    protected int quantity;
    protected String type;
    protected String imageURL;
    private boolean supportRush;
}

