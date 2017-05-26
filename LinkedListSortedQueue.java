/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;
import csci152.adt.SortedQueue;

/**
 *
 * @author User
 * @param <T>
 */
public class LinkedListSortedQueue<T extends Comparable> implements SortedQueue<T> {
    
    private Node<T> front;
    private int size;

    public LinkedListSortedQueue(){
        front = null;
        size = 0;
    }
    
    @Override
    public void insert(T value) {
        Node<T> newNode = new Node(value);
        if(size == 0) {
            front = newNode;
        } 
        else if (value.compareTo(front.getValue()) <= 0) {
            newNode.setLink(front);
            front = newNode;
        }
        else {
            Node<T> a = front;
            while(true) {
                if(a.getLink() != null) {
                    if(value.compareTo(a.getValue()) >= 0 && value.compareTo(a.getLink().getValue()) < 0 ) {
                        newNode.setLink(a.getLink());
                        a.setLink(newNode);
                        break;
                    }
                    a = a.getLink();
                }
                else {
                    a.setLink(newNode);
                    break;
                }
            }
        }
        size++;
    }

    @Override
    public T dequeue() throws Exception{
        if(size == 0){
            throw new Exception("Empty Queue.");
        }
        T result = front.getValue();
        if(size == 1) {
            front = null;
        }
        else{
            front = front.getLink();
        }
        size--;
        return result;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        front = null;
        size = 0;
    }
    
    @Override
    public String toString(){
        System.out.print("Front [ | ");
        Node<T> a = front;
        while(a != null){
            System.out.print(a.getValue() + " | ");
            a = a.getLink();
        }
        return "] Back";
    }
}
