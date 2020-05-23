package stack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        /*
            将一个中缀表达式转为后缀表达式
            1+((2+3)*4)-5  -->1 2 3 + 4 * + 5 -
            1、现将String表达式转为ArrayList方便操作 [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

            2、将得到的list转为后缀表达式的list-->[1, 2, 3, +, 4, *, +, 5, -]
         */
        String expression = "1+((2+3)*4)-5";
        List<String> stringList = toInfixExpressionList(expression);
        System.out.println("中缀表达式：" + stringList);
        List<String> stringsList1 = parseSuffixExpressionList(stringList);
        System.out.println("后缀表达式：" + stringsList1);
        System.out.println("结果=" + calculate(stringsList1));

/*        //先定义逆波兰表达式
        //（3+4）*5-6
        //数组和符号隔开
//        String suffixExpression = "30 4 + 5 * 6 - ";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / + ";
        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList="+rpnList);

        int res=calculate(rpnList);
        System.out.println("计算的结果="+res);*/

    }

    //将中缀转为后缀
    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<String>();//符号栈，存储符号
        List<String> s2 = new ArrayList<>();//存储中间结果

        for (String item : list) {
            //数字加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号，则依次弹出s1栈的运算符，并压入s2直至遇到左括号为止，此时这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将“（”弹出，消除小括号
            } else {
                //当item优先级小于等于s1栈顶，将s1的运算符弹出并加入s2中，之后再比较下面的符号
                //
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入s1栈中
                s1.push(item);
            }
        }
        //将剩余的运算符依次加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到List，因此按顺序输出就是对应的逆波兰表达式

    }

    //将中缀表达式转为List
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;//指针，用于遍历字符串
        String str;//对多位数的拼接
        char c;//没遍历一个字符，放入其中
        do {
            //如果c是一个非数字，加入集合
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add(c + "");
                i++;
            } else {//数字考虑多位数
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }


    //将一个逆波兰表达式放入ArrayList
    public static List<String> getListString(String suffixExpression) {
        //将其分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成运算
    public static int calculate(List<String> ls) {
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if (item.matches("\\d+")) {//匹配的多位数
                stack.push(item);
            } else {
                //pop出两个数，运算在入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }

        }
        //最后一个为结果
        return Integer.parseInt(stack.pop());
    }

    public static class Operation {
        private  static int ADD = 1;
        private static int SUB = 1;
        private static int MUL = 2;
        private static int DIV = 2;

        public static int getValue(String operation) {
            int result = 0;
            switch (operation) {
                case "+":
                    result = ADD;
                    break;
                case "-":
                    result = SUB;
                    break;
                case "*":
                    result = MUL;
                    break;
                case "/":
                    result = DIV;
                    break;
                default:
                    break;
            }
            return result;

        }

    }
}