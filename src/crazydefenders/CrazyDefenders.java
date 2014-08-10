package crazydefenders;

import javax.swing.JFrame;


public class CrazyDefenders extends JFrame implements Defaults {

    public CrazyDefenders()
    {
        add(new Ventana());
        setTitle("Crazy Defenders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(ANCHO, ALTO);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new CrazyDefenders();
    }
}