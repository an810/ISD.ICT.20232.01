package com.aims.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CD extends Product {
    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;
    String form;
}
