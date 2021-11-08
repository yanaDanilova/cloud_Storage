package com.geekbrains;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Stream;

public class TelNetApp {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("cloud_Storage.client/clientDir");

        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if(command.startsWith("/ls")){

                Files.walkFileTree(path,new SimpleFileVisitor<Path>(){

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println(file.getFileName());
                        return super.visitFile(file, attrs);
                    }
                });
            }

            if(command.startsWith("/mkDir")){
                String[] tokens =  command.split("\\s",2);
                Path dirName = Paths.get( path.getParent() + "/" +tokens[1]);
                Files.createDirectory(dirName);
            }
            if(command.startsWith("/cat")){
                String[] tokens = command.split("\\s",2);
                Path fileName = Paths.get(path + "/" + tokens[1]);
                System.out.println(Files.readAllLines(fileName));
            }
            if(command.startsWith("/cd")){
                String[] tokens = command.split("\\s",2);
                String inDir = tokens[1];
                Path inDirPath = Paths.get(path.getParent() + "/" + inDir);
                path = inDirPath;
                System.out.println(path);
            }

        }
    }

}
