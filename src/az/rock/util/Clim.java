package az.rock.util;

import java.util.AbstractList;
import java.util.List;

public abstract class Clim<E> extends AbstractList<E> implements List<E>{
    abstract int fixedLength();

}
