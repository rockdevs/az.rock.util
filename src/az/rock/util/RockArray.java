package az.rock.util;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

public abstract class RockArray<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable{
    abstract int fixedLength();
}
