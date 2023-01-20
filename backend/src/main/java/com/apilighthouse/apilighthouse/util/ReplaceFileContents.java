package com.apilighthouse.apilighthouse.util;

import com.apilighthouse.apilighthouse.entity.TLighthouse;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ReplaceFileContents {

    /**
     *
     * @param list
     * @throws IOException
     * copy from : https://blog.csdn.net/weixin_35306450/article/details/114162690
     */
    public static void write(List<TLighthouse> list) throws IOException {

        String oldFileName = "/etc/nginx/conf.d/default.conf";
        String tmpFileName = "./default.conf";
        String templateFileName = "./defaultTemplate.conf";

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(templateFileName));

            String property = System.getProperty("os.name");
            if (property.equals("Windows 10")){
                tmpFileName="src/main/resources/default.conf";
            }

            bw = new BufferedWriter(new FileWriter(tmpFileName));
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                if (i == 12) {
                    BufferedWriter finalBw = bw;
                    list.forEach(tLighthouse -> {
                        try {
                            finalBw.write("    location /" + tLighthouse.getServerName() + "/ {" + "\n");
                            finalBw.write("       proxy_pass   http://" + tLighthouse.getUrl() + ";" + "\n");
                            finalBw.write("    }" + "\n");
                            finalBw.write("    location /" + tLighthouse.getServerName() + " {" + "\n");
                            finalBw.write("       proxy_pass   http://" + tLighthouse.getUrl() + ";" + "\n");
                            finalBw.write("    }" + "\n");
                        }
                        catch (IOException e){
                            log.error(e.getMessage());
                        }
                    });
                }
                bw.write(line+"\n");
                i++;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if(br != null)
                    br.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            try {
                if(bw != null)
                    bw.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
// Once everything is complete, delete old file..
        File oldFile = new File(oldFileName);
        oldFile.delete();
// And rename tmp file's name to old file name
        File newFile = new File(tmpFileName);
        newFile.renameTo(oldFile);
    }
}
