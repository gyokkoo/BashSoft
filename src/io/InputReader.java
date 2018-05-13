package io;

import staticData.SessionData;

import java.io.IOException;
import java.util.Scanner;

public class InputReader {

    private static final String END_COMMAND = "quit";

    public static void readCommands() {
        OutputWriter.writeMessage(SessionData.currentPath + " > ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!END_COMMAND.equals(input)) {
            try {
                CommandInterpreter.interpretCommand(input);
            } catch (IOException e) {
                OutputWriter.displayException(e.getMessage());
            }
            OutputWriter.writeMessage(SessionData.currentPath + " > ");

            input = scanner.nextLine();
        }
    }
}
