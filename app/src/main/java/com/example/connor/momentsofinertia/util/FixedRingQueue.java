package com.example.connor.momentsofinertia.util;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by connor on 3/9/15.
 */
public class FixedRingQueue<E> {

    private int length;
    private int arrayStart;
    private int count;
    private E[] data;

    public FixedRingQueue(int size){
        this.length = size;
        data = (E[])new Object[size];
        count=0;
        arrayStart = size-1;
    }

    public boolean add(Object object) {
        if(object == null){
            return false;
        }

        arrayStart = getDataIndex(count);

        count++;
        count %= length;

        data[arrayStart] = (E)object;

        return true;
    }


    public void clear() {
        data = (E[])new Object[length];
        arrayStart = length-1;
    }

    public boolean contains(Object object) {
        for(int i =0;i<length;i++){
            if(data[i].equals(object)){
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        for(int i =0;i<length;i++){
            if(data[i] != null){
                return false;
            }
        }
        return true;
    }

    public int size() {
        return count;
    }

    private int getDataIndex(int absoluteIndex){
        return (length - absoluteIndex)%length;

    }

    public E get(int index){
        return data[getDataIndex(index)];
    }
}
