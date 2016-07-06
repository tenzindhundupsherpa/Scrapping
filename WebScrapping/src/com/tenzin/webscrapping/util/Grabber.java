/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.webscrapping.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Home
 */
public class Grabber {
        
    public URLConnection connect(String link) throws IOException{
        URL url = new URL(link);
        return url.openConnection();
    }
    
    public String getContent(String link)throws IOException{
        URLConnection conn = connect(link);
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            StringBuilder content = new StringBuilder();
            while((line=reader.readLine())!=null){
                content.append(line);               
            }
            reader.close();
            return content.toString();
    }
        public void downloadImage(String path,String fileName) throws IOException{
            URLConnection conn = connect(path);
            InputStream is = conn.getInputStream();
            FileOutputStream os = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int i=0;
            while((i=is.read(buffer))!=-1){
                os.write(buffer, 0, i);
            }
            os.close();
            is.close();
        }
        
    
}
