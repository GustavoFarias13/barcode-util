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

    private Integer code;

    private String country;
}
