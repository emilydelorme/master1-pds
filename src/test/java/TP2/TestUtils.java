package TP2;

import TP2.ASD.Program;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.tinylog.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class TestUtils {

    private TestUtils() {}

    public static boolean testFolder(String path) throws EmptyProgram, TypeException, IOException {
        File dir = new File(path);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".vsl"));
        Files.createDirectories(Paths.get("build/llvm/test"));

        for (File vslfile : Objects.requireNonNull(files)) {
            boolean result = testFile(vslfile.getPath());
            if (!result)
                return false;
        }
        return true;
    }

    public static boolean testFile(String path) throws IOException, EmptyProgram, TypeException {
        // Instantiate Lexer
        VSLLexer lexer = new VSLLexer(CharStreams.fromPath(Paths.get(path)));
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Instantiate Parser
        VSLParser parser = new VSLParser(tokens);

        // Parse
        Program ast = parser.program().out;

        Logger.info(ast.pp());

        String ir = ast.toIR().toString();
        Logger.info(ir);
        String llvmFilePath = "build/llvm/test/" + Paths.get(path).getFileName().toString().substring(0,
                                                                                                      Paths.get(path).getFileName().toString().length() - 3) + "ll";
        writeFile(llvmFilePath, ir);

        return true;
    }

    public static void writeFile(String path, String content) {
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }
}
