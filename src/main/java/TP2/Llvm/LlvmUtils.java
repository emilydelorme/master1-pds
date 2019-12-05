package TP2.Llvm;

public class LlvmUtils {

    private LlvmUtils() {}

    public static String globalInit() {
        return "; Target\n" +
                "target triple = \"x86_64-unknown-linux-gnu\"\n" + // Change this if you want to compile for
                // another OS
                "; External declaration of the printf function\n" +
                "declare i32 @printf(i8* noalias nocapture, ...)" +
                "declare i32 @scanf(i8* noalias, nocapture, ...)" +
                "\n\n";
    }
}
