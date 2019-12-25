import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class PlayPanel extends JPanel implements MouseListener, MouseMotionListener, OthPanel, OthConstant{
    FrameBase fb;
    String str;

    int size,x1,y1,x2,y2;
    private int stone=1;
    protected Othello oth;
    protected JButton b1,b2,btn,btn1;
    public PlayPanel(FrameBase fb,String str){
        this.fb=fb;
        this.str=str;
        this.setName(str);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        
        this.setSize(1000, 1000);//ここ変えたい
        addMouseListener(this);
        addMouseMotionListener(this);

        this.size=8;
        int[][][] t=new int[size][size][size*size-4+1];
        oth=new Othello(size,t,0);
    }
    public void pc(String str){
        fb.changePanel((JPanel)this, str);
    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){}
    public void mouseDragged(MouseEvent e){}
    public void mouseMoved(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
        int x=e.getX(),y=e.getY(),px=0,py=0,xsub=xzero,ysub=yzero;;
        if(x>=xzero&&x<=xzero+xwidth&&y>=yzero&&y<=yzero+ywidth){
        //マスに石を置く
            while(x<xsub||x>=xsub+xwidth/oth.size){
                xsub+=xwidth/oth.size;
                px++;
            }
            if(px==oth.size) px--;
            while(y<ysub||y>=ysub+ywidth/oth.size){
                ysub+=ywidth/oth.size;
                py++;
            }
            if(py==oth.size) py--;
            run(py,px);
        }
        repaint();
    }
    public void run(int x,int y){
        repaint();
        if(Operator.search(oth,x,y,stone)&&oth.table[x][y][oth.getZ()]==0){//もし(x,y)にc（黒or白)を置けるなら
            //oth.putStone(x,y,stone);//周りの石尾裏返す
            oth = Operator.putStone(oth, x, y, stone);
            stone=Operator.nextStone(oth, stone*(-1));
        }
    }
    public void paintComponent(Graphics g){//ここの中で空白のマスを調整する
        super.paintComponent(g);

        ImageIcon icon1=istone[2];
        ImageIcon icon2=istone[5];
        
        btn = new JButton("~ Home ~");
        btn.setBounds(xzero,0,100,yzero-5);
        btn.addActionListener(new HomeListener());
        this.add(btn);

        btn1=new JButton("~ Setting ~");
        btn1.setBounds(xzero+100,0,100,yzero-5);
        btn1.addActionListener(new SetListener());
        this.add(btn1);

        ImageIcon image=backgroung[0];
        g.drawImage(image.getImage(),0,0,700,600,this);
        
        int b=0,w=0;
        //g.setColor(Color.green);
        //g.fillRect(xzero,yzero,xwidth,ywidth);
        g.drawImage(board[0].getImage(),xzero,yzero,xwidth,ywidth,this);
        g.setColor(Color.white);
        g.drawRect(xzero,yzero,xwidth,ywidth);
        for(int i=0;i<oth.size;i++){
            g.drawLine(xzero+(xwidth/oth.size)*i,yzero,xzero+(xwidth/oth.size)*i,yzero+ywidth);
            g.drawLine(xzero,yzero+(ywidth/oth.size)*i,xzero+xwidth,yzero+(ywidth/oth.size)*i);
        }
        for(int i=0;i<oth.size;i++){
            for(int j=0;j<oth.size;j++){
                x1=xzero+(xwidth/oth.size)*(i); y1=yzero+(ywidth/oth.size)*(j);
                x2=xzero+(xwidth/oth.size)*(i+1); y2=yzero+(ywidth/oth.size)*(j+1);
                if(oth.table[j][i][oth.getZ()]==1){//黒石を表示
                    b++;
                    g.drawImage(icon1.getImage(),x1+1,y1+1,x2-x1-1,y2-y1-1,this);//+1と-1は微調整
                }
                if(oth.table[j][i][oth.getZ()]==-1){//白石を表示
                    w++;
                    g.drawImage(icon2.getImage(),x1+1,y1+1,x2-x1-1,y2-y1-1,this);
                }

                if(Operator.search(oth,j,i,stone)&&oth.table[j][i][oth.getZ()]==0){
                    g.setColor(Color.yellow);
                    g.fillRect(x1+2,y1+2,xwidth/oth.size-3,ywidth/oth.size-3);
                }
            }
        }
        Font font=new Font("Arial",Font.ITALIC,24);
        g.setFont(font);
        if(stone==0){
            if(b>w){
                g.setColor(Color.black);
                g.drawString("Winner:Black!!",xzero+(xwidth/oth.size)*(oth.size/2)-80,yzero+ywidth+30);
            }else if(b<w){
                g.setColor(Color.white);
                g.drawString("Winner:White!!",xzero+(xwidth/oth.size)*(oth.size/2)-80,yzero+ywidth+30);
            }else{
                g.setColor(Color.lightGray);
                g.drawString("Draw Match!!",xzero+(xwidth/oth.size)*(oth.size/2)-80,yzero+ywidth+30);
            }
        }else{
            if(stone==1){
                g.setColor(Color.black);
                g.drawString("Next:Black",xzero+(xwidth/oth.size)*(oth.size/2)-70,yzero+ywidth+30);
            }else if(stone==-1){
                g.setColor(Color.white);
                g.drawString("Next:White",xzero+(xwidth/oth.size)*(oth.size/2)-70,yzero+ywidth+30);
            }
        }
        g.setColor(Color.lightGray);
        if(b>w) g.drawString(">",xzero+(xwidth/oth.size)*(oth.size/2)-15,yzero+ywidth+60);
        else if(b<w) g.drawString("<",xzero+(xwidth/oth.size)*(oth.size/2)-15,yzero+ywidth+60);
        else g.drawString("=",xzero+(xwidth/oth.size)*(oth.size/2)-15,yzero+ywidth+60);
        g.setColor(Color.black); g.drawString("BLACK:"+b,xzero+(xwidth/oth.size)*(oth.size/2)-160,yzero+ywidth+60);
        g.setColor(Color.white); g.drawString("WHITE:"+w,xzero+(xwidth/oth.size)*(oth.size/2)+40,yzero+ywidth+60);

        b1=new JButton("BACK");
        b1.setForeground(Color.blue);
        b1.setFont(new Font("Arial",Font.ITALIC,25));
        b1.setBounds(330,500,120,50);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        add(b1);
        //if(stone==1)b1.setEnabled(false); ここ使えるようにしたい！
        
        b2=new JButton("RECET");
        b2.setForeground(Color.red);
        b2.setFont(new Font("Arial",Font.ITALIC,25));
        b2.setBounds(330,535,125,50);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        add(b2);
        
        setLayout(null);
        //addWindowListener(new MainWindowListener());
        b1.addActionListener(new BackListener());
        b2.addActionListener(new RecetListener());
    }

    class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("BACKを押しました。");
            if(oth.getZ()-1>=0&&stone!=0){
                Operator.backZ(oth);
                stone = Operator.nextStone(oth, stone*(-1));
                repaint();
            }
        }
    }
    class RecetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stone=1;
            int size=oth.size;
            int[][][] table=new int[size][size][size*size-4+1]; 
            System.out.println("RECETを押しました");
            oth=new Othello(size,table,0);
            repaint();
        }
    }
    class HomeListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("HOMEを押しました");
            pc(fb.PanelName[0]);
        }
    }
    class SetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("SETTINGを押しました");
            pc(fb.PanelName[2]);
        }
    }
}
