import javax.swing.ImageIcon;

interface OthConstant {
        int size = 8, xwidth = 400, ywidth = 400, xzero = 200, yzero = 44;
        String[] Stone = { "images/image002.png", // Image of blackStone
                        "images/image001.png", // Image of whiteStone
                        "images/black02.png", "images/white02.png", "images/white03.png", 
                        "images/white04.png" }, Backgroung = { "images/nightpd.jpg" }, // Image of backgroung
                        Board = { "images/board01.png" };
        ImageIcon[] istone = { new ImageIcon(Stone[0]), new ImageIcon(Stone[1]), new ImageIcon(Stone[2]),
                        new ImageIcon(Stone[3]), new ImageIcon(Stone[4]), new ImageIcon(Stone[5]) },
                        backgroung = { new ImageIcon(Backgroung[0]) }, board = { new ImageIcon(Board[0]) };
}