package ru.otus.vadim.ivanov.java.file;

public class PwdCommand implements Command{

    private FileSystemReceiver fileReceiver;

    public PwdCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        fileReceiver.pwd();
    }

    @Override
    public String getUsage() {
        return null;
    }
}
