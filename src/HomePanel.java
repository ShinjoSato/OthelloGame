import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.geom.*;
import javax.swing.JComponent;
import java.lang.Thread;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePanel extends JPanel implements MouseListener, MouseMotionListener, OthPanel, OthConstant{
    FrameBase fb;
    String str;
    private JButton b1,b2,b3,b4;

    private int size=8;
    //int xwidth=400,ywidth=400;
    //int xzero=200,yzero=44;
    int x1,y1,x2,y2;
    
    public HomePanel(FrameBase fb,String str){
        this.fb=fb;
        this.str=str;
        this.setName(str);
        this.setBackground(Color.darkGray);
        this.setLayout(null);

        this.setSize(800,600);//ここ変えたい
    }
    public void pc(String str){
        fb.changePanel((JPanel)this,str);
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void paintComponent(Graphics g){//ここの中で空白のマスを調整する
        super.paintComponent(g);

        Graphics2D g2=(Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));
        Ellipse2D.Double e2d=new Ellipse2D.Double();
        int b=0,w=0;
        g.setColor(Color.green);
        g.fillRect(xzero,yzero,xwidth,ywidth);
        g.setColor(Color.white);
        g.drawRect(xzero,yzero,xwidth,ywidth);
        for(int i=0;i<size;i++){
            g.drawLine(xzero+(xwidth/size)*i,yzero,xzero+(xwidth/size)*i,yzero+ywidth);
             g.drawLine(xzero,yzero+(ywidth/size)*i,xzero+xwidth,yzero+(ywidth/size)*i);
        }
        g.setColor(Color.black);
        
        Font font=new Font("Arial",Font.ITALIC,50);
        g.setFont(font);

        b1=new JButton("Othello");
        //button.setBackground(Color.white);
        b1.setFont(new Font("Arial",Font.ITALIC,50));
        b1.setBounds(xzero+(xwidth/size)*(size/2)-105,yzero,210,50);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        add(b1);
        
        b2=new JButton("~ Play ~");
        b2.setFont(new Font("Arial",Font.ITALIC,40));
        b2.setBounds(xzero+70,yzero+(ywidth/size)*3,260,50);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        add(b2);

        b3=new JButton("~ Setting ~");
        b3.setFont(new Font("Arial",Font.ITALIC,40));
        b3.setBounds(xzero+70,yzero+(ywidth/size)*4,260,50);
        b3.setContentAreaFilled(false);
        b3.setBorderPainted(false);
        add(b3);

        b4=new JButton("~ World ~");
        b4.setFont(new Font("Arial",Font.ITALIC,40));
        b4.setBounds(xzero+70,yzero+(ywidth/size)*5,260,50);
        b4.setContentAreaFilled(false);
        b4.setBorderPainted(false);
        add(b4);

        setLayout(null);//この文章がないと"Othello"が大量発生してしまう
        b1.addActionListener(new MainActionListener());
        b2.addActionListener(new PlayListener());
        b3.addActionListener(new SetListener());
        b4.addActionListener(new WorldListener());
    }
    class MainActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //b1.setLabel("こんにちは");
            System.out.println("Othelloを押しました。");
        }
    }
    class PlayListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Playを押しました。");
            pc(fb.PanelName[1]);
        }
    }
    class SetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Settingを押しました。");
            pc(fb.PanelName[2]);
        }
    }
    class WorldListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Worldを押しました。");
        }
    }   
}
