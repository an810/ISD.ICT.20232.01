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
public class DVD extends Product {
    String discType;
    String director;
    String runtime;
    String studio;
    String subtitles;
    Date releasedDate;
    String filmType;
}
