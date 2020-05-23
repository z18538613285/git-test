package linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "王麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("遍历-------------------");
        doubleLinkedList.list();

        System.out.println("修改-----------------------");
        HeroNode newHeroNode = new HeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.updata(newHeroNode);
        doubleLinkedList.list();

        System.out.println("删除-----------------------");
        doubleLinkedList.del(2);
        doubleLinkedList.list();

        System.out.println("顺序添加-----------------------");
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.list();
        System.out.println("-----------------------");
    }


    static class DoubleLinkedList {
        private HeroNode head = new HeroNode(0, "", "");

        public HeroNode getHead() {
            return head;
        }

        public void list() {
            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            while (true) {
                if (temp == null) {
                    break;
                }
                System.out.println(temp);
                temp = temp.next;
            }
        }

        public void add(HeroNode heroNode) {
            HeroNode temp = head;
            while (true) {
                //当
                if (temp.next == null) {
                    break;
                }
                temp = temp.next;
            }//当退出while时，temp指向了链表的最后
            temp.next = heroNode;
            heroNode.pre = temp;
        }

        public void addByOrder(HeroNode heroNode) {
            //需要一个辅助节点找到添加的位置
            HeroNode temp = head.next;
            boolean flag = false;//添加的编号是否存在
            while (true) {
                if (temp == null) {//说明temp已经在链表的最后
                    break;
                }
                if (temp.no > heroNode.no) {
                    break;
                } else if (temp.no == heroNode.no) {//说明编号已存在
                    flag = true;
                    break;
                }
                temp = temp.next;//后移，遍历
            }
            //判断flag
            if (flag) {
                System.out.println("准备插入的英雄" + heroNode.name + "已存在！编号为：" + heroNode.no);
            } else {
                heroNode.next = temp;
                heroNode.pre = temp.pre;
                temp.pre.next = heroNode;
                heroNode.next = temp;
            }
        }

        public void updata(HeroNode newheroNode) {
            if (head.next == null) {
                System.out.println("链表为空");
            }
            HeroNode temp = head.next;
            boolean flag = false;//表示是否找到
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.no == newheroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.name = newheroNode.name;
                temp.nickname = newheroNode.nickname;
            } else {
                System.out.println("没有找到编号为" + newheroNode.no + "的节点");
            }
        }

        //找到节点，自我删除
        public void del(int no) {

            if (head.next == null) {
                System.out.println("链表为空");
                return;
            }
            HeroNode temp = head.next;
            boolean flag = false;//表示是否找到
            while (true) {
                if (temp == null) {
                    break;
                }
                if (temp.no == no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.pre.next = temp.next;
                if (temp.next == null) {//是否是最后一个节点
                    return;
                }
                temp.next.pre = temp.pre;
            } else {
                System.out.println("要删除的" + no + "号节点不存在");
            }
        }


    }


    static class HeroNode {
        public int no;
        public String name;
        public String nickname;
        public HeroNode next;//指向下一个结点
        public HeroNode pre;//指向前一个结点

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