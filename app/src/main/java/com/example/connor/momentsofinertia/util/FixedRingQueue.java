/**
 * Copyright(C) 2015 Connor Marble
 *
 * This file is part of the android game Moments of Inertia
 *
 * Moments of Inertia is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Moments of Inertia is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Moments of Inertia.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.connor.momentsofinertia.util;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by connor on 3/9/15.
 */
public class FixedRingQueue<E> {

    private int length;
    private int arrayStart;
    private int count;
    private int numberAdded;
    private E[] data;

    public FixedRingQueue(int size){
        this.length = size;
        data = (E[])new Object[size];
        count=0;
        numberAdded = 0;
        arrayStart = size-1;
    }

    public boolean add(Object object) {
        if(object == null){
            return false;
        }

        arrayStart = getDataIndex(numberAdded);
        if(data[arrayStart] == null)
            count++;

        numberAdded ++;
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
        int result = (absoluteIndex)%length;
        if(result < 0){
            result += length;
        }
        return result;
    }

    public E get(int index){
        return data[getDataIndex(numberAdded - index -1)];
    }
}
