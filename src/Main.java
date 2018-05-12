import java.io.File;
import java.util.LinkedList;

public class Main {

    public static void traverseDirectory(String path) {
        LinkedList<File> subFolders = new LinkedList<>();
        File root = new File(path);

        subFolders.add(root);
        while(subFolders.size() != 0) {
            File currentFolder = subFolders.removeFirst();
            if (currentFolder.listFiles() != null) {
                for (File file : currentFolder.listFiles()) {
                    if (file.isDirectory()) {
                        subFolders.add(file);
                    }
                }
            }

            System.out.println(currentFolder.toString());
        }
    }

    public static void main(String[] args) {
        traverseDirectory("C:\\Program Files (x86)");
    }
}
