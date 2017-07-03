package com.Xepishon;

import java.io.File;

public class Flag {
    private String input,
            output, //имя выходного файла
            ofile;
    private int lCount = 0;
    private int nCount = 0;
    private int cCount = 0;
    private boolean l = false, n = false, c = false, d = false, o = false;

    public Flag(String[] argList) {
        if (argList.length == 0) throw new ArrayIndexOutOfBoundsException("have not command");
        for (int i = 0; i < argList.length; i++) {
            if (argList[i].length() != 0) {
                switch (argList[i]) {
                    case "-d": //название выходных файлов
                        d = true;
                        break;
                    case "-n": //колличество файлов
                        n = true;
                        nCount = Integer.parseInt(argList[i + 1]);
                        if (nCount < 0) {
                            nCount = 0;
                            throw new IllegalArgumentException("n not valid number");
                        }
                        break;
                    case "-c": //флаг колличества символав
                        c = true;
                        cCount = Integer.parseInt(argList[i + 1]);
                        if (cCount < 0) {
                            cCount = 0;
                            throw new IllegalArgumentException("c not valid number");
                        }
                        break;
                    case "-l": //флаг колличества строк
                        l = true;
                        lCount = Integer.parseInt(argList[i + 1]);
                        if (lCount <= 0) {
                            lCount = 0;
                            throw new IllegalArgumentException("l not valid number");
                        }
                        break;
                    case "-o": //базовое имя выходнго файла
                        o = true;
                        ofile = argList[i + 1];
                        ofile=ofile.replaceAll(" ","");
                        ofile=ofile.replace(".txt","");
                    default:
                        break;
                }
            }
        }
        input = argList[argList.length - 1];
        if (input == null || input.length() < 1) {
            input = null;
            throw new IllegalArgumentException("input not valid name");
        }
       if (!o||ofile.equals(input)) output=input;
        else output = ofile == null || ofile.equals("") ? "x" : ofile;
        if (!new File(input).canRead())
            throw new IllegalArgumentException(" not read file");
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    public int getlCount() {
        return lCount;
    }

    public int getnCount() {
        return nCount;
    }

    public int getcCount() {
        return cCount;
    }

    public boolean isL() {
        return l;
    }

    public boolean isN() {
        return n;
    }

    public boolean isC() {
        return c;
    }

    public boolean isD() {
        return d;
    }

}
