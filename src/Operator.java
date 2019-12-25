public class Operator{
    public static Othello putStone(Othello oth, int x, int y, int c) {// ○または●を置く
        if (search(oth,x, y, c) && oth.getSquare(x, y) == 0) {// もし(x,y)にc（黒or白)を置けるなら
            oth = trancferZ(oth); // 時間を進める
            oth.setSquare(x, y, c);// (i,j)に石を置く
            oth.PrintError(x, y); // ※念のための表示
            oth = turnStones(oth, x, y);// 周りの石尾裏返す
        }
        return oth;
    }

    public static int nextStone(Othello oth, int c) {// 次に置ける石を調べる
        for (int i = 0; i < oth.getSize(); i++) {
            for (int j = 0; j < oth.getSize(); j++) {
                if (search(oth, i, j, c) && oth.getSquare(i, j) == 0) return c;
            }
        }
        c *= -1;
        for (int i = 0; i < oth.getSize(); i++) {
            for (int j = 0; j < oth.getSize(); j++) {
                if (search(oth, i, j, c) && oth.getSquare(i, j) == 0) return c;
            }
        }
        return 0;
    }

    public static boolean search(Othello oth, int x, int y, int c) {// 置けるか置けないかの確認（一時的にここに置く）ルール１
        int fx = x - 1, fy = y - 1, lx = x + 1, ly = y + 1;
        if (x == 0) fx = 0;
        if (y == 0) fy = 0;
        if (x == oth.getSize() - 1) lx = oth.getSize() - 1;
        if (y == oth.getSize() - 1) ly = oth.getSize() - 1;
        for (int i = fx; i <= lx; i++) {
            for (int j = fy; j <= ly; j++) {
                if ( oth.getSquare(i, j) == c * (-1) ) {
                    if (x == i) {// 行が同じなら
                        if (searchLine(oth, x, y, j, c)) return true;
                    } else if (y == j) {// 列が同じなら
                        if (searchRow(oth, x, y, i, c)) return true;
                    } else {// 斜線が同じなら
                        if (searchSlant(oth, x, y, i, j, c)) return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean searchLine(Othello oth, int x, int y, int py, int c) {// 横に裏返せるかを調べる ルール１－１
        if (y < py) {
            while ((oth.getSquare(x, py) == c * (-1)) && py < oth.getSize() - 1) py++;
            if (oth.getSquare(x, py) == c) return true;
        } else {
            while ((oth.getSquare(x, py) == c * (-1)) && py > 0) py--;
            if (oth.getSquare(x, py) == c) return true;
        }
        return false;
    }

    public static boolean searchRow(Othello oth, int x, int y, int px, int c) {// 縦に裏返せるかを調べる ルール１－２
        if (x < px) {
            while ((oth.getSquare(px, y) == c * (-1)) && px < oth.getSize() - 1) px++;
            if (oth.getSquare(px, y) == c) return true;
        } else {
            while ((oth.getSquare(px, y) == c * (-1)) && px > 0) px--;
            if (oth.getSquare(px, y) == c) return true;
        }
        return false;
    }

    public static boolean searchSlant(Othello oth, int x, int y, int px, int py, int c) {// 斜めに裏返せるかを調べる ルール１－３
        if (x < px) {
            if (y < py) {// 右下方向
                while ((oth.getSquare(px, py) == c * (-1)) && (px < oth.getSize() - 1 && py < oth.getSize()-1)) {
                    px++;
                    py++;
                }
                if (oth.getSquare(px, py) == c) return true;
            } else {// 左下方向
                while ((oth.getSquare(px, py) == c * (-1)) && (px < oth.getSize() - 1 && py > 0)) {
                    px++;
                    py--;
                }
                if (oth.getSquare(px, py) == c) return true;
            }
        } else {
            if (y < py) {// 右上方向
                while ((oth.getSquare(px, py) == c * (-1)) && (py < oth.getSize() - 1 && px > 0)) {
                    py++;
                    px--;
                }
                if (oth.getSquare(px, py) == c) return true;
            } else {// 左上方向
                while ((oth.getSquare(px, py) == c * (-1)) && (py > 0 && px > 0)) {
                    py--;
                    px--;
                }
                if (oth.getSquare(px, py) == c) return true;
            }
        }
        return false;
    }

    public static Othello turnStones(Othello oth, int x, int y) {// 石を裏返す
        int fx = x - 1, fy = y - 1, lx = x + 1, ly = y + 1;
        if (x == 0) fx = 0;
        if (y == 0) fy = 0;
        if (x == oth.getSize() - 1) lx = oth.getSize() - 1;
        if (y == oth.getSize() - 1) ly = oth.getSize() - 1;
        for (int i = fx; i <= lx; i++) {
            for (int j = fy; j <= ly; j++) {
                if (oth.getSquare(i, j) == oth.getSquare(x, y) * (-1)) {
                    if (x == i) oth = turnLine(oth, x, y, j);// 行が同じなら、 x,y:置いた位置、j:変えるy方向、横
                    else if (y == j) oth = turnRow(oth, x, y, i);//列が同じなら
                    else oth = turnSlant(oth, x, y, i, j);
                }
            }
        }
        return oth;
    }

    public static Othello turnLine(Othello oth, int x, int y, int py) {// 横に裏返す
        if (y < py) {
            while ((oth.getSquare(x, py) == oth.getSquare(x, y) * (-1)) && py < oth.getSize() - 1) py++;
            if (oth.getSquare(x, py) == oth.getSquare(x, y)) {
                for (int j = y + 1; j < py; j++) {
                    oth.setSquare(x, j, (-1) * oth.getSquare(x, j));
                }
            }
        } else {
            while ((oth.getSquare(x, py) == oth.getSquare(x, y) * (-1)) && py > 0) py--;
            if (oth.getSquare(x, py) == oth.getSquare(x, y)) {
                for (int j = py + 1; j < y; j++) {
                    oth.setSquare(x, j, (-1) * oth.getSquare(x, j));
                }
            }
        }
        return oth;
    }

    public static Othello turnRow(Othello oth, int x, int y, int px) {// 縦に裏返す
        if (x < px) {
            while ((oth.getSquare(px, y) == oth.getSquare(x, y) * (-1)) && px < oth.getSize() - 1) px++;
            if (oth.getSquare(px, y) == oth.getSquare(x, y)) {
                for (int i = x + 1; i < px; i++) {
                    oth.setSquare(i, y, oth.getSquare(i, y) * (-1));
                }
            }
        } else {
            while ((oth.getSquare(px, y) == oth.getSquare(x, y) * (-1)) && px > 0) px--;
            if (oth.getSquare(px, y) == oth.getSquare(x, y)) {
                for (int i = px + 1; i < x; i++) {
                    oth.setSquare(i, y, oth.getSquare(i, y) * (-1));
                }
            }
        }
        return oth;
    }

    public static Othello turnSlant(Othello oth, int x, int y, int px, int py) {// 斜めに裏返す
        if (x < px) {
            if (y < py) {// 右下を裏返す
                while ((oth.getSquare(px, py) == oth.getSquare(x, y) * (-1)) && (px < oth.getSize() - 1 && py < oth.getSize() - 1)) {
                    px++;
                    py++;
                }
                if (oth.getSquare(px, py) == oth.getSquare(x, y)) {
                    int j = y + 1;
                    for (int i = x + 1; i < px; i++) {
                        oth.setSquare(i, j, oth.getSquare(i, j) * (-1));
                        j++;
                    }
                }
            } else {// 左下を裏返す
                while ((oth.getSquare(px, py) == oth.getSquare(x, y) * (-1)) && (px < oth.getSize() - 1 && py > 0)) {
                    px++;
                    py--;
                }
                if (oth.getSquare(px, py) == oth.getSquare(x, y)) {
                    int j = y - 1;
                    for (int i = x + 1; i < px; i++) {
                        oth.setSquare(i, j, oth.getSquare(i, j) * (-1));
                        j--;
                    }
                }
            }
        } else {
            if (y < py) {// 右上を裏返す
                while ((oth.getSquare(px, py) == oth.getSquare(x, y) * (-1)) && (py < oth.getSize() - 1 && px > 0)) {
                    py++;
                    px--;
                }
                if (oth.getSquare(px, py) == oth.getSquare(x, y)) {
                    int i = x - 1;
                    for (int j = y + 1; j < py; j++) {
                        oth.setSquare(i, j, oth.getSquare(i, j) * (-1));
                        i--;
                    }
                }
            } else {// 左上を裏返す
                while ((oth.getSquare(px, py) == oth.getSquare(x, y) * (-1)) && (py > 0 && px > 0)) {
                    py--;
                    px--;
                }
                if (oth.getSquare(px, py) == oth.getSquare(x, y)) {
                    int i = px + 1;
                    for (int j = py + 1; j < y; j++) {
                        oth.setSquare(i, j, oth.getSquare(i, j) * (-1));
                        i++;
                    }
                }
            }
        }
        return oth;
    }

    public static Othello trancferZ(Othello oth) {// z軸を1進める
        oth.setZ(oth.getZ()+1);
        for (int i = 0; i < oth.getSize(); i++) {
            for (int j = 0; j < oth.getSize(); j++) {
                oth.setSquare(i, j, oth.getSquare(i, j, oth.getZ()-1));
            }
        }
        return oth;
    }

    public static Othello backZ(Othello oth) {// z軸を1戻す
        for (int i = 0; i < oth.getSize(); i++) {
            for (int j = 0; j < oth.getSize(); j++) {
                oth.setSquare(i, j, oth.getSquare(i, j, oth.getZ()-1));
            }
        }
        oth.setZ(oth.getZ()-1);
        return oth;
    }

    /*public void putRandom(){//オセロ盤の空の枠に置く（※一時的にここに置く） 
        int count=0,extreme=10000;
        int i=(int)(Math.random()*size),j=(int)(Math.random()*size);
        int c=1; for(int k=0;k<size*size-4;k++){//全マス-4箇所のマス目分実行する //
        for(int k=0;k<10;k++){
            if(count>=extreme) c=c*(-1);//もし前の段階で同じ色が二回連続で置かれてたら、次の色を置く
            count=0;
            while(search(i,j,c)!=true||table[i][j][z]!=0){//置けるところを探す
                i=(int)(Math.random()*size); j=(int)(Math.random()*size); count++;
                if(count==10000){//一万回調べても置けられなかったら、別の色を置く 
                    c=c*(-1); 
                }
                if(count==20000){//どちらも置けなかったら即終了！ 
                    c=0;
                    break; 
                }
            }
            System.out.println("☆:"+count);
            if(c!=0){//もし石を置けるのなら
                putStone(i,j,c);
            }
            c=c*(-1);
        }
    }*/
}