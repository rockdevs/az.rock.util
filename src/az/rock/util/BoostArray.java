package az.rock.util;

import java.util.*;

public class BoostArray<E> extends RockArray<E> {

    @java.io.Serial
    private static final long serialVersionUID = 8683452501122892189L;

    /**
     * Default initial capacity.
     */
    private final int DEFAULT_CAPACITY = 1000;

    private final float INCREMENT_CAPACITY_RANGE = 1.1F;

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
        try {
            BoostArray<E> clone = (BoostArray<E>) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public boolean add(E e) {
        try {
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


}
