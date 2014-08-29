package crazydefenders;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Sprite implements Defaults {

        private boolean visible;
        private Image image;
        private Image leftImage;
        protected int x;
        protected int y;
        protected int width, height;



		protected boolean dying;
        protected int dx;
        protected int dy;
        protected int direction;
        protected int directiony;
        protected int largo;
        private final String explosion = "/images/explosion.png";
        

        public Sprite() {
            visible = true;
            direction = 1;
            directiony = 1;
        }
        
   public void setLargo(int largo) {
    		this.largo = largo;
    	}

    public void setDimensions(int x, int y){
			width = x;
			height = y;
		}
	public int getWidth() { 
		return width;
	}
	public int getHeight() {
		return height;
	}
        public void setDirection(int x){
        	if (x == 10)
        	{
        		Random gen = new Random();
        		//if (gen.nextBoolean())        		
        		x = -direction;
        		if (gen.nextBoolean())   
        			directiony = -directiony;
        	}
        	if (x != direction)
        		direction = x;
        }
        public int getDirection(){
        	return direction;
        }
        public int getDirectionY(){
        	return directiony;
        }
        
        public boolean isVisible() {
            return visible;
        }

        protected void setVisible(boolean visible) {
            this.visible = visible;
        }
        
        public void setImage(Image image) {
            this.image = image;
        }

        public void setImageLeft(Image imageL){
			this.leftImage = imageL;
		}
		public Image getImage() {
        	Image temp = null;
        	temp = image;
        	if (direction < 0)
        		if (leftImage != null)
        		{
        			temp = leftImage;
        			}
        	return temp;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getX() {
		    return x;
		}
		public void setY(int y) {
            if (y > ALTO-height-20)
            {
            	this.y = ALTO-height-20;
            }
            else
            {
            	if (y < 0)
            	{
            		this.y = 0;
            	}
            	else
            	{
            		this.y = y;
            	}
            }
            
        }
        public int getY() {
            return y;
        }

        public void die() {
		    visible = false;
		}
		public void setDying(boolean dying) {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(explosion));
			setImage(ii.getImage());
			setImageLeft(ii.getImage());			
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }
}
