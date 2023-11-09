package ru.otus.vadim.ivanov.java.file;

public class LsCommand implements Command{

    private FileSystemReceiver fileReceiver;

    public LsCommand(FileSystemReceiver fileReceiver) {
        this.fileReceiver = fileReceiver;
    }

    @Override
    public void execute(String[] args) {
        //проверяем формат
        if(args.length > 1) {
            System.out.println(String.format("Invalid arguments! Use:\n%s",getUsage()));
            return;
        }
        //если команда запущена с ключом -l показываем дополнительную информацию
        if(args.length == 1 && args[0].contains("-l")){
           fileReceiver.lsList();
           return;
        }

        //если без ключей, вызываем обычный ls
        fileReceiver.ls();
    }

    @Override
    public String getUsage() {
        return  " ls - print file list current work directory\n" +
                " ls -l  - print file list current work directory with additional details";
    }
}
