package ru.otus.vadim.ivanov.java.file;

import java.util.Arrays;

public class CdCommand implements Command{

    private FileSystemReceiver fileReceiver;
    public CdCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда cd требует обязательный аргумент
        if(args.length != 1){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.cd(args[0]);
    }

    @Override
    public String getUsage() {

        return "cd [path] - change current working directory to path";
    }
}
