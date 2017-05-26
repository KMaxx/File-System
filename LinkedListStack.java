/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Stack;

/**
 *
 * @author User
 * @param <T>
 */
public class LinkedListStack<T> implements Stack<T>{

    private Node<T> top;
    private int size;
    
    public LinkedListStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(T value) {
        Node<T> n = new Node(value);
        if(top == null) {
            top = n;
        }else{
            n.setLink(top);
            top = n;
        }
        size++;
    }

    @Override
    public T pop() throws Exception {
        if(size == 0){
            throw new Exception ("Empty Stack!");
        }
        else if(size == 1) {
            T result = top.getValue();
            top = null;
            size--;
            return result;
        }
        T result = top.getValue();
        top = top.getLink();
        size--;
        return result;
    }
    
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }
    
    @Override
    public String toString() {
        String res = "Top [ | ";
        Node<T> a = top;
        while(a != null) {
                res += " | " + a.getValue();
                a = a.getLink();
            }
        return res + "] Bottom";
    }
}
