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
import javax.swing.ImageIcon;

public class HomePanel extends Panels{
    FrameBase fb;
    String str;
    private JButton b1,b2,b3,b4;

    private int size=8;
    private int xwidth=Parameter.xwidth.get(), ywidth=Parameter.ywidth.get();
    private int xzero=Parameter.xzero.get(), yzero=Parameter.yzero.get();
    int x1,y1,x2,y2;
    
    public HomePanel(FrameBase fb,String str){
        this.fb=fb;
        this.str=str;
        this.setName(str);
        this.setBackground(Color.darkGray);
        this.setLayout(null);

        this.setSize(1200, 675);//ここ変えたい
    }

    public void pc(String str){
        fb.changePanel((JPanel)this,str);
    }

    public void paintComponent(Graphics g){//ここの中で空白のマスを調整する
        super.paintComponent(g);

        ImageIcon image=backgroung[3];
        g.drawImage(image.getImage(), 0, 0, 1200, 675, this);

        g = createBoard(g, size, xzero, yzero, xwidth, ywidth);

        g.setColor(Color.black);
        
        Font font=new Font("Arial",Font.ITALIC,50);
        g.setFont(font);

        b1 = createButton("Othello", 50, xzero+(xwidth/size)*(size/2)-105, yzero, 210, 50);
        add(b1);
        
        b2 = createButton("~ Play ~", 40, xzero+70, yzero+(ywidth/size)*3, 260, 50);
        add(b2);

        b3 = createButton("~ Setting ~", 40, xzero+70, yzero+(ywidth/size)*4, 260, 50);
        add(b3);

        b4 = createButton("~ World ~", 40, xzero+70, yzero+(ywidth/size)*5, 260, 50);
        add(b4);

        setLayout(null);//この文章がないと"Othello"が大量発生してしまう
        b1.addActionListener(new MainActionListener());
        b2.addActionListener(new PlayListener());
        b3.addActionListener(new SetListener());
        b4.addActionListener(new WorldListener());
    }
}
