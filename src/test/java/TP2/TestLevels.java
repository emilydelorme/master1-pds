package TP2;

import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestLevels
{

    @Test
    public void testLevel1() throws EmptyProgram, TypeException, IOException
    {
        Assertions.assertTrue(TestUtils.testFolder("tests/testlevel1"));
    }
}
