package az.rock.util;

import java.util.*;

public class BoostArray<E> extends Clim<E> {

    @java.io.Serial
    private static final long serialVersionUID = 8683452501122892189L;

    /**
     * Default initial capacity.
     */
    private  int DEFAULT_CAPACITY = 1000;

    private final float INCREMENT_CAPACITY_RANGE = 1.4F;

    private final int NULL_SAFETY_CAPACITY = DEFAULT_CAPACITY / 2;

    private int lastElementIndex =0;

    private  E[] boostArray;

    private final int[] nullSafety = new int[NULL_SAFETY_CAPACITY];

    private int nullSafetyLastIndex=0;

    public BoostArray() {
        boostArray = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public E get(int index) {
        return boostArray[index];
    }

    @Override
    public int size() {
        return boostArray.length;
    }

    @Override
    public int fixedLength() {
        return 0;
    }

    @Override
    public BoostArray<E> clone() {
        return (BoostArray<E>) super.clone();
    }

    @Override
    public boolean add(E e) {
        try {
            if(lastElementIndex==DEFAULT_CAPACITY){
                extend();
            }
            boostArray[lastElementIndex] = e;
            lastElementIndex++;
            return true;
        }catch (Exception exception){
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public E remove(int index) {
        E e;
        try {
            nullSafety[nullSafetyLastIndex] = index;
            nullSafetyLastIndex++;
            e = boostArray[index];
            boostArray[index] = null;
            return e;
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "BoostArray{" +
                "boostArray=" + Arrays.toString(boostArray) +
                '}';
    }

    private void extend(){
        DEFAULT_CAPACITY = (int) (DEFAULT_CAPACITY*INCREMENT_CAPACITY_RANGE);
        E[] newArr = (E[]) new Object[DEFAULT_CAPACITY];
        int i = 0;
        while (i<DEFAULT_CAPACITY){
            if(boostArray[i]!=null){
                newArr[i] = boostArray[i];
            }
            i++;
        }
        boostArray = newArr;
    }

}
