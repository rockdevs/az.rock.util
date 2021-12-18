package az.rock.util;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public abstract class RockArray<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
    abstract int fixedLength();

    @Override
    public RockArray<E> clone() {
        try {
            return (RockArray<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
