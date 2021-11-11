package com.example.SpringOracle.Entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_TABLES")
public class Tables {

    //  @Id
    //  @Column(name = "OWNER", nullable = false)
    //  private String owner;
    @Id
    @Column(name = "TABLE_NAME", nullable = false)
    private String table_name;
}
