package com.gustavofarias.barcodeutil.repository;

import com.gustavofarias.barcodeutil.model.Prefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository interface for managing Prefix entities.
 * <p>
 * Extends JpaRepository to provide CRUD operations on Prefix table.
 */
public interface PrefixRepository extends JpaRepository<Prefix, Long> {

    /**
     * Finds the first Prefix entity matching the specified code.
     * <p>
     * Uses a native SQL query to retrieve the record where 'code' matches the parameter,
     * limiting the result to one row.
     *
     * @param code the prefix code to search for
     * @return an Optional containing the found Prefix, or empty if not found
     */
    @Query(value = "SELECT * FROM prefix WHERE code = :code LIMIT 1", nativeQuery = true)
    Optional<Prefix> findFirstByCode(@Param("code") int code);

}
