package org;

import org.contracts.*;
import org.io.CommandInterpreter;
import org.io.IOManager;
import org.io.InputReader;
import org.io.OutputWriter;
import org.judge.Tester;
import org.network.Downloader;
import org.repository.RepositoryFilter;
import org.repository.RepositorySorter;
import org.repository.StudentsRepository;

public class Program {

    public static void main(String[] args) {
        org.contracts.ContentComparer contentComparer = new Tester();
        Downloader downloadManager = new Downloader();
        DirectoryManager ioManager = new IOManager();
        DataSorter repositorySorter = new RepositorySorter();
        DataFilter repositoryFilter = new RepositoryFilter();
        Database repository = new StudentsRepository(repositoryFilter, repositorySorter);

        Interpreter interpreter = new CommandInterpreter(
                contentComparer, repository, downloadManager, ioManager);
        Reader reader = new InputReader(interpreter);

        try {
            reader.readCommands();
        } catch (Exception e) {
            OutputWriter.displayException(e.getMessage());
        }
    }
}
