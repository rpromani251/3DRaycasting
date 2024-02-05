package src;

import java.awt.Color;
import java.awt.Graphics;

import util.Vec2;

public class Map {
    public static int[] map;
    public int mapX, mapY, mapS;

    public Map(int mapX, int mapY, int mapS) {
        
        this.mapX = mapX;
        this.mapY = mapY;
        this.mapS = mapS;

        Player.mapX = this.mapX;
        Player.mapY = this.mapY;
        Player.mapS = this.mapS;


        map = new int[] {
            1, 1, 1, 1, 1, 1, 1, 1, // 1
            1, 0, 1, 0, 0, 0, 0, 1, // 2
            1, 0, 1, 0, 0, 0, 0, 1, // 3
            1, 0, 1, 0, 0, 0, 0, 1, // 4
            1, 0, 0, 0, 0, 0, 0, 1, // 5
            1, 0, 0, 0, 0, 1, 0, 1, // 6
            1, 0, 0, 0, 0, 0, 0, 1, // 7
            1, 1, 1, 1, 1, 1, 1, 1, // 8
        };
    }

    public void draw(Graphics g) {
        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                // Create variables for correct sizing of tiles
                int x0 = x*mapS, y0 = y*mapS;

                // If wall --> white, else --> black
                if (map[y*mapX+x]==1) {
                    g.setColor(new Color(255, 255, 255));
                }
                else {
                    g.setColor(new Color(0, 0, 0));
                }

                // Draw Tiles
                g.fillRect(x0, y0, mapS-1, mapS-1);
            }
        }
    }
    
}
