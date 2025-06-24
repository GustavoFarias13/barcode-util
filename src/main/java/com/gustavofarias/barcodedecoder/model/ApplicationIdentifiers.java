package com.gustavofarias.barcodedecoder.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity representing an Application Identifier (AI) used in GS1 barcodes.
 * <p>
 * Maps to the database table "application_identifiers" and stores information
 * about GS1 AI codes and their descriptions.
 */
@Entity
@Table(name = "application_identifiers")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationIdentifiers {

    /**
     * Primary key - auto-generated unique ID for each record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The numeric Application Identifier code (e.g., 01, 10, 17).
     * Marked as unique and non-nullable in the database.
     */
    @Column(nullable = false, unique = true)
    private Integer code;

    /**
     * The description text that explains what the AI code means.
     */
    @Column(nullable = false)
    private String description;

}
