package com.gustavofarias.barcodedecoder.model;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Entity representing system numbers used in barcodes.
 * <p>
 * System numbers classify product categories or specific functions within barcode standards.
 */
@Entity
@Table(name = "system_numbers")
@Getter
public class SystemNumbers {

    /**
     * Primary key - auto-generated unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The system number value, unique and non-nullable.
     * For example, the first digit(s) of certain barcode types indicating product type.
     */
    @Column(nullable = false, unique = true)
    private Integer number;

    /**
     * A description explaining the meaning or usage of the system number.
     */
    @Column(nullable = false)
    private String description;
}
