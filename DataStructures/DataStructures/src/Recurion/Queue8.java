package Recurion;

public class Queue8 {
    //定义一个max有过个皇后
    int max = 8;
    //定义数组array，保存皇后放置的结果，比如 arr = {0,4,7,5,2,6,1,3}
    int[] array = new int[max];
    static int count = 0;
    static int judge = 0;
    public static void main(String[] args) {
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有"+count+"解法");
        System.out.println("一共判断冲突的次数："+judge);

    }

    /*
        方法，放置第n个皇后
        注意：check是每一次递归，都有8次循环，因此会有回溯
     */
    private void check(int n){
        if (n==max){//第9个皇后，其实8个皇后已经放完
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先放在第一列
            array[n] = i;
            //判断是否冲突
            if (judge(n)){
                //不冲突，接着放，开始递归
                check(n+1);
            }
            //如果冲突就继续执行array[n] = i ，即 将这个皇后放入本行的下一个位置
        }
    }

    //查看放置的皇后与已经摆放的是否冲突

    /**
     *
     * @param n 表示第几个皇后
     * @return
     */
    private boolean judge(int n){
        judge++;
        for (int i = 0; i < n; i++) {
            /*
                1、array[i] == array[n] 判断是否同一列
                2、Math.abs(n-i) == Math.abs((array[n] -array[i])) 判断是否同一斜线
                3、n每次递增，不会再同一行
             */
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs((array[n] -array[i]))){
                return false;
            }
        }
        return true;
    }

    //写一个方法，将皇后的位置输出
    private void print(){
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+"   ");
        }
        System.out.println();
    }
}
