package com.app.clientsdataapi.entity;

import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection="occupations")
public class Occupation {
    public Occupation(String name, Gender type) {
        this.name = name;
        this.type = type;
    }

    String id;
    @Indexed(unique = true)
    String name;
    Gender type;
}