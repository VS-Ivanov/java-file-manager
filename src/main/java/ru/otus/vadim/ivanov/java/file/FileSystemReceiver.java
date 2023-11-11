package ru.otus.vadim.ivanov.java.file;

import java.io.*;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileSystemReceiver {

    private File cwd;

    public FileSystemReceiver() {

        //запоминаем рабочую директорию приложения
        this.cwd = new File(System.getProperty("user.dir"));
    }

    public void pwd(){
        System.out.println(cwd.getAbsolutePath());
    }

    public void ls(){
        Arrays.stream(cwd.list()).forEach(System.out::println);
    }

    public void lsList(){
        Arrays.stream(cwd.listFiles()).forEach(
                file->System.out.println(String.format("%s %s %-10s %s",
                        file.isDirectory()?"d":"-",
                        convertTime(file.lastModified()),
                        file.length(),
                        file.getName()
                ))
        );
    }

    public void mkdir(String dirName){
        File targetDir = getAbsoluteFile(dirName);
        if(targetDir.exists() && targetDir.isDirectory()) {
            System.out.println(String.format("Directory %s already exists!",dirName));
            return;
        }

        if(targetDir.exists() && !targetDir.isDirectory()){
            System.out.println(String.format("File %s already exists! It is not directory!",dirName));
            return;
        }

        if(!targetDir.mkdir()){
            System.out.println(String.format("Directory %s not created!",dirName));
        };
    }

    public void cd(String dirName){

        //попытка перехода в текущий каталог
        if(Objects.equals(dirName,".")){
            return;
        }

        //попытка перехода в родительский каталог
        if(Objects.equals(dirName,"...")){
            cwd = cwd.getParentFile();
            return;
        }

        //иначе ходим куда-то в другое место
        File targetDir = getAbsoluteFile(dirName);
        if(!targetDir.exists()) {
            System.out.println(String.format("Directory %s not found!",dirName));
            return;
        }

        if(!targetDir.isDirectory()){
            System.out.println(String.format("%s not a directory!",dirName));
            return;
        }

        /*
          Насколько понял на java.io нет подходящего метода для смены текущей директории
          https://stackoverflow.com/questions/840190/changing-the-current-working-directory-in-java
          Метод System.setProperty("user.dir","path") - не срабатывает как нужно

         */
        //System.setProperty("user.dir",targetDir.getAbsolutePath());

        cwd = targetDir;

    }

    public void rm(String filename){
        File target = getAbsoluteFile(filename);
        if(!target.exists()) {
            System.out.println(String.format("%s not found!",filename));
            return;
        }

        //проверяем содержимое директории и только потом удаляем
        //если в директории есть содержимое - удаляем его
        if(target.isDirectory()){
            System.out.println(String.format("%s is a directory!",filename));

            Arrays.stream(target.list()).forEach(file ->
                    rm(target.getPath()+File.separator+file));

            if(!target.delete()){
                System.out.println(String.format("Cannot remove directory %s",filename));
                return;
            }

            System.out.println(String.format("%s - removed",filename));
            return;
        }

        //если это обычный файл - просто удаляем
        if(!target.delete()){
            System.out.println(String.format("Cannot remove file %s",filename));
            return;
        }

        System.out.println(String.format("%s - removed",filename));
    }

    public void finfo(String filename){
        File target = getAbsoluteFile(filename);

        if(!target.exists()){
            System.out.println(String.format("File %s not found!",filename));
            return;
        }

        System.out.println(String.format("Name: %s",target.getName()));
        System.out.println(String.format("Path: %s",target.getAbsolutePath()));
        System.out.println(String.format("Type: %s",target.isDirectory()?"Directory":"File"));
        System.out.println(String.format("IsHidden: %s",target.isHidden()));
        System.out.println(String.format("CanRead: %s",target.canRead()));
        System.out.println(String.format("CanWrite: %s",target.canWrite()));
        System.out.println(String.format("CanExecute: %s",target.canExecute()));
        System.out.println(String.format("Last modified: %s",convertTime(target.lastModified())));
        System.out.println(String.format("Size(bytes): %s",target.length()));

    }

    public void mv(String sourceFilename, String targetFilename) {
        File source = getAbsoluteFile(sourceFilename);
        File target = getAbsoluteFile(targetFilename);

        if(!source.exists()){
            System.out.println(String.format("%s not found!",sourceFilename));
            return;
        }

        //если наш таргет является директорий которая существует, то просто переносим туда
        if(target.exists() && target.isDirectory()){
            File mvTarget = new File(target.getAbsoluteFile()+File.separator+source.getName());
            if(!source.renameTo(mvTarget)){
                System.out.println(String.format("Cannot move %s to %s!",sourceFilename,targetFilename));
            }
            return;
        }

        //если таргет уже существует, но это не директория
        if(target.exists() && !target.isDirectory()){
            System.out.println(String.format("File %s already exists!", target.getAbsolutePath()));
            return;
        }

        //если таргета не существует, пробуем переименовать
        if(!source.renameTo(target)){
            System.out.println(String.format("Cannot move %s to %s!",sourceFilename,targetFilename));
        }

    }

    public void cp(String sourceFilename, String targetFilename){
        File source = getAbsoluteFile(sourceFilename);
        File target = getAbsoluteFile(targetFilename);

        //вызываем метод копирования файлов
        copy(source,target);

    }

    //найти файл с указанным именем в текущем каталоге или любом его подкаталоге
    public void find(String filename){
        //хз как правильно все это через стримы сделаю через рекурсию
        List<File> results = new ArrayList<>();
        findFiles(cwd,filename,results);

        System.out.println(String.format("Found %d matches:",results.size()));
        results.stream().forEach(System.out::println);

    }

    private void findFiles(File directory, String pattern, List results){
        File[] files = directory.listFiles();
        for(int i=0; i<files.length;i++){
            if(files[i].getName().contains(pattern)){
                results.add(files[i]);
            }
            if(files[i].isDirectory()){
                findFiles(files[i],pattern,results);
            }
        }
    }

    private void copy(File source, File target){
        if(!source.exists()){
            System.out.println(String.format("%s not found!",source.getAbsolutePath()));
            return;
        }

        File copy = new File(target.getAbsoluteFile()+File.separator+source.getName());
        if(copy.exists()){
            System.out.println(String.format("%s already exists!",copy.getAbsolutePath()));
            return;
        }

        //если соурс - директория, копируем рекурсивно все содержимое
        if(source.isDirectory()) {
            System.out.println(String.format("%s is a directory!",source.getAbsolutePath()));
            if(!copy.mkdir()){
                System.out.println(String.format("Directory %s not created!",copy.getAbsolutePath()));
                return;
            };
            Arrays.stream(source.listFiles()).forEach(file->copy(file,copy));
            System.out.println(String.format("%s - copied",source.getAbsolutePath()));
            return;
        }

        //если соурс - файл, копируем файл
        copyFile(source,copy);
        System.out.println(String.format("%s - copied",source.getAbsolutePath()));
    }

    private void copyFile(File source, File target){
        try(InputStream in = new FileInputStream(source)){
            try(OutputStream out = new FileOutputStream(target)){
                byte[] buf = new byte[1024];
                int length;
                while ((length = in.read(buf)) > 0) {
                    out.write(buf, 0, length);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private File getAbsoluteFile(String path){

//        if(path == ".") {return cwd;}
//        // родители могут вызываться рекурсивно :(
//        if(path == ".."){return cwd.getParentFile();}

        File file = new File(path);
        if(file.isAbsolute()){
            return file;
        }
        return new File(cwd.getAbsoluteFile()+File.separator+path);
    }

    private String convertTime(long time){
        Date date = new Date(time);
        Format format = new SimpleDateFormat(" dd.MM.yyyy HH:mm:ss");
        return format.format(date);
    }

}
