package othello;
//Othello.java 要するにオセロ,●=1,○=-1,×=0と考える
public class Othello {
    protected int[][][] table;
    protected int size;
    private int z;

    public Othello(int size, int[][][] table) {
        this.table = table;
        this.size = size;// オセロ盤のサイズを変更する
        this.z = 0;
        table[size / 2 - 1][size / 2 - 1][0] = 1;
        table[size / 2][size / 2][0] = 1;
        table[size / 2 - 1][size / 2][0] = -1;
        table[size / 2][size / 2 - 1][0] = -1;
    }

    public int getZ(){
        return z;
    }

    public int getSize(){
        return size;
    }

    public int getSquare(int x, int y){
        return getSquare(x, y, this.z);
    }

    public int getSquare(int x, int y, int z){
        return table[x][y][z];
    }

    public int[][][] getTable(){
        return table;
    }

    public void setZ(int z){
        this.z = z;
    }

    public void setSize(int size){
        this.size = size;
    }

    public void setSquare(int x, int y, int c){
        this.table[x][y][z] = c;
    }

    public void setTable(int[][][] table){
        this.table = table;
    }

    public void PrintError(int x, int y) {// 一時的な確認
        System.out.println("table[" + x + "][" + y + "][" + z + "] : " + table[x][y][z]);
    }
}
/*
 * ほぼ完成。あとは色々なオプションを付ければより楽しめる！！ searchLineとputLineをくっつけられるかもしれない...
 */
