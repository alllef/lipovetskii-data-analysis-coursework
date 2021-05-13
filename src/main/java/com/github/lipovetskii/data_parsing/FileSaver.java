package com.github.lipovetskii.data_parsing;

import java.io.FileWriter;
import java.io.IOException;

public class FileSaver {

    public static void saveString(String str){

        try (FileWriter writer = new FileWriter("howlongtobeat_data.csv",true)){
        writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
