package com.gustavofarias.barcodedecoder.repository;

import com.gustavofarias.barcodedecoder.model.ApplicationIdentifiers;
import com.gustavofarias.barcodedecoder.model.Prefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicationIdentifiersRepository extends JpaRepository<Prefix, Long> {

    @Query(value = "SELECT * FROM application_identifiers WHERE code = :code LIMIT 1", nativeQuery = true)
    Optional<ApplicationIdentifiers> findFirstByCode(@Param("code") int code);

}
