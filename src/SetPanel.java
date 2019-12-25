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
import javax.swing.JComboBox;

public class SetPanel extends Panels{
    FrameBase fb;
    String str;
    private JButton b1,b2;
    int size,x1,y1,x2,y2;

    private int xwidth=Parameter.xwidth.get(), ywidth=Parameter.ywidth.get();
    private int xzero=Parameter.xzero.get(), yzero=Parameter.yzero.get();

    public SetPanel(FrameBase fb,String str){
        this.fb=fb;
        this.str=str;
        this.setName(str);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.size=8;
        this.setSize(1200, 675);//ここ変えたい
    }    
    public void pc(String str){
        fb.changePanel((JPanel)this,str);
    }

    public void paintComponent(Graphics g){//ここの中で空白のマスを調整する
        super.paintComponent(g);

        ImageIcon image=backgroung[2];
        g.drawImage(image.getImage(), 0, 0, 1200, 675, this);

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
        
        b1 = createButton("~ Play ~", Font.PLAIN, 14, xzero+100, 0, 100, yzero-5, true, true);
        add(b1);

        b2 = createButton("~ Home ~", Font.PLAIN, 14, xzero, 0, 100, yzero-5, true, true);
        add(b2);

        ImageIcon icon1=new ImageIcon("image002.png");
        ImageIcon icon2=new ImageIcon("image004.png");
        JLabel label1=new JLabel(icon1);
        JLabel label2=new JLabel(icon2);
        add(label1);
        add(label2);
        g.drawImage(icon1.getImage(),40,90,this);

        setLayout(null);//この文章がないと"Othello"が大量発生してしまう
        b1.addActionListener(new PlayListener());
        b2.addActionListener(new HomeListener());
    }

    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        System.out.println(cb.getSelectedItem());
     }
}