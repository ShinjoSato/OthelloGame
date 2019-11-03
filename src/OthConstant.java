import javax.swing.ImageIcon;

interface OthConstant {
        int size = 18, xwidth = 400, ywidth = 400, xzero = 200, yzero = 44;
        String relative_path = "../images/";
        String[] Stone = { relative_path + "image002.png", // Image of blackStone
                        relative_path + "image001.png", // Image of whiteStone
                        relative_path + "black02.png", relative_path + "white02.png", relative_path + "white03.png",
                        relative_path + "white04.png" }, Backgroung = { relative_path + "nightpd.jpg" }, // Image of
                                                                                                         // backgroung
                        Board = { relative_path + "board01.png" };
        ImageIcon[] istone = { new ImageIcon(Stone[0]), new ImageIcon(Stone[1]), new ImageIcon(Stone[2]),
                        new ImageIcon(Stone[3]), new ImageIcon(Stone[4]), new ImageIcon(Stone[5]) },
                        backgroung = { new ImageIcon(Backgroung[0]) }, board = { new ImageIcon(Board[0]) };
}