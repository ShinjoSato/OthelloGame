//OthelloArt制作をする
public class OthelloArt extends Othello{
    private int k=0;
    private int[][] a;
    private int[] c;
    public OthelloArt(int size,int[][][] table,int z){
        super(size,table,z);
        z=0;
    }

    public void play(){
        c=new int[size*size-4+1];
        c[0]=1;
        //splay();
        //

        a=new int[size][size];
        art(a);

        searchShape();

        //component();

        rEview();
    }
    public void component(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(table[i][j][z]!=a[i][j]){
                    //System.out.println("一致しませんでした");
                    System.out.println("["+i+":"+j+"]");
                }
            }
        }
    }
    public boolean judgeCom(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(table[i][j][z]!=a[i][j]||table[j][i][z]!=a[i][j]) return false;
            }
        }
        return true;
    }
    public void rEview(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(table[i][j][z]==1) System.out.print("●");   
                else if(table[i][j][z]==-1) System.out.print("○");
                else System.out.print("□");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void art(int[][] a){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(i==0||j==3){
                    a[i][j]=1;
                }else{
                    a[i][j]=-1;
                }
            }
        }
        a[0][3]=-1;

        System.out.println("art");
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(a[i][j]==1) System.out.print("●");
                else if(a[i][j]==-1) System.out.print("○");
                else System.out.print("□");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void splay(){
        int k=0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(search(i,j,c[z])&&table[i][j][z]==0){
                    System.out.println(k+"回目");
                    putStone(i,j,c[z]);
                    c[z]=nextStone(c[z-1]*(-1));
                    k++;
                    i=0;j=-1;
                }
            }
        }
        rEview();
    }

    public void searchShape(){
    try{
        
        if(judgeCom()){
           System.out.println("一致しました");
        }else{//この中で再起を用いて目的の形ができるまでループさせる。
                for(int i=0;i<size;i++){
                    for(int j=0;j<size;j++){
                        if(search(i,j,c[z])&&table[i][j][z]==0){
                            //行き側の工程
                            System.out.print(k+"回目:");k++;
                            putStone(i,j,c[z]);c[z]=nextStone(c[z-1]*(-1));
                            searchShape();
    
                            //戻り側の工程
                            //c=subC;//ひとつ前に戻す
                            backZ();
                            System.out.println(z);
                        }
                    }
                }
        }

        
    }catch(Exception e){
        System.out.println(e); 
        //z--;
        //rEview();
    }
    }

}

//このままでは埒がないので、4×4の小さなオセロボードを作って実行する。
