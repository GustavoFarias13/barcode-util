package com.gustavofarias.barcodedecoder.repository;

import com.gustavofarias.barcodedecoder.model.Prefix;
import com.gustavofarias.barcodedecoder.model.SystemNumbers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SystemNumbersRepository extends JpaRepository<Prefix, Long> {

    @Query(value = "SELECT * FROM system_numbers WHERE number = :number LIMIT 1", nativeQuery = true)
    Optional<SystemNumbers> findFirstByNumber(@Param("number") int number);

}
