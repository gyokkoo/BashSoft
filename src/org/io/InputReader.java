package org.io;

import org.staticData.SessionData;

import java.io.IOException;
import java.util.Scanner;

public class InputReader {

    private static final String END_COMMAND = "quit";

    private CommandInterpreter interpreter;

    public InputReader(CommandInterpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void readCommands() {
        OutputWriter.writeMessage(SessionData.currentPath + " > ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!END_COMMAND.equals(input)) {
            try {
                this.interpreter.interpretCommand(input);
            } catch (IOException e) {
                OutputWriter.displayException(e.getMessage());
            }
            OutputWriter.writeMessage(SessionData.currentPath + " > ");

            input = scanner.nextLine();
        }
    }
}
