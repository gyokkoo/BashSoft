package org.io;

import org.annotations.Alias;
import org.annotations.Inject;
import org.contracts.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Objects;

public class CommandInterpreter implements Interpreter{

    private static final String COMMANDS_LOCATION = "src/org/io/commands";
    private static final String COMMANDS_PACKAGE = "org.io.commands.";

    private ContentComparer tester;
    private AsynchDownloader downloadManager;
    private DirectoryManager ioManager;
    private Database repository;

    public CommandInterpreter(ContentComparer tester,
                              AsynchDownloader downloadManager,
                              DirectoryManager ioManager,
                              Database repository) {
        this.tester = tester;
        this.downloadManager = downloadManager;
        this.ioManager = ioManager;
        this.repository = repository;
    }

    public void interpretCommand(String input) {
        String[] data = input.split("\\s+");
        String commandName = data[0].toLowerCase();
        try {
            Executable command = parseCommand(input, data, commandName);
            command.execute();
        } catch (Throwable t) {
            OutputWriter.displayException(t.getMessage());
        }
    }

    private Executable parseCommand(String input, String[] data, String command) {
        File commandsFolder = new File(COMMANDS_LOCATION);
        Executable executable = null;
        for (File file : Objects.requireNonNull(commandsFolder.listFiles())) {
            if (!file.isFile() || !file.getName().endsWith(".java")) {
                continue;
            }

            try {
                String className = file.getName().substring(0, file.getName().lastIndexOf('.'));
                Class<Executable> exetuableClass = (Class<Executable>)
                        Class.forName(COMMANDS_PACKAGE + className);

                if (!exetuableClass.isAnnotationPresent(Alias.class)) {
                    continue;
                }

                Alias alias = exetuableClass.getAnnotation(Alias.class);
                String value = alias.value();
                if (!value.equalsIgnoreCase(command)) {
                    continue;
                }

                Constructor executableConstructor =
                        exetuableClass.getConstructor(String.class, String[].class);
                executable = (Executable) executableConstructor.newInstance(input, data);
                this.injectDependencies(executable, exetuableClass);
                return executable;
            } catch (ReflectiveOperationException rfe) {
                rfe.printStackTrace();
            }
        }

        return executable;
    }

    private void injectDependencies(Executable executable, Class<Executable> exetuableClass)
            throws ReflectiveOperationException {
        Field[] exeFields = exetuableClass.getDeclaredFields();
        for (Field fieldToSet : exeFields) {
            if (!fieldToSet.isAnnotationPresent(Inject.class)) {
                continue;
            }

            fieldToSet.setAccessible(true);
            Field[] theseFields = CommandInterpreter.class.getDeclaredFields();
            for (Field thisField : theseFields) {
                if (!thisField.getType().equals(fieldToSet.getType())) {
                    continue;
                }

                thisField.setAccessible(true);
                fieldToSet.set(executable, thisField.get(this));
            }
        }
    }
}
