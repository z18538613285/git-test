package stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "900+20*10+22";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch = ' ';
        String keepNum="";//用于拼接数字
        while (true){
            ch = expression.substring(index,index+1).charAt(0);
            //判断是否是运算符
            if (operStack.isOper(ch)){
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()){
                    //不为空。当前符号与栈内符号比较优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else {
                        //优先级大于栈内，直接存入
                        operStack.push(ch );
                    }
                }else {
                    //站内没有运算符，直接存入
                    operStack.push(ch);
                }

            }else {
                //是数字
//                numStack.push(ch-48);
                //处理多位数，看后面是否还是数字
                keepNum +=ch;
                //如果ch是表达式最后一位
                if (index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                    //如果后一位是符号
                    numStack.push(Integer.parseInt(keepNum));
                    //清空keepNum
                    keepNum="";
                }


            }
            //判断是否存完表达式
            index++;
            if (index >=expression.length()){
                break;
            }
        }
        //开始运算
        while (true){
            if (operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.println("表达式"+expression+"的值为："+numStack.peek());
    }

    static class ArrayStack2 {
        private int maxSize;
        private int[] stack;
        private int top = -1;

        public ArrayStack2(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        public int peek(){
            return stack[top];
        }

        public boolean isFull() {
            return top == maxSize - 1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int value) {
            if (isFull()) {
                System.out.println("栈满");
                return;
            }
            top++;
            stack[top] = value;
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("栈为空");
            }
            int value = stack[top];
            top--;
            return value;
        }

        //遍历时，需要从栈顶开始显示数据
        public void list() {
            if (isEmpty()) {
                System.out.println("栈为空");
                return;
            }
            for (int i = top; i >= 0; i--) {
                System.out.println("stack[" + i + "]" + stack[i]);
            }

        }
        public int priority(int oper){
            if (oper=='*' || oper == '/'){
                return 1;
            } else if (oper=='+' || oper == '-'){
                return 0;
            }else {
                return -1;//假定目前的表达式只有+，-，*，/
            }

        }
        //判断是否是运算符
        public boolean isOper(char val){
            return val == '+' || val == '-' || val == '*' || val == '/';
        }

        //计算方法
        public int cal(int num2,int num1 ,int oper){
            int res = 0;//存放计算结果
            switch (oper){
                case '+':
                    res = num1 + num2;
                    break;
                case '-':
                    res = num1 - num2;
                    break;
                case '*':
                    res = num1 * num2;
                    break;
                case '/':
                    res = num1 / num2;
                    break;
                default:
                    break;
            }
            return res;
        }
    }
}