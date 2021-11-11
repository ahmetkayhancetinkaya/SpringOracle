package com.example.SpringOracle.Entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "USER_TAB_NAME")
public class Columns {
    @Id
    @Column(name = "COLUMN_NAME", nullable = false)
    private String column_name;
}
