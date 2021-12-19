package az.rock.util;



import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class BoostArray<T> extends Clim<T> {

    private T[] arr;
    private int arrSize = 0;
    private int nullArrSize = 0;
    private int canNullObjectOnArray = 10;
    private int initialCapacity = 1;
    private int[] emptyIndexes;
    private final int defaultCapacity;
    private float incrementCapacity;
    private final ExecutorService executorService;

    public int getArrSize() {
        return this.arrSize;
    }

    public BoostArray() {
        this.emptyIndexes = new int[this.canNullObjectOnArray];
        this.defaultCapacity = 20;
        this.incrementCapacity = 1.5F;
        this.executorService = Executors.newFixedThreadPool(4);
        this.initialCapacity = 20;
        this.arr = (T[]) new Object[20];
    }

    public BoostArray(int initialCapacity) {
        this.emptyIndexes = new int[this.canNullObjectOnArray];
        this.defaultCapacity = 20;
        this.incrementCapacity = 1.5F;
        this.executorService = Executors.newFixedThreadPool(4);
        this.initialCapacity = initialCapacity;
        this.arr = (T[]) new Object[this.initialCapacity];
    }

    public BoostArray(T[] arr) {
        this.emptyIndexes = new int[this.canNullObjectOnArray];
        this.defaultCapacity = 20;
        this.incrementCapacity = 1.5F;
        this.executorService = Executors.newFixedThreadPool(4);
        this.arr = arr;
    }

    private T[] init() {
        return null;
    }

    private boolean isEmpty(int index) {
        boolean result = false;
        if (index <= this.initialCapacity) {
            result = this.arr[index] == null;
        }

        return result;
    }

    public int firstEmptyNode() {
        int emptyIndex = 0;

        for (Iterator iterator = Arrays.stream(this.arr).iterator(); iterator.hasNext(); ++emptyIndex) {
            iterator.next();
        }

        return emptyIndex;
    }

    @Override
    public T get(int index) {
        return this.arr[index];
    }

    public boolean add(Object o) {
        try {
            this.arr[this.arrSize] = (T) o;
        } catch (ArrayIndexOutOfBoundsException var3) {
            this.reorderArray();
            this.arr[this.arrSize] = (T) o;
        }

        ++this.arrSize;
        return false;
    }

    public boolean remove(Object o) {
        if (this.arrSize <= 0) {
            try {
                throw new EmptyBoostArrayException();
            } catch (EmptyBoostArrayException e) {
                e.printStackTrace();
            }
        } else {
            --this.arrSize;
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
            --this.arrSize;
            this.arr[index] = null;
            if (index != this.arrSize) {
                this.emptyIndexes[this.nullArrSize] = index;
                ++this.nullArrSize;
            }

        }
        return null;
    }

    private void reorderArray() {
        this.initialCapacity = (int) ((float) this.initialCapacity * this.incrementCapacity);
        T[] newArr = (T[]) new Object[this.initialCapacity];

        for (int i = 0; i < this.arrSize; ++i) {
            if (this.arr[i] != null) {
                newArr[i] = this.arr[i];
            }
        }

        this.arr = newArr;
    }

    public void sort() throws EmptyBoostArrayException {
        if (this.arrSize == 0) {
            throw new EmptyBoostArrayException();
        } else {
            Arrays.sort(this.arr);
        }
    }

    public int length() {
        return this.arrSize;
    }

    public void clear() {
        this.arr = (T[]) new Object[20];
    }

    public void trim() {
    }

    public Iterator<T> iterator() {
        return ((Stream) Arrays.stream(this.arr).parallel()).iterator();
    }

    @Override
    public int size() {
        return 0;
    }

    public T[] toArray() {
        return this.arr;
    }

    public boolean flapSearch(Object o) {
        AtomicBoolean result = new AtomicBoolean(false);
        T[] first = Arrays.copyOfRange(this.arr, 0, this.initialCapacity / 2);
        T[] two = Arrays.copyOfRange(this.arr, this.initialCapacity / 2, this.initialCapacity);
        this.executorService.execute(() -> {
            for (int i = 0; i < this.initialCapacity / 2; ++i) {
                if (first[i] == o) {
                    result.set(true);
                }
            }

        });
        this.executorService.execute(() -> {
            for (int i = 0; i < this.initialCapacity / 2; ++i) {
                if (two[i] == o) {
                    result.set(true);
                }
            }

        });
        return true;
    }

    @Override
    int fixedLength() {
        return 0;
    }
}
