import io.OutputWriter;

public class Program {

    public static void main(String[] args) {
        // StudentsRepository.initializeData();
        // StudentsRepository.getStudentsByCourse("Unity");

        /*
        String test1path = "test1.txt";
        String test2path = "test2.txt";
        String test3path = "test3.txt";
        Tester.compareContent(test2path, test3path);
        */

        // IOManager.createDirectoryInCurrentFolder("Hi123");

        OutputWriter.changeCurrentDirAbsolute("C:\\Program Files (x86)");
        OutputWriter.traverseDirectory(1);
    }
}
