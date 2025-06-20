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

    @Column(nullable = false, unique = true)
    private Integer number;

    @Column(nullable = false)
    private String description;
}
