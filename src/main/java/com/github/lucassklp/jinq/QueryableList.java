package com.github.lucassklp.jinq;

import com.github.lucassklp.jinq.exceptions.NoElementException;

import java.util.*;
import java.util.function.*;

public class QueryableList<T> implements List<T> {

    private List<T> list;

    public QueryableList(){
        this.list = new ArrayList<>();
    }

    public QueryableList(List<T> list){
        this.list = list;
    }

    public static <T> QueryableList of(List<T> list){
        return new QueryableList(list);
    }

    public boolean add(T item) {
        return this.list.add(item);
    }

    @Override
    public boolean remove(Object o) {
        return this.list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return this.list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return this.list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.list.retainAll(c);
    }

    public T get(int index) {
        return this.list.get(index);
    }

    @Override
    public T set(int index, T element) {
        return this.list.set(index, element);
    }

    @Override
    public void add(int index, T element) {
        this.list.add(index, element);
    }

    @Override
    public T remove(int index) {
        return this.list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return this.list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return this.list.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return this.list.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return this.list.subList(fromIndex, toIndex);
    }


    public T reduce(BiFunction<T, T, T> func){
        Iterator<T> iterator = this.iterator();
        T source = iterator.next(); //enumerator.Current;
        T current = null;
        while ((current = iterator.next()) != null) {
            source = func.apply(source, current);
        }

        return source;
    }

    /**
     * Removes the first element that matches condition
     * @param predicate
     */
    public boolean remove(Predicate<T> predicate) {
        for(int i = 0; i < this.count(); i++){
            T item = this.list.get(i);
            if(predicate.test(item)){
                this.list.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove all elements that matches condition
     * @param predicate
     * @return quantity of removed itens
     */
    public int removeAll(Predicate<T> predicate) {
        int removed = 0;
        for(int i = 0; i < this.count(); i++){
            T item = this.list.get(i);
            if(predicate.test(item)){
                this.list.remove(i--);
                removed++;
            }
        }
        return removed;
    }

    public boolean exists(Predicate<T> predicate){
        for(T item : this.list){
            if(predicate.test(item)){
                return true;
            }
        }
        return false;
    }

    public void clear() {
        this.list.clear();
    }

    public T find(Predicate<T> predicate) throws NoElementException {
        for(T item : this.list){
            if(predicate.test(item)){
                return item;
            }
        }
        throw new NoElementException();
    }

    public QueryableList<T> findAll(Predicate<T> predicate) {
        QueryableList<T> result = new QueryableList<>();
        for(T item : this.list){
            if(predicate.test(item)){
                result.add(item);
            }
        }
        return result;
    }

    public int findIndex(Predicate<T> predicate) throws NoElementException {
        for (int i = 0; i < count(); i++) {
            if(predicate.test(get(i))){
                return i;
            }
        }
        throw new NoElementException();
    }

    public int count() {
        return this.list.size();
    }

    public <R> QueryableList<R> map(Function<T, R> map) {
        QueryableList<R> result = new QueryableList<>();
        for(T item : this.list){
            result.add(map.apply(item));
        }
        return result;
    }

    public T first() throws NoElementException {
        if(!isEmpty()) {
            return this.list.get(0);
        }
        else{
            throw new NoElementException();
        }
    }

    public T last() throws NoElementException {
        if(!isEmpty()) {
            return this.list.get(this.list.size() - 1);
        }
        else{
            throw new NoElementException();
        }
    }

    @Override
    public int size() {
        return this.count();
    }

    public boolean isEmpty(){
        return this.list.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    public <R extends Comparable<R>> T min(Function<T, R> func) throws NoElementException {
        if(!isEmpty()){
            T minor = this.first();

            for (int i = 1; i < this.count(); i++) {
                Comparable<R> cmp = func.apply(this.get(i));
                R comparable = func.apply(minor);

                if(cmp.compareTo(comparable) < 0){
                    minor = this.list.get(i);
                }
            }

            return minor;
        }
        else{
            throw new NoElementException();
        }
    }

    public <R extends Comparable<R>> T max(Function<T, R> func) throws NoElementException {
        if(!isEmpty()){
            T major = this.first();

            for (int i = 1; i < this.count(); i++) {
                Comparable<R> cmp = func.apply(this.get(i));
                R comparable = func.apply(major);

                if(cmp.compareTo(comparable) > 0){
                    major = this.list.get(i);
                }
            }

            return major;
        }
        else{
            throw new NoElementException();
        }
    }

    public <X extends Comparable<?>> QueryableList<T> orderBy(Function<T, X>... funcs){
        
        return this;
    }

    public <X extends Comparable<?>> QueryableList<T> orderByDescreasing(Function<T, X>... funcs){

        return this;
    }

    public <TKey> List<Group<TKey, T>> groupBy(Function<T, TKey> func){
        return Group.of(this, func);
    }


    public void forEach(BiConsumer<T, Integer> it){
        for(int i = 0; i < this.count(); i++){
            it.accept(this.list.get(i), i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return this.list.toArray(a);
    }
}
