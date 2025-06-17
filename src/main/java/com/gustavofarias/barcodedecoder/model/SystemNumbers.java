package com.gustavofarias.barcodedecoder.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "system_numbers")
@Getter
public class SystemNumbers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private String description;
}
