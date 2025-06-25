package com.gustavofarias.barcodeutil.repository;

import com.gustavofarias.barcodeutil.model.ApplicationIdentifiers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository interface for managing ApplicationIdentifiers entities.
 * <p>
 * Extends JpaRepository to provide standard CRUD operations.
 */
public interface ApplicationIdentifiersRepository extends JpaRepository<ApplicationIdentifiers, Long> {

    /**
     * Finds the first ApplicationIdentifiers record matching the given code.
     * <p>
     * Uses a native SQL query to retrieve one record by the 'code' column.
     *
     * @param code the application identifier code to search for
     * @return an Optional containing the found ApplicationIdentifiers, or empty if none found
     */
    @Query(value = "SELECT * FROM application_identifiers WHERE code = :code LIMIT 1", nativeQuery = true)
    Optional<ApplicationIdentifiers> findFirstByCode(@Param("code") int code);

}
