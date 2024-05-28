package ai;

import java.util.ArrayList;

import main.GamePanel;

public class Pathfinder {
    GamePanel gp;
    Node[][] node;
    ArrayList<Node> ol = new ArrayList<>();
    public ArrayList<Node> pl = new ArrayList<>();
    Node startN, goalN, currentN;
    boolean goalReached = false;
    int step = 0;

    public Pathfinder(GamePanel _gp){
        gp = _gp;
        instNodes();
    }

    public void instNodes(){
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row] = new Node(col, row);

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }




    public void resetNodes(){
        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){
            node[col][row].open = false;
            node[col][row].checked = false;
            node[col][row].solid = false;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }


        ol.clear();
        pl.clear();
        goalReached = false;
        step = 0;
    }




    public void setNodes(int startC, int startR, int goalC, int goalR){

        resetNodes();

        startN = node[startC][startR];
        currentN = startN;
        goalN = node[goalC][goalR];
        ol.add(currentN);

        int col = 0;
        int row = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow){

            int tileNum = gp.tm.mapTileNum[gp.currentMap][col][row];
            if(gp.tm.tile[tileNum].collision == true){
                node[col][row].solid = true;
            }
            for (int i = 0; i < gp.obj[1].length; i++) {
                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].collision == true){
                    int oCol = gp.obj[gp.currentMap][i].worldX/gp.tileSize;
                    int oRow = gp.obj[gp.currentMap][i].worldY/gp.tileSize;
                    node[oCol][oRow].solid = true;
                }
            }


            getCost(node[col][row]);

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
            }
        }
    }



    public void getCost(Node node){
        int xDistance = Math.abs(node.col - startN.col);
        int yDistance = Math.abs(node.row - startN.row);
        node.gCost = xDistance + yDistance;

        xDistance = Math.abs(node.col - goalN.col);
        yDistance = Math.abs(node.row - goalN.row);
        node.hCost = xDistance + yDistance;

        node.fCost = node.gCost + node.hCost;
    }







    public boolean search(){

        while(goalReached == false && step < 1000){
            int col = currentN.col;
            int row = currentN.row;

            currentN.checked = true;
            ol.remove(currentN);

            if(row - 1 >= 0){
                openN(node[col][row-1]);
            }
            if(col - 1 >= 0){
                openN(node[col-1][row]);
            }
            if(row + 1 < gp.maxWorldRow){
                openN(node[col][row+1]);
            }
            if(col + 1 < gp.maxWorldCol){
                openN(node[col+1][row]);
            }

            int bestNodeI = 0;
            int bestNodefCost = 999;

            for (int i = 0; i < ol.size(); i++) {
                if(ol.get(i).fCost < bestNodefCost){
                    bestNodeI = i;
                    bestNodefCost = ol.get(i).fCost;
                }

                else if(ol.get(i).fCost == bestNodefCost){
                    if(ol.get(i).gCost < ol.get(bestNodeI).gCost){
                        bestNodeI = i;
                    }
                }
            }

            if(ol.size() == 0){
                break;
            }

            currentN = ol.get(bestNodeI);

            if(currentN == goalN){
                goalReached = true;
                trackThePath();
            }
            step++;
        }

        return goalReached;
    }


    public void openN(Node node){
        if(node.open == false && node.checked == false && node.solid == false){
            node.open = true;
            node.parent = currentN;
            ol.add(node);
        }
    }



    public void trackThePath(){
        Node current = goalN;

        while(current != startN){
            pl.add(0, current);
            current = current.parent;
        }

    }
}
