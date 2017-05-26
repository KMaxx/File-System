/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci152.impl;

import csci152.adt.Set;

/**
 *
 * @author User
 * @param <T>
 */
public class LLQHashTableSet<T> implements Set<T> {

    private LinkedListQueue<T>[] buckets;
    private int size;
    
    public LLQHashTableSet (int n) {
        buckets = new LinkedListQueue[n];
        for(int i = 0; i < n; i++) {
            buckets[i] = null;
        }
        size = 0;
    }
    
    @Override
    public void add(T value) {
        if(!contains(value)) {  
            int h = Math.abs(value.hashCode()) % buckets.length;
            if(buckets[h] == null) {
                buckets[h] = new LinkedListQueue();
            }
            buckets[h].enqueue(value);
            size++;
        }
    }

    @Override
    public boolean contains(T value) {
        int h = Math.abs(value.hashCode()) % buckets.length;
        if(buckets[h] == null) {
            return false;
        }
        else {
            for(int i = 0; i < buckets[h].getSize(); i++) {
                try {
                    T x = buckets[h].dequeue();
                    buckets[h].enqueue(x);
                    if(x.equals(value)) {
                        return true;
                    }
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return false;
        }
    }

    @Override
    public boolean remove(T value) {
        if(contains(value)){
            int h = Math.abs(value.hashCode()) % buckets.length;
            for(int i = 0; i < buckets[h].getSize(); i++) {
                try {
                    T x = buckets[h].dequeue();
                    if(!x.equals(value)) {
                        buckets[h].enqueue(x);
                    }
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public T removeAny() throws Exception {
        if(size == 0) {
            throw new Exception("Empty Set!");
        }else {
           size--;
           int i = 0;
           while(buckets[i] == null || buckets[i].getSize() == 0) {
               i++;
           }
           T res = buckets[i].dequeue();
           return res;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        buckets = new LinkedListQueue[buckets.length];
        size = 0;
    }
    
    @Override
    public String toString() {
        String result = "{ ";
        
        for(int i = 0; i < buckets.length; i++)
            if(buckets[i] != null) {
                int len = buckets[i].getSize();
                for(int j = 0; j < len; j++) {
                    try {
                        T x = buckets[i].dequeue();
                        buckets[i].enqueue(x);
                        result += x + " "; 
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                }
                result += "\n  ";
            }
        return result + "}";
    } 
    
}
