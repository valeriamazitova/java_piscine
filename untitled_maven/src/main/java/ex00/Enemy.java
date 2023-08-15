package ex00;

import java.util.Random;

public class Enemy {
    private int x;
    private int y;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void move(GameMap map) {
        map.map[x][y] = '.';
        GameMap.Direction[] directions = GameMap.Direction.values();
        Random random = new Random();
        GameMap.Direction randomDirection = directions[random.nextInt(directions.length)];
        switch (randomDirection) {
            case UP:
                if (x > 0) {
                    x--;
                }
                break;
            case DOWN:
                if (x < map.mapSize - 1) {
                    x++;
                }
                break;
            case LEFT:
                if (y > 0) {
                    y--;
                }
                break;
            case RIGHT:
                if (y < map.mapSize - 1) {
                    y++;
                }
                break;
        }
        map.map[x][y] = 'x';
    }
}
