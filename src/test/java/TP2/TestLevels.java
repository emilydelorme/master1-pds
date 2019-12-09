package TP2;

import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tinylog.Logger;

import java.io.IOException;

public class TestLevels
{

    @Test
    public void testLevel1() throws EmptyProgram, TypeException, IOException
    {
        Logger.info("Compling tests/testlevel1/*");
        Assertions.assertTrue(TestUtils.testFolder("tests/testlevel1"));
    }

    @Test
    public void testLevel2() throws EmptyProgram, TypeException, IOException
    {
        Logger.info("Compling tests/testlevel2/*");
        Assertions.assertTrue(TestUtils.testFolder("tests/testlevel2"));
    }

    @Test
    public void testLevel3() throws EmptyProgram, TypeException, IOException
    {
        Logger.info("Compling tests/testlevel3/*");
        Assertions.assertTrue(TestUtils.testFolder("tests/testlevel3"));
    }

    @Test
    public void testLevel4() throws EmptyProgram, TypeException, IOException
    {
        Logger.info("Compling tests/testlevel4/*");
        Assertions.assertTrue(TestUtils.testFolder("tests/testlevel4"));
    }

    @Test
    public void testsujet() throws EmptyProgram, TypeException, IOException
    {
        Logger.info("Compling tests/sujet/*");
        Assertions.assertTrue(TestUtils.testFolder("tests/sujet"));
    }

}
