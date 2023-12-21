package com.example.mdi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class MDIEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

}
