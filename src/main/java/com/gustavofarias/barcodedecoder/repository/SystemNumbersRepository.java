package com.gustavofarias.barcodedecoder.repository;

import com.gustavofarias.barcodedecoder.model.SystemNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Repository interface for managing SystemNumbers entities.
 * <p>
 * Provides CRUD operations and a custom method to find by system number.
 */
public interface SystemNumbersRepository extends JpaRepository<SystemNumbers, Long> {

    /**
     * Finds the first SystemNumbers entity with the specified number.
     * <p>
     * Uses a native query to retrieve the record with the matching 'number' field, limited to one result.
     *
     * @param number the system number to search for
     * @return an Optional containing the found SystemNumbers, or empty if none found
     */
    @Query(value = "SELECT * FROM system_numbers WHERE number = :number LIMIT 1", nativeQuery = true)
    Optional<SystemNumbers> findFirstByNumber(@Param("number") int number);
}
