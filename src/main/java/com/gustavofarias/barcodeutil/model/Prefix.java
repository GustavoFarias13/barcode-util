package com.gustavofarias.barcodeutil.model;

import jakarta.persistence.*;
import lombok.Getter;

/**
 * Entity representing a prefix code and its associated country.
 * <p>
 * This is typically used to map barcode prefix codes to their
 * respective country or region of origin.
 */
@Entity
@Table(name = "prefix")
@Getter
public class Prefix {

    /**
     * Primary key - auto-generated unique identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The prefix code (e.g., first 2 or 3 digits of a barcode)
     * which identifies the country or special function.
     * This value is unique and cannot be null.
     */
    @Column(nullable = false, unique = true)
    private Integer code;

    /**
     * The country or region name associated with the prefix code.
     */
    @Column(nullable = false)
    private String country;
}
