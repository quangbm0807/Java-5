package org.example.ps27852_lab7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
        private Integer id;
        private String name;
        private String image;
        private Double   price;
        private String category;
        private int quantity;

}
