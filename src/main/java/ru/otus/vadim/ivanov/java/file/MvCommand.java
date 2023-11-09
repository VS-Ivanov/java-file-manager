package ru.otus.vadim.ivanov.java.file;

public class MvCommand implements Command{

    private FileSystemReceiver fileReceiver;

    public MvCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда mv требует два обязательных аргумента
        if(args.length != 2){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.mv(args[0],args[1]);
    }

    @Override
    public String getUsage() {
        return "mv [source] [destination] - move/rename file or directory source to destination";
    }
}
