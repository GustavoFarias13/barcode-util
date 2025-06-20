package com.gustavofarias.barcodedecoder.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "prefix")
@Getter
public class Prefix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer code;

    @Column(nullable = false)
    private String country;
}
