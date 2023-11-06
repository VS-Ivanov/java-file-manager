package ru.otus.vadim.ivanov.java.file;

import java.io.File;

public class FileSystemReceiver {

    private File currentWorkDir;

    public FileSystemReceiver() {

        this.currentWorkDir = new File(System.getProperty("user.dir"));
    }

    public void pwd() {
        System.out.println(String.format("Working Directory = %s",currentWorkDir.getAbsolutePath()));
    }

    public void cd(String targetPath) {
        System.out.println(String.format("Cd in Directory = %s",targetPath));
    }

}
