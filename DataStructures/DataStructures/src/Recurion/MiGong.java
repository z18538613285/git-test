package Recurion;

public class MiGong {
    public static void main(String[] args) {
        //建立迷宫数组
        int[][] map = new int[8][7];
        //使用1表示墙；
        //上下置1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        //输出数据
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"   ");
            }
            System.out.println();
        }

        setWay2(map , 1 ,1 );
        //输出数据
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j]+"   ");
            }
            System.out.println();
        }


    }

    /*
            使用递归回溯来给小球找路
            1、如果小球能到map[6][5] 位置，则说明找到
            2、当map[i][j] 为0：表示该点没有走过
                                      1：表示为墙
                                      2：表示通路可以走
                                      3：表示该点已经走过，但走不通
             3、走迷宫策略：下->右->上->左，如果该点走不通，在回溯
     */

    /**
     *
     * @param map 表示地图
     * @param i 从哪个位置开始找
     * @param j
     * @return 如果找到通路。就返回true，否则返回false
     */
    public static boolean setWay(int[][] map,int i, int j ){

        if (map[6][5] == 2){//通路已经找到ok
            return true;
        }else {
            if (map[i][j] ==0 ){ //如果当前这个点没有走过
                //按照策略 下->右->上->左 走
                map[i][j] =2; //假定该点可以走通
                if (setWay(map,i+1,j)){//向下走
                    return true;
                }else if (setWay(map,i,j+1)){//向右走
                    return true;
                }else if (setWay(map,i-1,j)){//向上走
                    return true;
                }else if (setWay(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点走不通，是死路
                    map[i][j]=3;
                    return false;
                }
            }else { //如果map[i][j] !=0，可能是1,2,3
                return false;
            }
        }
    }

    //改变策略 上->右->下->左
    public static boolean setWay2(int[][] map,int i, int j ){

        if (map[6][5] == 2){//通路已经找到ok
            return true;
        }else {
            if (map[i][j] ==0 ){ //如果当前这个点没有走过
                //按照策略 下->右->上->左 走
                map[i][j] =2; //假定该点可以走通
                if (setWay2(map,i-1,j)){//向上走
                    return true;
                }else if (setWay2(map,i,j+1)){//向右走
                    return true;
                }else if (setWay2(map,i+1,j)){//向下走
                    return true;
                }else if (setWay2(map,i,j-1)){//向左走
                    return true;
                }else {
                    //说明该点走不通，是死路
                    map[i][j]=3;
                    return false;
                }
            }else { //如果map[i][j] !=0，可能是1,2,3
                return false;
            }
        }
    }

}
