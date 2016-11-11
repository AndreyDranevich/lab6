package com.university.lab6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
  Created by Andrey on 29.10.2016.v
*/

class Reader {
    static List<String> readFile(String way) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(new File(way)));
        String line;
        List<String> itemsList = new ArrayList<String>();
        while ((line = in.readLine()) != null)
            itemsList.add(line);
        in.close();
        return itemsList;
    }
}
