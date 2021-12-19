package az.rock.util;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public abstract class Clim<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
    abstract int fixedLength();

    @Override
    public Clim<E> clone() {
        try {
            return (Clim<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


}
