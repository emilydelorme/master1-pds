package TP2;

import TP2.ASD.Program;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tinylog.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class TestUtils {

    private TestUtils() {}

    public static boolean testFolder(String path) throws EmptyProgram, TypeException, IOException
    {
        File dir = new File(path);
        File [] files = dir.listFiles((dir1, name) -> name.endsWith(".vsl"));

        for (File vslfile : Objects.requireNonNull(files)) {
            boolean result = testFile(vslfile.getPath());
            if(!result)
                return false;
        }
        return true;
    }

    public static boolean testFile(String path) throws IOException, EmptyProgram, TypeException
    {
        // Instantiate Lexer
        VSLLexer lexer = new VSLLexer(CharStreams.fromPath(Paths.get(path)));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Instantiate Parser
        VSLParser parser = new VSLParser(tokens);

        // Parse
        Program ast = parser.program().out;

        Logger.info(ast.pp());
        Logger.info(ast.toIR());

        return true;
    }
}
