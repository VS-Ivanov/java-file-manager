package ru.otus.vadim.ivanov.java.file;

import java.util.Arrays;

public class CdCommand implements Command{

    private FileSystemReceiver fileReceiver;
    public CdCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        System.out.println(Arrays.deepToString(args));
        fileReceiver.cd(args[0]);
    }

    @Override
    public String getUsage() {
        return null;
    }
}
