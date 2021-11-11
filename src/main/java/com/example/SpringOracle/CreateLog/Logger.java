package com.example.SpringOracle.CreateLog;

import com.example.SpringOracle.Repositoris.ColumnRepository;
import com.example.SpringOracle.Repositoris.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Logger {
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    ColumnRepository columnRepository;



    public List<String> CreateNotLogTables() {
        List<String> notLogTables = tableRepository.findAllActiveUsersNative();
        for (int i = 0; i < notLogTables.size(); i++) {
            String tablePoint = notLogTables.get(i);
            String tablePointAlternative = (notLogTables.get(i) + "_LOG");

            if ((notLogTables.contains(tablePoint) && notLogTables.contains(tablePointAlternative))) {
                notLogTables.remove(tablePoint);
                notLogTables.remove(tablePointAlternative);
                i--;
            }
        }
        return  notLogTables;
    }

    public List<String> createColumnName(String table_name) {
        List<String> tableColumsName = columnRepository.findAllUserColumns(table_name);
        return tableColumsName;

    }
}






