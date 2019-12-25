import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameBase extends JFrame {
    public String[] PanelName = { "home", "play", "set" };
    Panels[] op = new Panels[3];

    public FrameBase() {
        super("OTHELLO");
        this.setSize(1200, 675);    // 16:9

        op[0] = new HomePanel(this, PanelName[0]);
        this.add((HomePanel) op[0]);
        ((HomePanel) op[0]).setVisible(false);

        op[1] = new PlayPanel(this, PanelName[1]);
        this.add((PlayPanel) op[1]);
        ((PlayPanel) op[1]).setVisible(false);

        op[2] = new SetPanel(this, PanelName[2]);
        this.add((SetPanel) op[2]);
        ((SetPanel) op[2]).setVisible(true);
    }

    public static void main(String[] args) {
        FrameBase fb = new FrameBase();
        fb.setDefaultCloseOperation(EXIT_ON_CLOSE);
        fb.setVisible(true);
    }

    public void changePanel(JPanel jp, String str) {
        System.out.println("name:" + jp.getName() + "\n" + "str:" + str);
        String name = jp.getName();
        if (name == PanelName[0]) {
            op[0] = (HomePanel) jp;
            ((HomePanel) op[0]).setVisible(false);
        } else if (name == PanelName[1]) {
            op[1] = (PlayPanel) jp;
            ((PlayPanel) op[1]).setVisible(false);
        } else if (name == PanelName[2]) {
            op[2] = (SetPanel) jp;
            ((SetPanel) op[2]).setVisible(false);
        }
        /*
        switch(name){
            case PanelName[0]:{
                op[0] = (HomePanel) jp;
                ((HomePanel) op[0]).setVisible(false);
                break;
            }
            case PanelName[1]:{
                op[1] = (PlayPanel) jp;
                ((PlayPanel) op[1]).setVisible(false);
                break;
            }
            case PanelName[2]:{
                op[2] = (SetPanel) jp;
                ((SetPanel) op[2]).setVisible(false);
                break;
            }
        }*/


        if (str == PanelName[0]) {
            ((HomePanel) op[0]).setVisible(true);
        } else if (str == PanelName[1]) {
            ((PlayPanel) op[1]).setVisible(true);
        } else if (str == PanelName[2]) {
            ((SetPanel) op[2]).setVisible(true);
        }

        /*switch(str){
            case PanelName[0]: ((HomePanel) op[0]).setVisible(true); break;
            case PanelName[1]: ((PlayPanel) op[1]).setVisible(true); break;
            case PanelName[2]: ((SetPanel) op[2]).setVisible(true); break;
        }*/
    }
}
