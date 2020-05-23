package sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        // 0：表示没有棋子，1：表示黑子，2：表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的数组
        System.out.println("原始的数组……");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data+"     ");
            }
            System.out.println();
        }
        //将二维数组转为稀疏数组
        //1、先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        //2、创建对应的稀疏数组
        int[][] sparseArr1 = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr1[0][0] = chessArr1.length;
        sparseArr1[0][1] = chessArr1[0].length;
        sparseArr1[0][2] = sum;
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr1[count][0] = i;
                    sparseArr1[count][1] = j;
                    sparseArr1[count][2] = chessArr1[i][j];

                }
            }
        }
        //输出稀疏数组
        System.out.println("稀疏数组……");
        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < sparseArr1[0].length; row++) {
                System.out.print(sparseArr1[col][row]+"     ");
            }
            System.out.println();
        }

        //稀疏数组转为原始数组
        int[][] chessArr2 =new int[sparseArr1[0][0]][sparseArr1[0][1]];
        for (int i = 1; i < sparseArr1.length; i++) {
            chessArr2[sparseArr1[i][0]][sparseArr1[i][1]] = sparseArr1[i][2];
        }
        //输出转换后的二维数组
        System.out.println("转换后的二维数组……");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data+"     ");
            }
            System.out.println();
        }
    }
}
