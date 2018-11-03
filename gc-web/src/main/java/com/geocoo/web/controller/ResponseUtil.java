package com.geocoo.web.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * desc:
 *
 * @author taopy
 * @create 2018-11-03 2:07 PM
 */
public class ResponseUtil {

    public static void downloadFile(HttpServletResponse response, File file, Boolean deleteAfterDownload) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            byte[] bytes = new byte[1024];
            OutputStream ops = response.getOutputStream();
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            int i = bis.read(bytes);
            while (i != -1) {
                ops.write(bytes, 0, i);
                ops.flush();
                i = bis.read(bytes);
            }
            if (deleteAfterDownload) {
                file.delete();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}