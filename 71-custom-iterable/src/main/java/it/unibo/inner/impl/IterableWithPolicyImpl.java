package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] arr;

    public IterableWithPolicyImpl(final T[] arr){
        this.arr = arr;
    }
    
    public void setIterationPolicy(final Predicate<T> filter){

    }

    public Iterator<T> iterator(){
        return new IterableWithPolicyImplIterator();
    }

    class IterableWithPolicyImplIterator implements Iterator<T> {

        private int index = 0;

        public boolean hasNext() {
            return index < IterableWithPolicyImpl.this.arr.length;
        }

        public T next() {
            if(!this.hasNext()){
                throw new NoSuchElementException();
            }

            T elem = IterableWithPolicyImpl.this.arr[index];
            index++;
            return elem;
        }
        
    }
}
