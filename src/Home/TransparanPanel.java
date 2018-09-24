
package Home;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
/**
 *
 * @author Rafael Aweng
 */
public class TransparanPanel extends JPanel {
    private Color color;
    public TransparanPanel() {
        setOpaque(false);
        color = new Color(0, 0, 0, 10);
    }
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        color = new Color(0, 0, 0, 10);
        repaint();
    }
    @Override
    protected void paintComponent(Graphics grp) {
        super.paintComponent(grp);
        Graphics2D d = (Graphics2D) grp.create();
        d.setColor(color);
        d.fillRect(0, 0, getWidth(), getHeight());
        d.dispose();
    }
}
