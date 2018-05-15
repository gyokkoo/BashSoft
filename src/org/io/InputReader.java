package org.io;

import org.staticData.SessionData;

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
            this.interpreter.interpretCommand(input);
            OutputWriter.writeMessage(SessionData.currentPath + " > ");

            input = scanner.nextLine();
        }
    }
}
