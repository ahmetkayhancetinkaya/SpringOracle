package com.example.SpringOracle.Repositoris;

import com.example.SpringOracle.Entities.Columns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ColumnRepository extends JpaRepository<Columns, Integer> {
    @Query(
            value = "SELECT COLUMN_NAME FROM USER_TAB_COLUMNS WHERE TABLE_NAME= ?1" ,
            nativeQuery = true)
    List<String> findAllUserColumns(String query_table_name);
}
