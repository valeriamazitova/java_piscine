package ex02;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileUtility {
    private Path currentPath;

    public FileUtility(Path currentPath) {
        this.currentPath = currentPath;
    }

    public void runCommand(String[] commandArgs) throws IOException {

        switch (commandArgs[0]) {
            case "ls":
                printListOfFilesAndFolders();
                break;
            case "cd":
                if (commandArgs.length > 1) {
                    changeDirectory(commandArgs[1]);
                } else {
                    System.err.println("Usage: cd <folder_name>");
                }
                break;
            case "mv":
                if (commandArgs.length != 3) {
                    System.out.println("Usage: mv <source_file> <destination_file/folder>");
                } else {
                    moveFileOrRename(commandArgs[1], commandArgs[2]);
                }
                break;
            default:
                System.out.println("Unknown command: " + commandArgs[0]);
                break;
        }
    }

    private void moveFileOrRename(String source, String destination) throws IOException {
        Path sourcePath = currentPath.resolve(source);
        Path destinationPath = currentPath.resolve(destination);

        if (Files.exists(sourcePath)) {
            if (Files.isDirectory(destinationPath)) {
                Files.move(sourcePath, destinationPath.resolve(sourcePath.getFileName()), StandardCopyOption.REPLACE_EXISTING);
            } else {
                Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } else {
            System.out.println("Source file not found: " + source);
        }
    }

    private void changeDirectory(String folderName) {
        while (folderName.startsWith("../")) {
            currentPath = currentPath.getParent();
            folderName = folderName.replaceFirst("../", "");
        }


        Path newPath = currentPath.resolve(folderName);
        if (Files.isDirectory(newPath)) {

            currentPath = newPath;

            System.out.println(currentPath);

        } else {
            System.err.println("Folder not found: " + folderName);
        }


    }

    private void printListOfFilesAndFolders() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(currentPath)) {
            for (Path entry : stream) {
                String type = Files.isDirectory(entry) ? "folder" : "file";
                long size = Files.isDirectory(entry) ? calculateFolderSize(entry) : Files.size(entry);
                System.out.println(entry.getFileName() + " " + size + " KB (" + type + ")");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long calculateFolderSize(Path folder) throws IOException {
        long size = 0;
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    size += calculateFolderSize(entry);
                } else {
                    size += Files.size(entry);
                }
            }
        }
        return size / 1024; // Convert bytes to KB
    }
}