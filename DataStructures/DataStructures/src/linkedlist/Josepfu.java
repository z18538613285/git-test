package linkedlist;

public class Josepfu {
    public static void main(String[] args) {

        CircleSingleLinkedList csll = new CircleSingleLinkedList();
        csll.addBoy(25);
        csll.showBoy();
        csll.countBoy(1, 2, 5);
    }


    //创建环形单向链表
    static class CircleSingleLinkedList {
        //创建一个first节点，当前没有编号
        private Boy first = null;

        public void addBoy(int nums) {
            if (nums < 1) {
                System.out.println("nums的值不正确");
            }

            Boy curBoy = null;
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i);
                if (i == 1) {
                    first = boy;
                    first.setNext(first);//构成环
                    curBoy = first;
                } else {
                    curBoy.setNext(boy);
                    boy.setNext(first);
                    curBoy = boy;
                }
            }
        }

        public void showBoy() {
            if (first == null) {
                System.out.println("链表为空");
                return;
            }
            Boy curBoy = first;
            while (true) {
                System.out.println("小孩的编号：" + curBoy.getNo());
                if (curBoy.getNext() == first) {
                    break;
                }
                curBoy = curBoy.getNext();//后移

            }
        }

        //根据用户的输入，计算出小孩出圈的顺序

        /**
         * @param startNo  表示从第几个小孩开始数
         * @param countNum 表示数几下
         * @param nums     表示最初有几个小孩
         */
        public void countBoy(int startNo, int countNum, int nums) {
            if (first == null || startNo < 1 || startNo > nums) {
                System.out.println("参数输入有误，请重新输入");
                return;
            }
            //辅助节点事先指向最后一个节点
            Boy helper = first;
            while (true) {
                if (helper.getNext() == first) {//说明helper指向最后小孩节点
                    break;
                }
                helper = helper.getNext();
            }
            //小孩报数前，先让first和helper移动 k-1 次
            for (int i = 0; i < startNo - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //当小孩报数时，让first和helper指针同时移动 m-1 次，然后出圈
            //这是一个循环操作，直到圈中只有一个节点
            while (true) {
                if (helper == first) {
                    break;
                }
                //让first和helper同时移动counttNum - 1
                for (int i = 0; i < countNum - 1; i++) {
                    first = first.getNext();
                    helper = helper.getNext();
                }
                //这是first指向的节点就是要出圈的小孩节点
                System.out.println("小孩" + first.getNo() + "出圈");
                first = first.getNext();
                helper.setNext(first);
            }
            System.out.println("最后留在圈中的小孩编号是" + first.getNo());


        }

    }


    static class Boy {
        private int no;//编号
        private Boy next;//指向下一个节点

        public Boy(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public Boy getNext() {
            return next;
        }

        public void setNext(Boy next) {
            this.next = next;
        }
    }
}