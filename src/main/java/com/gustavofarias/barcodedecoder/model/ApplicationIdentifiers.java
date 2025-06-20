package com.gustavofarias.barcodedecoder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_identifiers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationIdentifiers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer code;

    @Column(nullable = false)
    private String description;

    public ApplicationIdentifiers(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
