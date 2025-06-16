package com.gustavofarias.barcodedecoder.repository;

import com.gustavofarias.barcodedecoder.model.Prefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PrefixRepository extends JpaRepository<Prefix, Long> {

    @Query("SELECT p.country FROM Prefix p WHERE p.code = :code")
    Optional<String> findCountryByPrefixCode(@Param("code") int code);

}
