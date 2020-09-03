package com.example.knowledge.interview.algorithm;

import java.util.Stack;

/**
 * 问题：实现一个栈，该栈带有出栈，入栈，取最小元素3个方法。要保证这3个方法的时间复杂度都为O(1)
 */
public class StackMinValue {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minSrack = new Stack<>();

    /**
     * 入栈操作
     * @param element
     */
    public void push(int element){
        mainStack.push(element);

        if(minSrack.isEmpty() || element <= minSrack.peek()){
            minSrack.push(element);
        }
    }

    /**
     * 出栈操作
     * @return
     */
    public Integer pop(){
        if(mainStack.peek().equals(minSrack.peek())){
            minSrack.pop();
        }

        return mainStack.pop();
    }

    /**
     * 获取最小元素
     * @return
     * @throws Exception
     */
    public int getMin() throws Exception{
        if (mainStack.empty()){
            throw new Exception("stack is Empty");
        }
        return minSrack.peek();
    }

    public static void main(String[] args) throws Exception {
        StackMinValue minValue = new StackMinValue();
        minValue.push(4);
        minValue.push(9);
        minValue.push(7);
        minValue.push(3);
        minValue.push(8);
        minValue.push(5);
        System.out.println(minValue.getMin());
        minValue.pop();
        minValue.pop();
        minValue.pop();
        System.out.println(minValue.getMin());
    }

}
