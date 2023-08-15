package ex00;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        int mapSize = 10;
//        int enemyCount = 2;
//        int wallsCount = 5;
        int enemyCount = getEnemyCountFromArgs(args);

        int wallsCount = getWallsCountFromArgs(args);

        int mapSize = getMapSizeFromArgs(args);

        GameMap gameMap = new GameMap(mapSize, enemyCount, wallsCount);

        Scanner scanner = new Scanner(System.in);



        while (true) {

            gameMap.printMap();
            if (gameMap.isGameOver()) {
                System.out.println("Game Over! You were caught by an enemy.");
                break;
            } else if (gameMap.isPlayerAtTarget()) {
                System.out.println("Congratulations! You reached the target.");
                break;
            }

            System.out.print("Enter your move (up/down/left/right): ");
            String input = scanner.nextLine();
            GameMap.Direction direction = parseDirection(input);
            if (direction != null) {
                gameMap.movePlayer(direction);
                gameMap.moveEnemies();
            } else {
                System.out.println("Invalid move. Please enter a valid direction.");
            }
            clearConsole();
        }

        scanner.close();
    }

    private static int getMapSizeFromArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided.");
            System.exit(-1);
        }

        int mapSize = Integer.parseInt(args[2].substring("--size=".length()));

        if (mapSize <= 0) {
            System.err.println("Map size cannot be less than zero.");
            System.exit(-1);
        }

        return mapSize;
    }

    private static int getWallsCountFromArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided.");
            System.exit(-1);
        }

        int wallsCount = Integer.parseInt(args[1].substring("--wallsCount=".length()));

        if (wallsCount <= 0) {
            System.err.println("Walls number cannot be less than zero.");
            System.exit(-1);
        }

        return wallsCount;
    }

    private static int getEnemyCountFromArgs(String[] args) {
        if (args.length == 0) {
            System.err.println("No arguments provided.");
            System.exit(-1);
        }

        int enemiesCount = Integer.parseInt(args[0].substring("--enemiesCount=".length()));

        if (enemiesCount <= 0) {
            System.err.println("Enemies number cannot be less than zero.");
            System.exit(-1);
        }

        return enemiesCount;
    }


    private static void clearConsole() {
        System.out.print("\033[H\033[2J");  // ANSI escape code to clear screen
        System.out.flush();
    }

    private static GameMap.Direction parseDirection(String input) {
        switch (input.toLowerCase()) {
            case "up":
                return GameMap.Direction.UP;
            case "down":
                return GameMap.Direction.DOWN;
            case "left":
                return GameMap.Direction.LEFT;
            case "right":
                return GameMap.Direction.RIGHT;
            default:
                return null;
        }
    }
}
