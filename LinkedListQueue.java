/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;
import csci152.adt.Queue;

/**
 *
 * @author User
 * @param <T>
 */
public class LinkedListQueue<T> implements Queue<T> {

    private Node<T> front;
    private Node<T> back;
    int size;
    
    public LinkedListQueue(){
        front = null;
        back = null;
        size = 0;
    }
    
    @Override
    public void enqueue(T value) {
        Node<T> a = new Node(value);
        if(size==0) {
            front = a;
            back = a;
        }
        else{
            back.setLink(a);
            back = a;
        }
        size++;
    }

    @Override
    public T dequeue() throws Exception {
        if(size == 0){
            throw new Exception("Empty Queue.");
        }
        else if(size == 1) {
            T result = front.getValue();
            front = null;
            back = null;
            size--;
            return result;
        }
        else{
            T result = front.getValue();
            front = front.getLink();
            size--;
            return result;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
        size = 0;
    }
    
    @Override
    public String toString(){
        System.out.print("[ | ");
        Node<T> a = front;
        while(a != null){
            System.out.print(a.getValue() + " | ");
            Node<T> next = a.getLink();
            a = next;
        }
        return "]";
    }
}
