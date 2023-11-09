package ru.otus.vadim.ivanov.java.file;

public class CpCommand implements Command{
    private FileSystemReceiver fileReceiver;

    public CpCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        // команда cp требует два обязательных аргумента
        if(args.length != 2){
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }

        fileReceiver.cp(args[0],args[1]);
    }

    @Override
    public String getUsage() {
        return "cp [source] [destination] - copy file or directory source to destination";
    }
}
