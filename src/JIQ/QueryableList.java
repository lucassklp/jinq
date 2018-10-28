package JIQ;

import JIQ.exceptions.NoElementException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public class QueryableList<T> implements Iterable<T>{

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

    public T get(int index) {
        return this.list.get(index);
    }

    public boolean remove(T item) {
        return this.list.remove(item);
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

    public int clear() {
        int count = this.count();
        this.list.clear();
        return count;
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

    public T reduce(BiFunction<T, T, T> red) throws NoElementException {
        return this.first();
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

    public boolean isEmpty(){
        return this.list.size() == 0;
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

    public void forEach(BiConsumer<T, Integer> it){
        for(int i = 0; i < this.count(); i++){
            it.accept(this.list.get(i), i);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }
}
