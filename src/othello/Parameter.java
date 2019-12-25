package othello;

public enum Parameter {
    size(18), 
    xwidth(400),
    ywidth(400),
    xzero(200),
    yzero(44);

    public final int num;

    private Parameter(int num){
        this.num = num;
    }

    public int get(){
        return num;
    }
}