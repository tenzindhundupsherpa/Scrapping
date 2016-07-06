/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.webscrapping;

import com.tenzin.webscrapping.util.Grabber;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 *
 * @author Home
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner input=new Scanner(System.in);
        System.out.println("Enter URL:");
        String link = input.next();
        String baseURL = "http://goalnepal.com/";
        try{
        
            Grabber grabber = new Grabber();
            
            String regEx="graphics/event/(.*?).jpg";
           
            Pattern pattern = Pattern.compile(regEx);
            
            Matcher matcher= pattern.matcher(grabber.getContent(link));
            
            while(matcher.find()){
                String imgPath=matcher.group(0);
               
                String path = baseURL + imgPath;
               
                String[] tokens = path.split("/");
                
                String folder = tokens[tokens.length-2];
                File file = new File(folder);
                if(!file.isDirectory()){
                    file.mkdir();
                }
                
                System.out.println("Downloading " + path);
                
                grabber.downloadImage(path, folder + "/" + tokens[tokens.length-1]);
                
            }
            
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
    
}
