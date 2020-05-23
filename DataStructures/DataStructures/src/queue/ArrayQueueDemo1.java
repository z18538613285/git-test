package queue;

import java.util.Scanner;

public class ArrayQueueDemo1 {

    public static void main(String[] args) {

        //测试
        ArrayQueue arrayQueue =new ArrayQueue(3);
        char key = ' ';  //接收用户输入
        Scanner sc = new Scanner(System.in);
        boolean flag=true;
        //输出一个菜单
        while (flag){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");

            key =sc.next().charAt(0);//接收字符

            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = sc.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    int res = arrayQueue.getQueue();
                    break;
                case 'h':
                    int num=arrayQueue.headQueue();
                    System.out.println("队列的头数据："+num);
                    break;
                case 'e':
                    sc.close();
                    flag=false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出！");

    }


    public static class ArrayQueue {
        private int maxSize; //表示队列的最大容量8
        private int front; //队列头
        private int rear;  //队列尾
        private int[] arr;  //该数组用于存放数据，模拟队列

        //创建队列的模拟器
        public ArrayQueue(int arrMaxSize) {
            this.maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1;  //指向队列头部，分析出front是指向队列头部的前一个位置
            rear = -1;   //指向队列队尾，指向队列尾的数据（即队列的最后一个数据）
        }

        //判断队列是否满
        public boolean isFull(){
            return rear==maxSize-1;
        }

        //判断队列是否为空
        public boolean isEmpty(){
            return rear==front;
        }

        //添加数据到队列
        public void addQueue(int n){
            //判断队列是否满
            if (this.isFull()){
                System.out.println("队列满，不能添加数据！");
                return;
            }
            rear++;
            arr[rear]=n;
        }

        //获取队列数据，出队列
        public int getQueue(){
            //判断队列是否空
            if(this.isEmpty()){
                //通过抛出异常
                throw new RuntimeException("队列空，不能取数据！");
            }

            front++;
            return arr[front];
        }

        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if (this.isEmpty()){
                System.out.println("队列为空，没有数据！");
                return;
            }
            for (int i = 0; i < arr.length; i++) {
                System.out.println("arr["+i+"] = "+arr[i]);
            }
        }

        //显示队列的头数据，
        public int headQueue(){
            //判断
            if (this.isEmpty()){
                throw new RuntimeException("队列为空，没有数据！");
            }
            return arr[front+1];
        }

    }
}
