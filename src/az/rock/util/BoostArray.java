package az.rock.util;

import az.rock.util.exceptions.EmptyBoostArrayException;
import az.rock.util.exceptions.NegativeIndexException;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class BoostArray<T> extends Clim<T> {


    private int lastElementIndex = 0;
    private int initialCapacity = 1000;
    private float incrementCapacity = 1.75F;

    private Object[] boostArray = new Object[initialCapacity];

    private int nullArrSize = 0;
    private final int canNullObjectOnArray = initialCapacity /4;
    private final int[] NULL_INDEXES_ARRAY = new int[initialCapacity /3];


    public BoostArray() {
    }

    public BoostArray(int initialCapacity) {
        this.initialCapacity = initialCapacity;
    }

    public BoostArray(int initialCapacity, float incrementCapacity) {
        this.initialCapacity = initialCapacity;
        this.incrementCapacity = incrementCapacity;
    }


    public BoostArray(T[] boostArray) {
        this.boostArray = boostArray;
    }


    private boolean isEmpty(int index) {
        boolean result = false;
        if (index <= this.initialCapacity) {
            result = this.boostArray[index] == null;
        }

        return result;
    }

    public int firstEmptyNode() {
        int emptyIndex = 0;

        for (Iterator<? extends T> iterator = (Iterator<? extends T>) Arrays.stream(this.boostArray).iterator(); iterator.hasNext(); ++emptyIndex) {
            iterator.next();
        }

        return emptyIndex;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) boostArray[index];
    }

    @Override
    public boolean add(Object o) {
        if(boostArray.length<= lastElementIndex){
            grow();
        }
        this.boostArray[this.lastElementIndex] = o;
        this.lastElementIndex++;
        return true;
    }

    private void grow(){
        initialCapacity *= incrementCapacity;
        final Object[] newArr = new Object[this.initialCapacity];

        for (int i = 0; i < this.lastElementIndex; i++) {
            if (this.boostArray[i] != null) {
                newArr[i] = boostArray[i];
            }
        }
        boostArray = newArr;
    }
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return super.addAll(c);
    }

    @Override
    public void add(int index, Object element) {
        super.add(index, (T) element);
    }

    @Override
    public boolean remove(Object o) {
        if (this.lastElementIndex <= 0) {
            try {
                throw new EmptyBoostArrayException();
            } catch (EmptyBoostArrayException e) {
                e.printStackTrace();
            }
        } else {
            --this.lastElementIndex;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if (index < 0) {
            try {
                throw new NegativeIndexException();
            } catch (NegativeIndexException e) {
                e.printStackTrace();
            }
        } else {
            --this.lastElementIndex;
            this.boostArray[index] = null;
            if (index != this.lastElementIndex) {
                this.NULL_INDEXES_ARRAY[this.nullArrSize] = index;
                ++this.nullArrSize;
            }

        }
        return null;
    }

    @Override
    public int size() {
        return boostArray.length;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) this.boostArray;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return (Iterator<T>) ( Arrays.stream(this.boostArray).parallel()).iterator();
    }

    @Override
    int fixedLength() {
        return lastElementIndex;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        this.boostArray = (T[]) new Object[initialCapacity];
    }


    public void sort() throws EmptyBoostArrayException {
        if (this.lastElementIndex == 0) {
            throw new EmptyBoostArrayException();
        } else {
            Arrays.sort(this.boostArray);
        }
    }

    public boolean flapSearch(Object o) {
        AtomicBoolean result = new AtomicBoolean(false);
        //T[] first = Arrays.copyOfRange(this.boostArray, 0, this.initialCapacity / 2);
        //T[] two = Arrays.copyOfRange(this.boostArray, this.initialCapacity / 2, this.initialCapacity);
//        this.executorService.execute(() -> {
//            for (int i = 0; i < this.initialCapacity / 2; ++i) {
//                if (first[i] == o) {
//                    result.set(true);
//                }
//            }
//
//        });
//        this.executorService.execute(() -> {
//            for (int i = 0; i < this.initialCapacity / 2; ++i) {
//                if (two[i] == o) {
//                    result.set(true);
//                }
//            }
//
//        });
        return true;
    }

}
