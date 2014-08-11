package crazydefenders;

import javax.swing.ImageIcon;


public class Disparo extends Sprite {

    private String shot = "/images/shot.png";
    private String shotLeft = "/images/shotLeft.png";

    public Disparo() {
    }
    
    public Disparo(int x, int y, int direction) {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        ImageIcon iii = new ImageIcon(this.getClass().getResource(shotLeft));
        setImage(ii.getImage());
        setImageLeft(iii.getImage());
        
        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 
        
        setX(x);
        setY(y);
        setDirection(direction);
    }
}