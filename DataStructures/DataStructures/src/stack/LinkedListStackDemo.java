package stack;

import linkedlist.SingleLinkedListDemo;

import java.util.Scanner;
import java.util.Stack;

public class LinkedListStackDemo {
    public static void main(String[] args) {
        LinkedListStack stack = new LinkedListStack(4);

        String key = "";
        boolean loop = true;
        Scanner sc = new Scanner(System.in);

        while (loop) {
            System.out.println("show：显示栈");
            System.out.println("exit：退出程序");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.println("请输入你的选择：");
            key = sc.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int value = sc.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("出栈的数据：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    sc.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出！");
    }


    static class LinkedListStack {

        //初始化一个头结点，头结点不要动，不存放具体的数据
        private HeroNode head = new HeroNode(0);
        private int count;
        private int maxSize;

        public LinkedListStack(int maxSize) {
            this.maxSize = maxSize;
        }

        public void push(int no) {
            HeroNode temp = head;
            HeroNode cur = new HeroNode(no);
            if (count == maxSize) {
                System.out.println("栈满");
                return;
            }
            while (true) {
                //当
                if (temp.getNext() == null) {
                    break;
                }
                temp = temp.getNext();
            }//当退出while时，temp指向了链表的最后
            count++;
            temp.setNext(cur);
        }


        public int pop() {
            HeroNode temp = head;

            if (count == 0) {
                throw new RuntimeException("栈为空");
            }
            for (int i = 1; i < count; i++) {
                temp = temp.getNext();
            }
            count--;
            int value = temp.getNext().getNo();
            temp.setNext(null);
            return value;

        }


        public void list() {
            if (count == 0) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.getNext();


            for (int i = 1; i <= count; i++) {
                for (int j = 1; j <= count - i; j++) {
                    temp = temp.getNext();
                }
                System.out.println(temp);
                temp = head.getNext();
            }
        }
    }

    static class HeroNode {
        private int no;
        private HeroNode next;//指向下一个结点

        //构造器
        public HeroNode(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public HeroNode getNext() {
            return next;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }


        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    '}';
        }
    }
}