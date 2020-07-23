import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        JFrame obj = new JFrame("Snake Game");
        Gameplay gameplay = new Gameplay();

        obj.setSize(905, 700);
        centreWindow(obj);
        obj.setBackground(Color.DARK_GRAY);
        obj.setResizable(false);
        obj.add(gameplay);

        //gameplay has to be added before
        obj.setVisible(true);
        obj.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        //(int) is "cast" for primitives, changing the result to another type.
        int xcentre = (int) (dimension.getWidth()/2 - frame.getWidth()/2 );
        int ycentre = (int) (dimension.getHeight()/2 - frame.getHeight()/2 );
        frame.setLocation(xcentre, ycentre);
    }


}
