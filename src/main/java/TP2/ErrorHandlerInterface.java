package TP2;

public interface ErrorHandlerInterface
{
    default void exitWithMessage(String message)
    {
        System.err.println("ERROR: " + message);
        
        System.exit(1);
    }
    
    void checkError();
}
