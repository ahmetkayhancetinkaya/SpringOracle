package com.example.SpringOracle;


import com.example.SpringOracle.CreateLog.Logger;
import com.example.SpringOracle.Repositoris.ColumnRepository;
import com.example.SpringOracle.Repositoris.TableRepository;
import com.example.SpringOracle.WriteFile.CreateFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/deneme")
public class MainControllerr {

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ColumnRepository columnRepository;

    @Autowired
    private CreateFile createFile;

    @RequestMapping(path = "/alltables")
    public @ResponseBody
    String getAllTables() throws IOException {
        createFile.writeQuery();
        //  logger.CreateNotLogTables();
        //  logger.createColumnName("COUNTRIES");
        //    List<String>notLogTables = tableRepository.findAllActiveUsersNative();
        //  System.out.println(logger.createColumnName("COUNTRIES"));
        return "Log Tabloları Olmayan Tablolar içinLog Tablosu Sorguları Oluşturuldu...";
    }
}
