package com.example.SpringOracle.WriteFile;

import com.example.SpringOracle.CreateLog.Logger;
import com.example.SpringOracle.Repositoris.ColumnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class CreateFile implements ICreateFile {
    @Autowired
    private Logger logger;


    @Override
    public void writeQuery() throws IOException {
        List<String> endLogTables = logger.CreateNotLogTables();
        System.out.println("Şu Tablolar için Log sorguları yazıldı : " + endLogTables);

        File file = new File("LogWithSpring.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        int i;
        for (i = 0; i < endLogTables.size(); i++) {
            bWriter.write("Create table " + endLogTables.get(i) + "_LOG As Select * From " + endLogTables.get(i) + " Where 1=0;" +
                    "\n/" +
                    "\n" +
                    "Alter Table " + endLogTables.get(i) + "_LOG " + "Add (ISLEM_TURU varchar(20), ISLEM_ZAMANI Date Default Sysdate);\n" +
                    "/\n" +
                    "CREATE OR REPLACE TRIGGER " + endLogTables.get(i) + "_DELETE_UPDATE_TRG\n AFTER DELETE OR UPDATE OF "
            );


            List<String> endTableColumnName = logger.createColumnName(endLogTables.get(i));
            //System.out.println(endTableColumnName);


            for (int z = 0, x = 0; z < endTableColumnName.size(); z++, x++) {
                bWriter.write(endTableColumnName.get(z));
                if (x != endTableColumnName.size() - 1)
                    bWriter.write(", ");
            }

            bWriter.write(" ON " + endLogTables.get(i) + "\n REFERENCING OLD AS OLD NEW AS NEW FOR EACH ROW \n BEGIN \n IF DELETING THEN \n INSERT INTO "
                    + endLogTables.get(i) + "_LOG\n (");

            for (int z = 0; z < endTableColumnName.size(); z++) {
                bWriter.write(endTableColumnName.get(z));
                bWriter.write(", ");
            }

            bWriter.write("ISLEM_TURU)\n VALUES\n (");

            for (int z = 0; z < endTableColumnName.size(); z++) {
                bWriter.write(":OLD." + endTableColumnName.get(z));
                bWriter.write(", ");
            }

            bWriter.write("'Silindi');\n ELSIF UPDATING THEN\n INSERT INTO " + endLogTables.get(i) + "_LOG\n (");

            for (int z = 0; z < endTableColumnName.size(); z++) {
                bWriter.write(endTableColumnName.get(z));
                bWriter.write(", ");
            }

            bWriter.write("ISLEM_TURU)\n VALUES\n (");

            for (int z = 0; z < endTableColumnName.size(); z++) {
                bWriter.write(":OLD." + endTableColumnName.get(z));
                bWriter.write(", ");
            }

            bWriter.write("'Güncellendi');\n END IF;\n END;\n/\n\n\n");
        }
        bWriter.close();
    }
}
