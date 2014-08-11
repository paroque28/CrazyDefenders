package crazydefenders;

import java.awt.Image;

public class Sprite {

        private boolean visible;
        private Image image;
        private Image leftImage;
        protected int x;
        protected int y;
        protected boolean dying;
        protected int dx;
        protected int dy;
        protected int direction;

        public Sprite() {
            visible = true;
            direction = 1;
        }
        
        public void setDirection(int x){
        	if (x != direction)
        		direction = x; 			
        }
        public int getDirection(){
        	return direction;
        }
        
        public void die() {
            visible = false;
        }

        public boolean isVisible() {
            return visible;
        }

        protected void setVisible(boolean visible) {
            this.visible = visible;
        }
        
        public void setImageLeft(Image imageL){
        	this.leftImage = imageL;
        }
        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
        	Image temp = null;
        	if (direction >= 0)
        		temp = image;
        	if (direction < 0)
        		temp = leftImage;
        	return temp;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setDying(boolean dying) {
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }
}
