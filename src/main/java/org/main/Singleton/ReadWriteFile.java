package org.main.Singleton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadWriteFile {
    private static ReadWriteFile instance;
    public ReadWriteFile() {
    }

    public static ReadWriteFile getInstance(){
        if (instance == null){
            instance = new ReadWriteFile();
        }
        return instance;
    }

    public List<String> readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        BufferedReader br = Files.newBufferedReader(path);
        List<String> listOfLines = new ArrayList<>();

        String input;
        try {
            while ((input = br.readLine()) != null) {
                listOfLines.add(input);
            }
        } finally {
            br.close();
        }
        return listOfLines;
    }

    public void writeFile(String filePath, List<String> listOfLines) throws IOException{
        Path path = Paths.get(filePath);
        BufferedWriter bw = Files.newBufferedWriter(path);

        try{
            Iterator<String> iter = listOfLines.iterator();
            while (iter.hasNext()){
//                String item = this.curata(iter.next());
                String item = iter.next();
                if (item.length()<2) continue;
                bw.write(item);
                bw.newLine();
            }
        }finally{
            bw.close();
        }
    }
}
