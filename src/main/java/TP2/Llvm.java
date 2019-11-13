package TP2;

import java.util.List;
import java.util.ArrayList;

// This file contains a simple LLVM IR representation
// and methods to generate its string representation

public class Llvm
{
    static public class IR
    {
        List<Instruction> header; // IR instructions to be placed before the code (global definitions)
        List<Instruction> code; // main code

        public IR(List<Instruction> header, List<Instruction> code)
        {
            this.header = header;
            this.code = code;
        }

        // append an other IR
        public IR append(IR other)
        {
            header.addAll(other.header);
            code.addAll(other.code);
            return this;
        }

        // append a code instruction
        public IR appendCode(Instruction inst)
        {
            code.add(inst);
            return this;
        }

        // append a code header
        public IR appendHeader(Instruction inst)
        {
            header.add(inst);
            return this;
        }

        // Final string generation
        public String toString()
        {
            // This header describe to LLVM the target
            // and declare the external function printf
            StringBuilder r = new StringBuilder();

            // Append Base headers
            r.append("; Target\n");
            r.append("target triple = \"x86_64-unknown-linux-gnu\"\n"); // Change this if you want to compile for
                                                                        // another OS
            r.append("; External declaration of the printf function\n");
            r.append("declare i32 @printf(i8* noalias nocapture, ...)");
            r.append("declare i32 @scanf(i8* noalias, nocapture, ...)");
            r.append("\n\n");

            header.forEach(r::append);

            r.append("\n; Actual code begins\n\n");

            // We create the function main
            // if function don't work uncomment this:
            // r.append("define i32 @main() {\n");

            code.forEach(r::append);

            // if function don't work uncomment this:
            // r.append("}\n");
            return r.toString();
        }
    }

    // Returns a new empty list of instruction, handy
    static public List<Instruction> empty()
    {
        return new ArrayList<>();
    }

    // LLVM Types
    static public abstract class Type
    {
        public abstract String toString();
    }

    static public class Int extends Type
    {
        public String toString()
        {
            return "i32";
        }
    }

    static public class Void extends Type
    {
        public String toString()
        {
            return "void";
        }
    }

    // LLVM IR Instructions
    static public abstract class Instruction
    {
        @Override
        public abstract String toString();
    }

    static public class Add extends Instruction
    {
        Type type;
        String left;
        String right;
        String lvalue;

        public Add(Type type, String left, String right, String lvalue)
        {
            this.type = type;
            this.left = left;
            this.right = right;
            this.lvalue = lvalue;
        }

        @Override
        public String toString()
        {
            return lvalue + " = add " + type + " " + left + ", " + right + "\n";
        }
    }

    static public class Sub extends Instruction
    {
        Type type;
        String left;
        String right;
        String lvalue;

        public Sub(Type type, String left, String right, String lvalue)
        {
            this.type = type;
            this.left = left;
            this.right = right;
            this.lvalue = lvalue;
        }

        @Override
        public String toString()
        {
            return lvalue + " = sub " + type + " " + left + ", " + right + "\n";
        }
    }

    static public class Mul extends Instruction
    {
        Type type;
        String left;
        String right;
        String lvalue;

        public Mul(Type type, String left, String right, String lvalue)
        {
            this.type = type;
            this.left = left;
            this.right = right;
            this.lvalue = lvalue;
        }

        @Override
        public String toString()
        {
            return lvalue + " = mul " + type + " " + left + ", " + right + "\n";
        }
    }

    static public class Div extends Instruction
    {
        Type type;
        String left;
        String right;
        String lvalue;

        public Div(Type type, String left, String right, String lvalue)
        {
            this.type = type;
            this.left = left;
            this.right = right;
            this.lvalue = lvalue;
        }

        @Override
        public String toString()
        {
            return lvalue + " = udiv " + type + " " + left + ", " + right + "\n";
        }
    }

    static public class Return extends Instruction
    {
        Type type;
        String value;

        public Return(Type type, String value)
        {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return "ret " + type + " " + value + "\n";
        }
    }

    static public class Alloca extends Instruction
    {
        Type type;
        String value;

        public Alloca(Type type, String lvalue)
        {
            this.type = type;
            this.value = lvalue;
        }

        @Override
        public String toString()
        {
            return value + " = alloca " + type;
        }
    }

    static public class Store extends Instruction
    {
        Type type;
        String value;
        String pointer;

        public Store(Type type, String value, String pointer)
        {
            this.type = type;
            this.value = value;
            this.pointer = pointer;
        }

        @Override
        public String toString()
        {
            return "store " + type + " " + value + ", " + type + "* " + pointer;
        }
    }

    static public class ifStatement extends Instruction
    {

        @Override
        public String toString()
        {
            return null;
        }
    }

    static public class ifElseStatement extends Instruction
    {

        @Override
        public String toString()
        {
            return null;
        }
    }

    static public class whileStatemet extends Instruction
    {

        @Override
        public String toString()
        {
            return null;
        }
    }
}
