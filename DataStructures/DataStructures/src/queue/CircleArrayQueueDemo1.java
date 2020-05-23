package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo1 {

    public static void main(String[] args) {

        //测试
        CircleArray circleArray =new CircleArray(4);//有效数据最大为三
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
                    circleArray.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = sc.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    int res = circleArray.getQueue();
                    break;
                case 'h':
                    int num=circleArray.headQueue();
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

/*
    当队列满时 (rear + 1) % maxSize = front
    队列为空时 rear=front
    队列中有效的数据个数 (rear + maxSize - front)%maxSize
 */
    public static class CircleArray {
        private int maxSize; //表示队列的最大容量8
        private int front; //队列头
        //front指向队列的第一个元素，初始值为0
        private int rear;  //队列尾
        //rear指向队列的最后一个元素的后一个位置，初始值为0
        private int[] arr;  //该数组用于存放数据，模拟队列

        //创建队列的模拟器
        public CircleArray(int arrMaxSize) {
            this.maxSize = arrMaxSize;
            arr = new int[maxSize];
        }

        //判断队列是否满
        public boolean isFull(){
            return (rear + 1) % maxSize ==front;
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
            }else{

                arr[rear]=n;
                rear=(rear+1)%maxSize;
            }
        }

        //获取队列数据，出队列
        public int getQueue(){
            //判断队列是否空
            if(isEmpty()){
                throw new RuntimeException("队列为空，无法取数据！");

            }
                int value=arr[front];
                front=(front+1)%maxSize;
                return value;

        }

        //显示队列的所有数据
        public void showQueue(){
            //遍历
            if (this.isEmpty()){
                System.out.println("队列为空，没有数据！");
                return;
            }else{
                //从front开始遍历
                for (int i = front; i < front+size(); i++) {
                    System.out.println("arr["+i%maxSize+"] = "+arr[i%maxSize]);
                }
            }

        }

        //求出当前队列有效数据的个数
        public int size(){
            return (rear + maxSize - front)%maxSize;
        }

        //显示队列的头数据，
        public int headQueue(){
            //判断
            if (this.isEmpty()){
                throw new RuntimeException("队列为空，没有数据！");

            }
                return arr[front];

        }

    }
}
