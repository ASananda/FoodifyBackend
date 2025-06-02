package com.foodify.foodify.Entity;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "foods")
public class FoodEntity {
    @Id
    private String id;

    private String name;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;


}
