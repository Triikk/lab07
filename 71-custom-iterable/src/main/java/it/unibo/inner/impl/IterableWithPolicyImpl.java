package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] elements){
        this(
            elements,
            new Predicate<T>(){
                public boolean test(final T elem) {
                    return true;    
                }            
            }
        );
    }

    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter){
        this.elements = List.of(elements);
        this.setIterationPolicy(filter);
    }
    
    public void setIterationPolicy(final Predicate<T> filter){
        this.filter = filter;
    }

    public Iterator<T> iterator(){
        return new IterableWithPolicyImplIterator();
    }

    class IterableWithPolicyImplIterator implements Iterator<T> {

        private int index = 0;

        public boolean hasNext() {
            while(isIndexValid() && !isValidElement()){
                index++;
            }
            return isIndexValid();
        }

        public T next() {
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }
            return elements.get(index++);
        }

        private boolean isIndexValid(){
            return index >= 0 && index < elements.size();
        }

        private boolean isValidElement(){
            return filter.test(elements.get(index));
        }

    }
}
