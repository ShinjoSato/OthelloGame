import othello.*;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panels extends JPanel implements MouseListener, MouseMotionListener, OthConstant{
	private String[] PanelName = { "home", "play", "set" };

    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void pc(String str){}
    public void paintComponent(Graphics g){}

    public Graphics createBoard(Graphics g, int size, int x, int y, int width, int height){
        //g.setColor(Color.green);
        //g.fillRect(x, y, width, height);
        g.drawImage(board[0].getImage(), x, y, width, height, this);
        g.setColor(Color.white);
        g.drawRect(x, y, width, height);
        for(int i=0; i<size; i++){
            g.drawLine(x + (width/size) * i, y, x + (width/size) * i, y + height);
            g.drawLine(x, y + (height/size) * i, x+width, y+(height/size)*i);
        }
        return g;
    }

    public JButton createButton(String name, int font, Color color, int size, int x, int y, int width, int height, boolean isAreaFiiled, boolean isBorder){
    	JButton button = new JButton(name);
        button.setFont(new Font("Arial", font, size));
        button.setForeground(color);
        button.setBounds(x, y, width, height);
        button.setContentAreaFilled(isAreaFiiled);
        button.setBorderPainted(isBorder);
        return button;
    }

    public JButton createButton(String name, int font, int size, int x, int y, int width, int height, boolean isAreaFiiled, boolean isBorder){
        return createButton(name, font, Color.black, size, x, y, width, height, isAreaFiiled, isBorder);
    }

    public JButton createButton(String name, Color color, int size, int x, int y, int width, int height){
    	return createButton(name, Font.ITALIC, color, size, x, y, width, height, false, false);
    }

    public JButton createButton(String name, int size, int x, int y, int width, int height){
        return createButton(name, Font.ITALIC, Color.black, size, x, y, width, height, false, false);
    }

    class SetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("SETTINGを押しました..");
            pc(PanelName[2]);
        }
    }

    class PlayListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Playを押しました..");
            pc(PanelName[1]);
        }
    }

    class HomeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Homeを押しました..");
            pc(PanelName[0]);
        }
    }

    class MainActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Othelloを押しました。");
        }
    }

    class WorldListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Worldを押しました。");
        }
    }   
}