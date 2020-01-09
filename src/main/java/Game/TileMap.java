package Game;

import javafx.scene.image.*;

import java.nio.ByteBuffer;

public class TileMap extends ImageView {
    private final static int TILE = GameManager.TILE;

    private int[][] map;
    private final int RESOLUTION_WIDTH;
    private final int RESOLUTION_HEIGHT;
    private final int TILE_LENGTH_X;
    private final int TILE_LENGTH_Y;
    private final int OFFSET_X;
    private final int OFFSET_Y;
    private  boolean OFFSET_X_FLAG;
    private  boolean OFFSET_Y_FLAG;

    public TileMap(int mapWidth , int mapHeight){
        RESOLUTION_WIDTH  = mapWidth;
        RESOLUTION_HEIGHT  = mapHeight;

        TILE_LENGTH_X = (int) Math.ceil(mapWidth / TILE);
        TILE_LENGTH_Y = (int) Math.ceil(mapHeight / TILE);

        OFFSET_X = TILE_LENGTH_X * TILE - RESOLUTION_WIDTH;
        OFFSET_Y = TILE_LENGTH_Y * TILE - RESOLUTION_HEIGHT;

        OFFSET_X_FLAG = OFFSET_X != 0;
        OFFSET_Y_FLAG = OFFSET_Y != 0;

        map = generateMapArray();
        repaint();
    }

    public void repaint() {
        Image tileset = new Image("/sheet.png");
        PixelReader tilereader = tileset.getPixelReader();

        byte[] buffer = new byte[TILE * TILE * 4];
        WritablePixelFormat<ByteBuffer> picFormat = WritablePixelFormat.getByteBgraInstance();

        WritableImage paintedMap = new WritableImage(RESOLUTION_WIDTH , RESOLUTION_HEIGHT);
        PixelWriter tileWriter = paintedMap.getPixelWriter();

        for (int x = 0; x < TILE_LENGTH_X; x++) {
            for (int y = 0; y < TILE_LENGTH_Y; y++ ) {
                switch(map[y][x]) {
                    case 0:
                        tilereader.getPixels(0 , 0 , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 1:
                        tilereader.getPixels(TILE, 0 , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 2:
                        tilereader.getPixels(TILE , TILE , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 3:
                        tilereader.getPixels(3 * TILE , TILE , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 4:
                        tilereader.getPixels(2 * TILE , TILE , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 5:
                        tilereader.getPixels(2 * TILE , 0 , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 6:
                        tilereader.getPixels(3 * TILE , 0 , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                    case 7:
                        tilereader.getPixels(0 , TILE , TILE , TILE , picFormat , buffer , 0 , 256);
                        break;
                }
                if(y == TILE_LENGTH_Y - 1 & OFFSET_Y_FLAG){
                    tileWriter.setPixels(x * TILE , y * TILE, TILE , OFFSET_Y , picFormat , buffer , 0 , 256);
                }
                else {
                    tileWriter.setPixels(x * TILE , y * TILE, TILE , TILE , picFormat , buffer , 0 , 256);
                }
            }
        }
        this.setImage(paintedMap);
    }

    private int[][] generateMapArray() {
        return new int[][] {
                        {0 , 0 , 0 , 0 , 0 , 5 , 1 , 6 , 0 , 0 },
                        {0 , 5 , 1 , 6 , 0 , 2 , 0 , 2 , 5 , 1 },
                        {0 , 2 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {0 , 2 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {0 , 2 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {0 , 2 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {0 , 2 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {0 , 2 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {1 , 3 , 0 , 2 , 0 , 2 , 0 , 2 , 2 , 0 },
                        {0 , 0 , 0 , 4 , 1 , 3 , 0 , 4 , 3 , 0 },};
    }
}