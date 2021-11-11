package com.example.SpringOracle.Repositoris;

import com.example.SpringOracle.Entities.Tables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Tables, Integer> {
    @Query(
            value = "SELECT table_name FROM user_tables" ,
            nativeQuery = true)
    List<String> findAllActiveUsersNative();

}
