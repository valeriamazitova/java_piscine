package ex00.ImagesToChar.src.java.edu.school21.printer.app;

import ex00.ImagesToChar.src.java.edu.school21.printer.logic.FilePrinter;

class Program {
    public static void main(String[] args) {

        String white_pixel = getWhitePixelFromArgs(args);

        String black_pixel = getBlackPixelFromArgs(args);

        String filePath = getFilePathFromArgs(args);



        FilePrinter filePrinter = new FilePrinter(white_pixel, black_pixel, filePath);

        filePrinter.openFileAndPrint();

        filePrinter.printCharArray();
    }

    private static String getWhitePixelFromArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided.");
            System.exit(-1);
        }
        String value = null;

        if (args[1].startsWith("--white=")) {
            value = args[1].substring("--white=".length());

            if (value.length() > 1) {
                System.err.println("White pixel is not a char.");
                System.exit(-1);
            }
        }

        return value;
    }

    private static String getBlackPixelFromArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided.");
            System.exit(-1);
        }
        String value = null;

        if (args[2].startsWith("--black=")) {
            value = args[2].substring("--black=".length());

            if (value.length() > 1) {
                System.err.println("Black pixel is not a char.");
                System.exit(-1);
            }
        }

        return value;
    }

    private static String getFilePathFromArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided.");
            System.exit(-1);
        }
        String value = null;

        if (args[3].startsWith("--filePath=")) {
            value = args[3].substring("--filePath=".length());
        }

        return value;
    }
}