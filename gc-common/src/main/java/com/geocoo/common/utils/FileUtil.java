package com.geocoo.common.utils;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * desc:
 * file utils
 *
 * @author taopy
 * @create 2018-10-20 5:12 PM
 */
public class FileUtil {

    public static String mkdirs(String first, String... more) {
        Path p = Paths.get(first, more);
        File dir = new File(p.toString());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return p.toString();
    }

    public static void saveFile(byte[] fileBytes, String filepath) throws IOException {


        Path path = Paths.get(filepath);
        Files.write(path, fileBytes);
    }

    public static File getZipfile(String filepath) {

        try {
            String zipFilepath = ZipUtil.zipDir(filepath);
            return new File(zipFilepath);

        } catch (IOException e) {
            System.out.println("zip convert result error ");
            return null;
        }
    }


}