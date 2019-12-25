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

public class PlayPanel extends Panels{
    FrameBase fb;
    String str;

    int size,x1,y1,x2,y2;
    private int stone=1;
    
    private int xwidth=Parameter.xwidth.get(), ywidth=Parameter.ywidth.get();
    private int xzero=Parameter.xzero.get(), yzero=Parameter.yzero.get();
    
    protected Othello oth;
    protected JButton b1,b2,btn,btn1;
    public PlayPanel(FrameBase fb,String str){
        this.fb=fb;
        this.str=str;
        this.setName(str);
        this.setBackground(Color.darkGray);
        this.setLayout(null);
        
        this.setSize(1200, 675);//ここ変えたい
        addMouseListener(this);
        addMouseMotionListener(this);

        this.size=18;
        int[][][] t=new int[size][size][size*size-4+1];
        oth=new Othello(size,t);
    }
    @Override
    public void pc(String str){
        fb.changePanel((JPanel)this, str);
    }

    public void mouseClicked(MouseEvent e){
        int x=e.getX(),y=e.getY(),px=0,py=0,xsub=xzero,ysub=yzero;;

        if(x>=xzero&&x<=xzero+xwidth&&y>=yzero&&y<=yzero+ywidth){
        //マスに石を置く
            while(x<xsub||x>=xsub+xwidth/oth.getSize()){
                xsub+=xwidth/oth.getSize();
                px++;
            }
            if(px==oth.getSize()) px--;
            while(y<ysub||y>=ysub+ywidth/oth.getSize()){
                ysub+=ywidth/oth.getSize();
                py++;
            }
            if(py==oth.getSize()) py--;
            run(py,px);
        }
        repaint();
    }

    /**
     * ここに int[] stone = new int[z];を組み込みたい
     */
    public void run(int x,int y){
        repaint();
        if(Operator.search(oth,x,y,stone)&&oth.getSquare(x, y)==0){//もし(x,y)にc（黒or白)を置けるなら
            //oth.putStone(x,y,stone);//周りの石尾裏返す
            oth = Operator.putStone(oth, x, y, stone);
            stone=Operator.nextStone(oth, stone*(-1));
        }
    }
    @Override
    public void paintComponent(Graphics g){//ここの中で空白のマスを調整する
        super.paintComponent(g);

        System.out.println("読み込みました");

        ImageIcon icon1=istone[2];
        ImageIcon icon2=istone[5];
        
        btn = createButton("~ Home ~", Font.PLAIN, 14, xzero, 0, 100, yzero-5, true, true);
        btn.addActionListener(new HomeListener());
        this.add(btn);

        btn1 = createButton("~ Setting ~", Font.PLAIN, 14, xzero+100, 0, 100, yzero-5, true, true);
        btn1.addActionListener(new SetListener());
        this.add(btn1);

        ImageIcon image=backgroung[1];
        g.drawImage(image.getImage(), 0, 0, 1200, 675, this);
        
        g = createBoard(g, size, xzero, yzero, xwidth, ywidth);

        int b=0,w=0;
        for(int i=0; i<oth.getSize(); i++){
            for(int j=0;j<oth.getSize(); j++){
                x1=xzero+(xwidth/oth.getSize())*(i); y1=yzero+(ywidth/oth.getSize())*(j);
                x2=xzero+(xwidth/oth.getSize())*(i+1); y2=yzero+(ywidth/oth.getSize())*(j+1);
                if(oth.getSquare(j, i)/*   oth.table[j][i][oth.getZ()]*/==1){//黒石を表示
                    b++;
                    g.drawImage(icon1.getImage(),x1+1,y1+1,x2-x1-1,y2-y1-1,this);//+1と-1は微調整
                }
                if(oth.getSquare(j, i)/*oth.table[j][i][oth.getZ()]*/==-1){//白石を表示
                    w++;
                    g.drawImage(icon2.getImage(),x1+1,y1+1,x2-x1-1,y2-y1-1,this);
                }

                if(Operator.search(oth,j,i,stone)&& oth.getSquare(j, i)/*  oth.table[j][i][oth.getZ()]*/==0){
                    g.setColor(Color.yellow);
                    g.fillRect(x1+2,y1+2,xwidth/oth.getSize()-3,ywidth/oth.getSize()-3);
                }
            }
        }
        Font font=new Font("Arial",Font.ITALIC,24);
        g.setFont(font);
        if(stone==0){
            if(b>w){
                g.setColor(Color.black);
                g.drawString("Winner:Black!!", xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-80,yzero+ywidth+30);
            }else if(b<w){
                g.setColor(Color.white);
                g.drawString("Winner:White!!", xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-80,yzero+ywidth+30);
            }else{
                g.setColor(Color.lightGray);
                g.drawString("Draw Match!!", xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-80,yzero+ywidth+30);
            }
        }else{
            if(stone==1){
                g.setColor(Color.black);
                g.drawString("Next:Black",xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-70,yzero+ywidth+30);
            }else if(stone==-1){
                g.setColor(Color.white);
                g.drawString("Next:White",xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-70,yzero+ywidth+30);
            }
        }
        g.setColor(Color.lightGray);
        if(b>w) g.drawString(">",xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-15,yzero+ywidth+60);
        else if(b<w) g.drawString("<",xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-15,yzero+ywidth+60);
        else g.drawString("=",xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-15,yzero+ywidth+60);
        g.setColor(Color.black); g.drawString("BLACK:"+b,xzero+(xwidth/oth.getSize())*(oth.getSize()/2)-160,yzero+ywidth+60);
        g.setColor(Color.white); g.drawString("WHITE:"+w,xzero+(xwidth/oth.getSize())*(oth.getSize()/2)+40,yzero+ywidth+60);

        b1 = createButton("BACK", Color.blue, 25, 330, 500, 120, 50);
        add(b1);
        //if(stone==1)b1.setEnabled(false); ここ使えるようにしたい！
        
        b2 = createButton("RECET", Color.red, 25, 330, 535, 125, 50);
        add(b2);
        
        setLayout(null);
        //addWindowListener(new MainWindowListener());
        b1.addActionListener(new BackListener());
        b2.addActionListener(new RecetListener());
    }

    /**
     * stone = Operator.nextStone(oth, stone*(-1)); を
     * stone = stone[z-1]; に書き換えたい
     */
    class BackListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("BACKを押しました。");
            if(oth.getZ()-1>=0&&stone!=0){
                oth = Operator.backZ(oth);
                stone = Operator.nextStone(oth, stone*(-1));
                repaint();
            }
        }
    }

    class RecetListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            stone=1;
            int size=oth.getSize();
            int[][][] table=new int[size][size][size*size-4+1]; 
            System.out.println("RECETを押しました");
            oth=new Othello(size,table);
            repaint();
        }
    }
}
