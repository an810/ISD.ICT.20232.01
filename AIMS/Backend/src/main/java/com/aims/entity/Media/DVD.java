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
public class DVD extends Media{
    String discType;
    String director;
    String runtime;
    String studio;
    String subtitles;
    Date releasedDate;
    String filmType;
}
