package ex00;

import com.diogonunes.jcolor.Attribute;

import java.util.Random;

import static com.diogonunes.jcolor.Ansi.colorize;
import static com.diogonunes.jcolor.Attribute.*;

public class GameMap {
    char[][] map;
    int mapSize;
    private int playerX;
    private int playerY;
    private int targetX;
    private int targetY;
    private final Enemy[] enemies;

    public GameMap(int size, int enemyCount, int wallCount) {
        mapSize = size;
        map = new char[size][size];
        enemies = new Enemy[enemyCount];
        initializeMap();
        initializePlayer();
        initializeTarget();
        initializeEnemies();
        initializeObstacles(wallCount);
    }

    private void initializeMap() {
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = '.';
            }
        }
    }

    private void initializePlayer() {
        playerX = getRandomCoordinate();
        playerY = getRandomCoordinate();
        map[playerX][playerY] = 'o';
    }

    private void initializeTarget() {
        targetX = getRandomCoordinate();
        targetY = getRandomCoordinate();
        map[targetX][targetY] = 'O';
    }

    private void initializeEnemies() {
        for (int i = 0; i < enemies.length; i++) {
            int enemyX = getRandomCoordinate();
            int enemyY = getRandomCoordinate();
            enemies[i] = new Enemy(enemyX, enemyY);
            map[enemyX][enemyY] = 'x';
        }
    }

    private void initializeObstacles(int wallsNumber) {
        for (int i = 0; i < wallsNumber; i++) {
            int enemyX = getRandomCoordinate();
            int enemyY = getRandomCoordinate();
            map[enemyX][enemyY] = '#';
        }
    }

    private int getRandomCoordinate() {
        return new Random().nextInt(mapSize);
    }

    public void printMap() {
        Attribute[] fontFormat = new Attribute[]{BLACK_TEXT(), YELLOW_BACK(), BOLD()};
        Attribute[] playerFormat = new Attribute[]{BLACK_TEXT(), GREEN_BACK(), BOLD()};
        Attribute[] enemyFormat = new Attribute[]{BLACK_TEXT(), RED_BACK(), BOLD()};
        Attribute[] targetFormat = new Attribute[]{BLACK_TEXT(), BLUE_BACK(), BOLD()};
        Attribute[] obstacleFormat = new Attribute[]{BLACK_TEXT(), MAGENTA_BACK(), BOLD()};


        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                if (map[i][j] == '.') {
                    System.out.print(colorize(" ", fontFormat));
                } else if (map[i][j] == 'o') {
                    System.out.print(colorize(String.valueOf(map[i][j]), playerFormat));
                } else if (map[i][j] == 'O') {
                    System.out.print(colorize(String.valueOf(map[i][j]), targetFormat));
                } else if (map[i][j] == 'x') {
                    System.out.print(colorize(String.valueOf(map[i][j]), enemyFormat));
                } else if (map[i][j] == '#') {
                    System.out.print(colorize(String.valueOf(map[i][j]), obstacleFormat));
                }

            }
            System.out.println();
        }
    }

    public boolean isGameOver() {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == playerX && enemy.getY() == playerY) {
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerAtTarget() {
        return playerX == targetX && playerY == targetY;
    }

    public void movePlayer(Direction direction) {
        map[playerX][playerY] = '.';
        switch (direction) {
            case UP:
                if (playerX > 0) {
                    playerX--;
                }
                break;
            case DOWN:
                if (playerX < mapSize - 1) {
                    playerX++;
                }
                break;
            case LEFT:
                if (playerY > 0) {
                    playerY--;
                }
                break;
            case RIGHT:
                if (playerY < mapSize - 1) {
                    playerY++;
                }
                break;
        }
        map[playerX][playerY] = 'o';
    }

    public void moveEnemies() {
        for (Enemy enemy : enemies) {
            enemy.move(this);
        }
    }

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
