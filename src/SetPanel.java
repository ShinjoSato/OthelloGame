import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JComponent;
import java.lang.Thread;
import javax.swing.JComboBox;


public class SetPanel extends JPanel implements ActionListener ,MouseListener, MouseMotionListener, OthPanel, OthConstant{
    FrameBase fb;
    String str;
    private JButton b1,b2;
    int size,x1,y1,x2,y2;
    public SetPanel(FrameBase fb,String str){
        this.fb=fb;
        this.str=str;
        this.setName(str);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        this.size=8;
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

        b1=new JButton("~ Play ~");
        //b1.setFont(new Font("Arial",Font.ITALIC,40));
        b1.setBounds(xzero+100, 0, 100, yzero-5);
        b1.setContentAreaFilled(true);//ボタンの中の色
        b1.setBorderPainted(true);//ボタンの淵
        add(b1);

        b2=new JButton("~ Home ~");
        //b2.setFont(new Font("Arial",Font.ITALIC,40));
        b2.setBounds(xzero, 0, 100, yzero-5);
        b2.setContentAreaFilled(true);//ボタンの中の色
        b2.setBorderPainted(true);//ボタンの淵
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
    class PlayListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Playを押しました。");
            pc(fb.PanelName[1]);
        }
    }
    class HomeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Homeを押しました。");
            pc(fb.PanelName[0]);
        }
    }
}