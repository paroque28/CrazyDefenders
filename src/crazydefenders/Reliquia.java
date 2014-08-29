package crazydefenders;

import javax.swing.ImageIcon;

public class Reliquia extends Sprite {

	public Reliquia(int x, int y) {
		this.setX(x);
		this.setY(y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/images/heart.png"));

        setDimensions(ii.getImage().getWidth(null), ii.getImage().getHeight(null)); 

        setImage(ii.getImage());
	}

}
