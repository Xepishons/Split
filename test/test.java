import com.Xepishon.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    void flagTest() throws IOException {
        File read = new File("output\\test.txt");
        read.createNewFile();
        String[] args = new String[9];
        args[0] = "-o";
        args[1] = "-d";
        args[2] = "-l";
        args[3] = "35";
        args[4] = "-c";
        args[5] = "2000";
        args[6] = "-n";
        args[7] = "10";
        args[8] = "output\\test.txt";
        Flag executor = new Flag(args);
        assertTrue(executor.isD());
        assertEquals(35,executor.getlCount());
        assertEquals(2000,executor.getcCount());
        assertEquals(10,executor.getnCount());
        assertEquals("output\\test.txt",executor.getInput());
        read.delete();
    }

    
}
