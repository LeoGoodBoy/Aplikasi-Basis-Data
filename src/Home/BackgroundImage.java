/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Home;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author basisd03
 */
public class BackgroundImage extends JPanel {

    private Image image;
    private String folder;

    public BackgroundImage() {

        folder = System.getProperty("user.dir") + File.separator + "images";
        new File(folder).mkdir();
        Toolkit tool = Toolkit.getDefaultToolkit();
        Image ig = tool.getImage(folder + File.separator + "bg" + ".jpg");
        image = new ImageIcon(ig).getImage();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D gd = (Graphics2D) grphcs.create();
        gd.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        gd.dispose();
    }
}
