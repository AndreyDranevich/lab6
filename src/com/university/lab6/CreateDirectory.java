package com.university.lab6;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
  Created by Andrey on 29.10.2016.
*/

public class CreateDirectory {
    public static void main(String[] args) throws IOException {
        String myDir = createRootDirectory();
        createFoldersAndFiles(myDir);
    }

    static private String createRootDirectory() throws IOException {
        List<String> root = Reader.readFile("root.txt");
        Pattern wayB = Pattern.compile("[0-9a-zA-Z_:-]+");
        Matcher m = wayB.matcher(root.get(0));
        String wayBefore = "";
        while (m.find()) {
            wayBefore += m.group() + File.separator;
        }
        String myDir = wayBefore + root.get(1);

        File myPath = new File(myDir);
        myPath.mkdirs();
        return myDir;
    }

    static private void createFoldersAndFiles(String myDir) throws IOException {
        List<String> files = Reader.readFile("files.txt");
        List<String> poem = Reader.readFile("poem.txt");
        Pattern file = Pattern.compile("[0-9a-zA-Z_-]+(\\.[a-z]+)?");

        for (String s : files) {
            Matcher mFile = file.matcher(s);
            String way = myDir;
            String name = "";
            int level = 1;
            while (mFile.find()) {
                String temp = mFile.group();
                if (!isFile(temp)) {
                    way += File.separator + deleteEnd(temp);
                    level++;
                }
                if (isFile(temp)) {
                    if (isTxtForm(temp, name)) {
                        way += File.separator + name;
                        level++;
                    }
                    name = temp;
                }
            }
            File f = new File(way);
            f.mkdirs();
            if (!name.equals("")) {
                File f112 = new File(way + File.separator + name);
                f112.createNewFile();
                FileWriter fw = new FileWriter(way + File.separator + name);
                fw.write(way + "\r\nNesting level: " + level + "\r\n" + poem.get(level - 1));
                fw.close();
            }
        }

    }

    static private boolean isFile(String s) {
        Pattern file = Pattern.compile("[0-9a-zA-Z_-]+\\.[a-z]+");
        Matcher m = file.matcher(s);
        return m.find();
    }

    static private String deleteEnd(String s) {
        return s.substring(0, s.length());
    }

    static private boolean isTxtForm(String s1, String s2) {
        int s1Len = s1.length();
        int s2Len = s2.length();
        if (s1Len < 5 | s2Len < 5)
            return false;
        if (s1.substring(s1Len - 4).equals(s2.substring(s2Len - 4)))
            return true;
        return false;
    }
}
