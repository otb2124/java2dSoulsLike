package ai;

public class Node {
    
    Node parent;
    public int col, row;
    int gCost, hCost, fCost;
    boolean solid, open, checked;

    public Node(int _col, int _row){
        col = _col;
        row = _row;
    }
}
