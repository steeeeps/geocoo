package com.geocoo.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * desc: zip utils
 *
 * @author taopy
 * @create 2018-10-07 10:41 PM
 */
public class ZipUtil {

    public static void unzip(String sourceZipfile, String targetDir) throws IOException {

        FileUtil.mkdirs(targetDir);

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(sourceZipfile));
        ZipEntry zipEntry = zis.getNextEntry();
        while (zipEntry != null) {
            String fileName = zipEntry.getName();
            String filepath = Paths.get(targetDir, fileName).toString();
            if (fileName.endsWith(File.separator)) {
                FileUtil.mkdirs(filepath);
                zipEntry = zis.getNextEntry();
                continue;
            }
            File newFile = new File(filepath);
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            zipEntry = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }

    public static String zipDir(String dirToZip) throws IOException {
        File fileToZip = new File(dirToZip);
        String path = fileToZip.getParent();
        String zipFilepath = Paths.get(path, fileToZip.getName() + ".zip").toString();


        FileOutputStream fos = new FileOutputStream(zipFilepath);
        ZipOutputStream zipOutputStream = new ZipOutputStream(fos);

        zipFile(fileToZip, fileToZip.getName(), zipOutputStream);
        zipOutputStream.close();
        fos.close();
        return zipFilepath;
    }

    private static void zipFile(File fileToZip, String filename, ZipOutputStream zipOutputStream) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }


        if (fileToZip.isDirectory()) {
            if (filename.endsWith(File.separator)) {
                zipOutputStream.putNextEntry(new ZipEntry(filename));
                zipOutputStream.closeEntry();
            } else {
                zipOutputStream.putNextEntry(new ZipEntry(filename + File.separator));
                zipOutputStream.closeEntry();
            }
            File[] childs = fileToZip.listFiles();
            for (File childFile : childs) {
                zipFile(childFile, filename + File.separator + childFile.getName(), zipOutputStream);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(filename);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOutputStream.write(bytes, 0, length);
        }
        fis.close();
    }


}