package ru.otus.vadim.ivanov.java.file;

public class MkdirCommand implements Command{

    private FileSystemReceiver fileReceiver;

    public MkdirCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда mkdir требует обязательный аргумент
        if(args.length != 1){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.mkdir(args[0]);
    }

    @Override
    public String getUsage() {
        return "mkdir [name] - make new directory";
    }
}
