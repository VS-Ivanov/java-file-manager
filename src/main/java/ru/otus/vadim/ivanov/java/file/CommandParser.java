package ru.otus.vadim.ivanov.java.file;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {

    //cmd arg1 arg2 "arg3 arg3 arg3" -key
    public static String[] parse(String clientLine) {
        /*
         Сканируем строку посимвольно в поисках пробелов и кавычек
         Возвращаем массив блоков разделенных пробелами
         Все что находится внутри кавычек считаем одним блоком
         Первый элемент в возвращаемом массиве всегда является commandName
         */
        List<String> parsedBlocks = new ArrayList();

        try {
            int startIndex = 0;
            boolean quoteFlag = false;
            for(int i=0; i < clientLine.length();i++) {
                char c = clientLine.charAt(i);
                if(c == ' ') {
                    if(!quoteFlag) {
                        if(startIndex == i) {continue;} //на случай лишних пробелов
                        parsedBlocks.add(clientLine.substring(startIndex, i).trim());
                        startIndex = i+1;
                    }
                }
                if( c == '\"') {
                    //если флаг открывания кавычек не установлен - ставим
                    if(!quoteFlag) {
                        quoteFlag = true;
                        continue;
                    }
                    //был установлен флаг открывания кавычек, мы нашли конец блока
                    //читаем с небольшим смещением от startIndex, чтобы не забирать кавычку
                    parsedBlocks.add(clientLine.substring(startIndex+1,i).trim());
                    startIndex = i+1;
                    quoteFlag = false;
                }
            }

            //добавляем последнюю часть
            if(startIndex != clientLine.length()) {
                parsedBlocks.add(clientLine.substring(startIndex).trim());
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return parsedBlocks.toArray(new String[0]);
    }
}
