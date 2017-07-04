import com.Xepishon.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    void flagTest() throws IOException {
        File read = new File("trash\\test.txt");
        read.createNewFile();
        String[] args = new String[10];
        args[0] = "-o";
        args[1] = "trash\\output.txt";
        args[2] = "-d";
        args[3] = "-l";
        args[4] = "35";
        args[5] = "-c";
        args[6] = "2000";
        args[7] = "-n";
        args[8] = "10";
        args[9] = "trash\\test.txt";
        Flag executor = new Flag(args);
        assertTrue(executor.isD());
        assertTrue(executor.isC());
        assertTrue(executor.isL());
        assertTrue(executor.isN());
        assertEquals(35, executor.getlCount());
        assertEquals(2000, executor.getcCount());
        assertEquals(10, executor.getnCount());
        assertEquals("trash\\test.txt", executor.getInput());
        assertEquals("trash\\output", executor.getOutput());
        read.delete();
    }

    @Test
    void commandTest() throws IOException {
        Main test = new Main();
        String[] args = new String[10];
        args[0] = "-o";
        args[1] = "trash\\output.txt";
        args[2] = "-d";
        args[3] = "-l";
        args[4] = "35";
        args[5] = "-c";
        args[6] = "1";
        args[7] = "-n";
        args[8] = "3";
        args[9] = "trash\\test.txt";
        File file = new File(args[9]);
        PrintWriter wrFile = new PrintWriter(file);
        wrFile.write("a\nb\nc\n");
        wrFile.close();
        test.command(args);
        file.delete();
        assertEquals("a\n\n", test.read("trash\\output1.txt"));
        assertEquals("a\nb\n\n", test.read("trash\\output2.txt"));
        assertEquals("b\nc\n\n", test.read("trash\\output3.txt"));
        assertEquals("c\n\n", test.read("trash\\output4.txt"));
        for (int i = 1; i < 4; i++) new File("trash\\output" + i + ".txt").delete();
    }


}
