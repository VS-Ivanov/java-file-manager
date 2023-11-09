package ru.otus.vadim.ivanov.java.file;

public class PwdCommand implements Command{

    private FileSystemReceiver fileReceiver;

    public PwdCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        //команда pwd не поддерживает аргументы
        if(args.length > 0){
            System.out.println(String.format("Command not support arguments! Use:\n %s",getUsage()));
            return;
        }
        fileReceiver.pwd();
    }

    @Override
    public String getUsage() {
        return " pwd - print current work directory";
    }
}
