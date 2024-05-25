package com.aims.entity.Media;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CD extends Media{
    String artist;
    String recordLabel;
    String musicType;
    Date releasedDate;
    String form;
}
