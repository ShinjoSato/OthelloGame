public class Main15 {
    public static void main(String[] args) {
        int size = 8;
        int[][][] table = new int[size][size][size * size - 4 + 1];
        OthelloArt oa = new OthelloArt(size, table, 0);
        oa.play();
    }
}
