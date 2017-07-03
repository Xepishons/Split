package com.Xepishon;

import java.io.*;
import java.util.ArrayList;

import static java.nio.file.Files.exists;

public class Main {

    private static final String[] abc = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

    public static void main(String[] args) throws Exception {
//        try {
        command(args);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//
//        }

    }

    public static void command(String[] args) throws FileNotFoundException {
        Flag argPosition = new Flag(args);
        String inputData = read(argPosition.getInput());
        ArrayList<StringBuilder> result = new ArrayList<>();
        String[] text = inputData.split("\n");

        int stringCount = 100;//колличество строк в файлах
        int fileAmount = text.length / 100; //колличество файлов
        int fileCount = 0; //колличество файлов для цикла
        int i = 0;//смещение по массиву
        int range;//разбиение на отдельные части

        if (argPosition.isC()) {
            int countC = 0;
            StringBuilder outString = new StringBuilder();
            for (String j : text) {
                countC += j.length();
                if (countC > argPosition.getcCount()) {
                    int inStrCount = countC - argPosition.getcCount() - 1;
                    outString.append(j.substring(0, inStrCount));
                    result.add(outString);
                    outString = new StringBuilder();
                    outString.append(j.substring(inStrCount)).append("\n");
                    countC = 0;
                } else if (countC == argPosition.getcCount()) {
                    outString.append(j).append("\n");
                    result.add(outString);
                    outString = new StringBuilder();
                    countC = 0;
                }
                outString.append(j).append("\n");
            }
            result.add(outString);

        } else {
            if (argPosition.isN()) {
                fileAmount = argPosition.getnCount();
                if (text.length / argPosition.getnCount() * argPosition.getnCount() ==
                        text.length) stringCount = text.length / argPosition.getnCount();
                else stringCount = text.length / argPosition.getnCount() + 1;

            } else if (argPosition.isL()) {
                stringCount = argPosition.getlCount();
                if (text.length / argPosition.getlCount() * argPosition.getlCount() ==
                        text.length)
                    fileAmount = text.length / argPosition.getlCount();
                else fileAmount = text.length / argPosition.getlCount() + 1;
            }

            //основной цикл прохода
            while (fileCount < fileAmount) {
                StringBuilder outString = new StringBuilder();

                //усливие прохода цикла
                if (i + stringCount > text.length) {
                    range = text.length;
                } else {
                    range = i + stringCount;
                }

                //цикл добавление строк для записи
                while (i < range) {
                    outString.append(text[i]).append("\n");
                    i++;

                }

                result.add(outString);
                fileCount++;
            }
        }
        write(result, argPosition.isD(), argPosition.getOutput());
    }

    public static String read(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);

        if (!exists(file.toPath()))
            throw new FileNotFoundException("File not found");

        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {

                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }


    private static void write(ArrayList result, Boolean flagD, String output)
            throws FileNotFoundException {
        if (flagD) {
            int numberFile = 1;
            for (int i = 0; i < result.size(); i++) {
                PrintWriter out = new PrintWriter(output + numberFile + ".txt");
                numberFile++;
                out.println(result.get(i));
                out.close();
            }
        } else {
            int[] positionABC = new int[howPosition(result.size())];
            if (positionABC.length == 0) positionABC = new int[1];
            for (int i = 0; i < result.size() - 1; i++) {
                positionABC = switchABC(positionABC);
                String outName = output;
                for (int j = 0; j <= positionABC.length - 1; j++) {
                    outName = outName + abc[positionABC[j]];
                }
                outName += ".txt";
                PrintWriter out = new PrintWriter(outName);
                positionABC[positionABC.length - 1]++;
                out.println(result.get(i));
                out.close();
            }
        }

    }

    private static int howPosition(int size) {
        int result = 0;
        while (size > 0) {
            size /= 25;
            result++;
        }
        return result;
    }

    public static int[] switchABC(int[] result) {//для проверки массива
        for (int i = result.length - 1; i > 0; i--) {
            if (i != 0 &&
                    result[i] >= 25 &&
                    result.length > 1) {
                result[i] = 0;
                result[i - 1] += 1;
            }
        }
        if (result[0] > 24) result[0] = 24;
        return result;
    }

}
