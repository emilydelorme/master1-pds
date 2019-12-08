package TP2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import TP2.ASD.Program;
import TP2.Llvm.InstructionHandler;
import TP2.exceptions.EmptyProgram;
import TP2.exceptions.TypeException;

public class Main
{
    public static void main(String[] args)
    {

        try
        {
            // Set input
            CharStream input;

            if (args.length == 0)
            {
                // From standard input
                input = CharStreams.fromStream(System.in);
            } else
            {
                // From file set in first argument
                input = CharStreams.fromPath(Paths.get(args[0]));
            }

            // Instantiate Lexer
            VSLLexer lexer = new VSLLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Instantiate Parser
            VSLParser parser = new VSLParser(tokens);

            // Parse
            Program ast = parser.program().out;

            // Pretty-print the program (to debug parsing, if you implemented it!)
            System.out.println("========================================"
                               + "\n              PRETTY CODE"
                               + "\n========================================");

            System.out.println(ast.pp());

            System.out.println("========================================"
                              + "\n               LLVM CODE"
                              + "\n========================================");

            // Compute LLVM IR from the ast
            try
            {
                InstructionHandler ir = ast.toIR();

                // Save LLVM IR in a file
                if (args.length >= 2)
                {
                    Files.createDirectories(Paths.get("build/llvm/"));
                    writeToFile("build/llvm/" + args[1], ir.toString());
                }
                
                // Output LLVM IR
                System.out.println(ir);
            } catch (TypeException | EmptyProgram e)
            {
                e.printStackTrace(); // Useful for developping, not for the ``end users''!
                System.err.println(e.getMessage());
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String path, String content) {

        try(FileOutputStream outputStream =  new FileOutputStream(path)) {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
