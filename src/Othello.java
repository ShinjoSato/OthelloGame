//Othello.java 要するにオセロ,●=1,○=-1,×=0と考える
public class Othello {
    protected int[][][] table;
    protected int size;
    protected int z;

    public Othello(int size, int[][][] table, int z) {
        this.table = table;
        this.size = size;// オセロ盤のサイズを変更する
        this.z = z;
        table[size / 2 - 1][size / 2 - 1][0] = 1;
        table[size / 2][size / 2][0] = 1;
        table[size / 2 - 1][size / 2][0] = -1;
        table[size / 2][size / 2 - 1][0] = -1;
    }

    public void putStone(int x, int y, int c) {// ○または●を置く
        if (search(x, y, c) && table[x][y][z] == 0) {// もし(x,y)にc（黒or白)を置けるなら
            trancferZ(); // 時間を進める
            table[x][y][z] = c; // (i,j)に石を置く
            PrintError(x, y); // ※念のための表示
            turnStones(x, y);// 周りの石尾裏返す
        }
    }

    public void trancferZ() {// z軸を1進める
        z++;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j][z] = table[i][j][z - 1];
            }
        }
    }

    public void backZ() {// z軸を1戻す
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j][z] = table[i][j][z - 1];
            }
        }
        z--;
    }

    /*
     * public void putRandom(){//オセロ盤の空の枠に置く（※一時的にここに置く） int count=0,extreme=10000;
     * int i=(int)(Math.random()*size),j=(int)(Math.random()*size); int c=1; for(int
     * k=0;k<size*size-4;k++){//全マス-4箇所のマス目分実行する //for(int k=0;k<10;k++){
     * if(count>=extreme) c=c*(-1);//もし前の段階で同じ色が二回連続で置かれてたら、次の色を置く count=0;
     * while(search(i,j,c)!=true||table[i][j][z]!=0){//置けるところを探す
     * i=(int)(Math.random()*size); j=(int)(Math.random()*size); count++;
     * if(count==10000){//一万回調べても置けられなかったら、別の色を置く c=c*(-1); }
     * if(count==20000){//どちらも置けなかったら即終了！ c=0; break; }
     * }System.out.println("☆:"+count); if(c!=0){//もし石を置けるのなら putStone(i,j,c); }
     * c=c*(-1); } }
     */
    public int nextStone(int c) {// 次に置ける石を調べる
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (search(i, j, c) && table[i][j][z] == 0)
                    return c;
            }
        }
        c *= -1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (search(i, j, c) && table[i][j][z] == 0)
                    return c;
            }
        }
        return 0;
    }

    public boolean search(int x, int y, int c) {// 置けるか置けないかの確認（一時的にここに置く）ルール１
        int fx = x - 1, fy = y - 1, lx = x + 1, ly = y + 1;
        if (x == 0)
            fx = 0;
        if (y == 0)
            fy = 0;
        if (x == size - 1)
            lx = size - 1;
        if (y == size - 1)
            ly = size - 1;
        for (int i = fx; i <= lx; i++) {
            for (int j = fy; j <= ly; j++) {
                if (table[i][j][z] == c * (-1)) {
                    if (x == i) {// 行が同じなら
                        if (searchLine(x, y, j, c)) {
                            return true;
                        }
                    } else if (y == j) {// 列が同じなら
                        if (searchRow(x, y, i, c)) {
                            return true;
                        }
                    } else {// 斜線が同じなら
                        if (searchSlant(x, y, i, j, c)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean searchLine(int x, int y, int py, int c) {// 横に裏返せるかを調べる ルール１－１
        if (y < py) {
            while ((table[x][py][z] == c * (-1)) && py < size - 1) {
                py++;
            }
            if (table[x][py][z] == c) {
                return true;
            }
        } else {
            while ((table[x][py][z] == c * (-1)) && py > 0) {
                py--;
            }
            if (table[x][py][z] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean searchRow(int x, int y, int px, int c) {// 縦に裏返せるかを調べる ルール１－２
        if (x < px) {
            while ((table[px][y][z] == c * (-1)) && px < size - 1) {
                px++;
            }
            if (table[px][y][z] == c) {
                return true;
            }
        } else {
            while ((table[px][y][z] == c * (-1)) && px > 0) {
                px--;
            }
            if (table[px][y][z] == c) {
                return true;
            }
        }
        return false;
    }

    public boolean searchSlant(int x, int y, int px, int py, int c) {// 斜めに裏返せるかを調べる ルール１－３
        if (x < px) {
            if (y < py) {// 右下方向
                while ((table[px][py][z] == c * (-1)) && (px < size - 1 && py < size - 1)) {
                    px++;
                    py++;
                }
                if (table[px][py][z] == c) {
                    return true;
                }
            } else {// 左下方向
                while ((table[px][py][z] == c * (-1)) && (px < size - 1 && py > 0)) {
                    px++;
                    py--;
                }
                if (table[px][py][z] == c) {
                    return true;
                }
            }
        } else {
            if (y < py) {// 右上方向
                while ((table[px][py][z] == c * (-1)) && (py < size - 1 && px > 0)) {
                    py++;
                    px--;
                }
                if (table[px][py][z] == c) {
                    return true;
                }
            } else {// 左上方向
                while ((table[px][py][z] == c * (-1)) && (py > 0 && px > 0)) {
                    py--;
                    px--;
                }
                if (table[px][py][z] == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public void turnStones(int x, int y) {// 石を裏返す
        int fx = x - 1, fy = y - 1, lx = x + 1, ly = y + 1;
        if (x == 0)
            fx = 0;
        if (y == 0)
            fy = 0;
        if (x == size - 1)
            lx = size - 1;
        if (y == size - 1)
            ly = size - 1;
        for (int i = fx; i <= lx; i++) {
            for (int j = fy; j <= ly; j++) {
                if (table[i][j][z] == table[x][y][z] * (-1)) {
                    if (x == i) {// 行が同じなら
                        turnLine(x, y, j);// x,y:置いた位置、j:変えるy方向、横
                    } else if (y == j) {// 列が同じなら
                        turnRow(x, y, i);
                    } else {// System.out.println("turnSlant");
                        turnSlant(x, y, i, j);
                    }
                }
            }
        }
    }

    public void turnLine(int x, int y, int py) {// 横に裏返す
        if (y < py) {
            while ((table[x][py][z] == table[x][y][z] * (-1)) && py < size - 1) {
                py++;
            }
            if (table[x][py][z] == table[x][y][z]) {
                for (int j = y + 1; j < py; j++) {
                    table[x][j][z] = table[x][j][z] * (-1);
                }
            }
        } else {
            while ((table[x][py][z] == table[x][y][z] * (-1)) && py > 0) {
                py--;
            }
            if (table[x][py][z] == table[x][y][z]) {
                for (int j = py + 1; j < y; j++) {
                    table[x][j][z] = table[x][j][z] * (-1);
                }
            }
        }
    }

    public void turnRow(int x, int y, int px) {// 縦に裏返す
        if (x < px) {
            while ((table[px][y][z] == table[x][y][z] * (-1)) && px < size - 1) {
                px++;
            }
            if (table[px][y][z] == table[x][y][z]) {
                for (int i = x + 1; i < px; i++) {
                    table[i][y][z] = table[i][y][z] * (-1);
                }
            }
        } else {
            while ((table[px][y][z] == table[x][y][z] * (-1)) && px > 0) {
                px--;
            }
            if (table[px][y][z] == table[x][y][z]) {
                for (int i = px + 1; i < x; i++) {
                    table[i][y][z] = table[i][y][z] * (-1);
                }
            }
        }
    }

    public void turnSlant(int x, int y, int px, int py) {// 斜めに裏返す
        if (x < px) {
            if (y < py) {// 右下を裏返す
                while ((table[px][py][z] == table[x][y][z] * (-1)) && (px < size - 1 && py < size - 1)) {
                    px++;
                    py++;
                }
                if (table[px][py][z] == table[x][y][z]) {
                    int j = y + 1;
                    for (int i = x + 1; i < px; i++) {
                        table[i][j][z] = table[i][j][z] * (-1);
                        j++;
                    }
                }
            } else {// 左下を裏返す
                while ((table[px][py][z] == table[x][y][z] * (-1)) && (px < size - 1 && py > 0)) {
                    px++;
                    py--;
                }
                if (table[px][py][z] == table[x][y][z]) {
                    int j = y - 1;
                    for (int i = x + 1; i < px; i++) {
                        table[i][j][z] = table[i][j][z] * (-1);
                        j--;
                    }
                }
            }
        } else {
            if (y < py) {// 右上を裏返す
                while ((table[px][py][z] == table[x][y][z] * (-1)) && (py < size - 1 && px > 0)) {
                    py++;
                    px--;
                }
                if (table[px][py][z] == table[x][y][z]) {
                    int i = x - 1;
                    for (int j = y + 1; j < py; j++) {
                        table[i][j][z] = table[i][j][z] * (-1);
                        i--;
                    }
                }
            } else {// 左上を裏返す
                while ((table[px][py][z] == table[x][y][z] * (-1)) && (py > 0 && px > 0)) {
                    py--;
                    px--;
                }
                if (table[px][py][z] == table[x][y][z]) {
                    int i = px + 1;
                    for (int j = py + 1; j < y; j++) {
                        table[i][j][z] = table[i][j][z] * (-1);
                        i++;
                    }
                }
            }
        }
    }

    public void PrintError(int x, int y) {// 一時的な確認
        System.out.println("table[" + x + "][" + y + "][" + z + "] : " + table[x][y][z]);
    }
}
/*
 * ほぼ完成。あとは色々なオプションを付ければより楽しめる！！ searchLineとputLineをくっつけられるかもしれない...
 */
