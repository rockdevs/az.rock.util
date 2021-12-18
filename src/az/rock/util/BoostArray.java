package az.rock.util;

import java.util.*;

public class BoostArray<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 8683452501122892189L;

    /**
     * Default initial capacity.
     */
    private final int DEFAULT_CAPACITY = 10;

    private final float INCREMENT_CAPACITY_RANGE = 1.1F;

    private final int NULL_SAFETY_CAPACITY = DEFAULT_CAPACITY / 2;

    private E[] boostArray;

    private final int[] nullSafety = new int[NULL_SAFETY_CAPACITY];

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

        return super.add(e);
    }
}
