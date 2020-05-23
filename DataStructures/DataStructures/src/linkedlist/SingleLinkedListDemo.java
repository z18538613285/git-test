package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "王麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(5, "林冲", "豹子头");
        HeroNode hero6 = new HeroNode(6, "林冲", "豹子头");
        HeroNode hero7 = new HeroNode(7, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();

        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero5);
        singleLinkedList1.addByOrder(hero7);

        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero6);
        singleLinkedList1.list();
        System.out.println("----------------");
        singleLinkedList2.list();
        System.out.println("----------------");

        SingleLinkedList append = append(singleLinkedList1, singleLinkedList2);
        append.list();
        System.out.println("-------------");

      /*  singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.list();
        System.out.println("-----------------------------");
        HeroNode newheronode = new HeroNode(2, "小卢", "小玉龙");
        singleLinkedList.updata(newheronode);
        singleLinkedList.list();
        System.out.println("---------------------------------");
        singleLinkedList.del(1);
        singleLinkedList.list();
        System.out.println("---------------------------------");

        singleLinkedList.del(2);
        singleLinkedList.del(3);
        singleLinkedList.list();
        System.out.println("---------------------------------");

        singleLinkedList.del(4);
        singleLinkedList.list();
        System.out.println("---------------------------------");*/
        /*System.out.println("有效的节点个数="+getLength(singleLinkedList.head));

        HeroNode res=findLastIndexNode(singleLinkedList.head,1);
        System.out.println("倒数第一个节点："+res);
        HeroNode res1=findLastIndexNode(singleLinkedList.head,4);
        System.out.println("倒数第四个节点："+res1);

        reverseList(singleLinkedList.head);
        System.out.println("反转之后：");
        singleLinkedList.list();*/
/*        System.out.println("逆序打印");
        reversePrint(singleLinkedList.head);*/





    }

    //使用栈逆序打印
    public static void reversePrint(HeroNode head){
        if (head.next==null){
            return;
        }
        Stack<HeroNode> stack=new Stack<>();
        HeroNode temp =head.next;
        while (temp!=null){
            stack.push(temp);
            temp=temp.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    //将两个有序的单链表添加到一个有序的单链表中
    public static SingleLinkedList append(SingleLinkedList singleLinkedList1,SingleLinkedList singleLinkedList2){
        SingleLinkedList appendsingleLinkedList=new SingleLinkedList();
        HeroNode temp1=singleLinkedList1.head.next;
        HeroNode temp2=singleLinkedList2.head.next;
        HeroNode temp3=appendsingleLinkedList.head;

            while (temp2!=null&& temp1!=null){
                if (temp1.no<temp2.no){
                    temp3.next=temp1;
                    temp3=temp3.next;
                    temp1=temp1.next;//temp后移
                }else if (temp1.no>temp2.no){
                    temp3.next=temp2;
                    temp3=temp3.next;
                    temp2=temp2.next;//temp后移
                }
            }
        while ( temp2!=null){
            temp3.next=temp1;
            temp3=temp3.next;
            temp1=temp1.next;//temp后移
        }
        while ( temp1!=null){
            temp3.next=temp1;
            temp3=temp3.next;
            temp1=temp1.next;//temp后移
        }
        return appendsingleLinkedList;
    }

    //将单链表反转
    public static void reverseList(HeroNode head){
        if (head.next==null || head.next.next==null){
            return;
        }
        HeroNode temp=head.next;
        HeroNode next =null;//指向当前链表temp的下一个节点
        HeroNode reverseList = new HeroNode(0,"","");
        //每遍历一个节点，就将其取出放在新的链表最前端
        while (temp!=null){
            next=temp.next;//暂时保存当前节点的下一个节点
            temp.next=reverseList.next;//将temp的下一个节点指向新的链表恶毒最前端
            reverseList.next=temp;//将temp连接到新的节点
            temp=next;//temp后移
        }
        head.next=reverseList.next;
    }

    //查找单链表的倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next==null){
            return null;
        }
        int size=getLength(head);
        if (index<=0 || index >size){
            return null;
        }
        HeroNode temp=head.next;
        for (int i = 0; i < size - index; i++) {
            temp=temp.next;
        }
        return temp;
    }


    //获得单链表的节点个数（不包括头节点）
    public static int getLength(HeroNode head){
        if (head.next==null){
            return 0;
        }
        int length=0;
        HeroNode cur=head.next;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }



    //定义SingleLinkedList 管理我们的英雄
    public static class SingleLinkedList{
        //初始化一个头结点，头结点不要动，不存放具体的数据
        private HeroNode head=new HeroNode(0,"","");

        //添加结点入链表
        /*
            找到当前链表的最后结点
            最后将这个结点的next 指向新的结点
         */
        public void add(HeroNode heroNode){
            HeroNode temp = head;
            while (true){
                //当
                if(temp.next ==null){
                    break;
                }
                temp = temp.next;
            }//当退出while时，temp指向了链表的最后
            temp.next=heroNode;
        }

        //根据排名添加，如果排名相同，添加失败
        public void addByOrder(HeroNode heroNode){
            //需要一个辅助节点找到添加的位置
            HeroNode temp =head;
            boolean flag=false;//添加的编号是否存在
            while (true){
                if (temp.next==null){//说明temp已经在链表的最后
                    break;
                }
                if (temp.next.no > heroNode.no){
                    break;
                }else if (temp.next.no == heroNode.no){//说明编号已存在
                    flag =true;
                    break;
                }
                temp=temp.next;//后移，遍历
            }
            //判断flag
            if (flag){
                System.out.println("准备插入的英雄"+heroNode.name+"已存在！编号为："+heroNode.no);
            }else {
                heroNode.next=temp.next;
                temp.next=heroNode;
            }
        }

        //修改节点的信息，编号不能改，根据编号来修改
        public void updata(HeroNode newheroNode){
            if (head.next==null){
                System.out.println("链表为空");
            }
            HeroNode temp=head.next;
            boolean flag=false;//表示是否找到
            while (true){
                if (temp==null){
                    break;
                }
                if (temp.no==newheroNode.no){
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            if (flag){
                temp.name=newheroNode.name;
                temp.nickname=newheroNode.nickname;
            }else {
                System.out.println("没有找到编号为"+newheroNode.no+"的节点");
            }
        }

        public void del(int no){
            HeroNode temp=head;
            boolean flag=false;//表示是否找到
            while (true){
                if (temp.next==null){
                    break;
                }
                if (temp.next.no==no){
                    flag=true;
                    break;
                }
                temp=temp.next;
            }
            if (flag){
                temp.next=temp.next.next;
            }else {
                System.out.println("要删除的"+no+"号节点不存在");
            }
        }

        public void list(){
            if (head.next==null){
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            while (true){
                if (temp==null){
                    break;
                }
                System.out.println(temp);
                temp=temp.next;
            }
        }


    }




    public static class HeroNode{
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;//指向下一个结点

        //构造器

        public HeroNode(int no, String name, String nickname) {
            this.no = no;
            this.name = name;
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickname='" + nickname + '\'' +
                    '}';
        }
    }
}
