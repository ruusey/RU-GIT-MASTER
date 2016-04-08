package finalproj;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteLoader {
   static BufferedImage spriteSheet;
   
   int width;
   int height;
   int rows;
   int columns;
   BufferedImage[] sprites;
   public static final int TILE_SIZE=64;
   public SpriteLoader(int width, int height, int rows, int columns) throws IOException {
      this.width = width;
      this.height = height;
      this.rows = rows;
      this.columns = columns;
      
      spriteSheet = loadSprite("src/main/java/assets/finalSprites.png");   
      
   }
   public BufferedImage getSprite(int xGrid, int yGrid) {

       if (spriteSheet == null) {
           spriteSheet = loadSprite("src/main/java/assets/finalSprites.png");
       }

       return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
   }
   public static BufferedImage loadSprite(String path){
	   try {
		return ImageIO.read(new File("src/main/java/assets/finalSprites.png"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;   
   }

   
}