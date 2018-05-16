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
    private DataSorter sorter;
    private DataFilter filter;
    private Database repository;

    public CommandInterpreter(ContentComparer tester,
                              AsynchDownloader downloadManager,
                              DirectoryManager ioManager,
                              DataSorter sorter,
                              DataFilter filter,
                              Database repository) {
        this.tester = tester;
        this.downloadManager = downloadManager;
        this.ioManager = ioManager;
        this.sorter = sorter;
        this.filter = filter;
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
        /*
        switch (command) {
            case "open":
                return new OpenFileCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "mkdir":
                return new MakeDirectoryCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "ls":
                return new TraverseFoldersCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "cmp":
                return new CompareFilesCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "cdrel":
                return new ChangeRelativePathCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "cdabs":
                return new ChangeAbsolutePathCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "readdb":
                return new ReadDatabaseCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "help":
                return new GetHelpCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "show":
                return new ShowCourseCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "filter":
                return new PrintFilteredStudentsCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "order":
                return new PrintOrderedStudentsCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "download":
                return new DownloadFileCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "downloadasynch":
                return new DownloadAsynchCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "dropdb":
                return new DropDatabaseCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            case "display":
                return new DisplayCommand(input, data, this.repository,
                        this.tester, this.inputOutputManager, this.downloadManager);
            default:
                throw new InvalidInputException(input);
        }
        */

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
