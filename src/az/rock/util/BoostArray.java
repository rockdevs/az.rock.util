package az.rock.util;



import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class BoostArray<T> extends Clim<T> {


    private int boostArrSize = 0;
    private int DEFAULT_CAPACITY = 1000;
    private float INCREMENT_CAPACITY = 1.75F;

    private Object[] boostArray = new Object[DEFAULT_CAPACITY];

    private int nullArrSize = 0;
    private final int canNullObjectOnArray = DEFAULT_CAPACITY/4;
    private final int[] NULL_INDEXES_ARRAY = new int[DEFAULT_CAPACITY /3];

    public BoostArray() {
    }

    public BoostArray(int defaultCapacity) {
        this.DEFAULT_CAPACITY = defaultCapacity;
    }

    public BoostArray(int defaultCapacity,float incrementCapacity) {
        this.DEFAULT_CAPACITY = defaultCapacity;
        this.INCREMENT_CAPACITY = incrementCapacity;
    }


    public BoostArray(T[] boostArray) {
        this.boostArray = boostArray;
    }


    private boolean isEmpty(int index) {
        boolean result = false;
        if (index <= this.DEFAULT_CAPACITY) {
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
    public T get(int index) {
        return (T) this.boostArray[index];
    }

    @Override
    public boolean add(T o) {
        if(boostArray.length<=boostArrSize){
            reorder();
        }
        this.boostArray[this.boostArrSize] = o;
        this.boostArrSize++;
        return true;
    }

    private void reorder(){
        DEFAULT_CAPACITY *=INCREMENT_CAPACITY;
        Object[] newArr = new Object[this.DEFAULT_CAPACITY];

        for (int i = 0; i < this.boostArrSize; i++) {
            if (this.boostArray[i] != null) {
                newArr[i] = this.boostArray[i];
            }
        }
        this.boostArray = newArr;
    }
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return super.addAll(c);
    }

    @Override
    public void add(int index, T element) {
        super.add(index, element);
    }

    @Override
    public boolean remove(Object o) {
        if (this.boostArrSize <= 0) {
            try {
                throw new EmptyBoostArrayException();
            } catch (EmptyBoostArrayException e) {
                e.printStackTrace();
            }
        } else {
            --this.boostArrSize;
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
            --this.boostArrSize;
            this.boostArray[index] = null;
            if (index != this.boostArrSize) {
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
    public T[] toArray() {
        return (T[]) this.boostArray;
    }

    @Override
    public Iterator<T> iterator() {
        return ((Stream) Arrays.stream(this.boostArray).parallel()).iterator();
    }

    @Override
    int fixedLength() {
        return boostArrSize;
    }

    @Override
    public void clear() {
        this.boostArray = (T[]) new Object[DEFAULT_CAPACITY];
    }


    public void sort() throws EmptyBoostArrayException {
        if (this.boostArrSize == 0) {
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
