package JIQ.abstractions;

import java.util.function.Function;
import java.util.function.Predicate;

public interface IList<T> extends Iterable<T> {

    boolean add(T item);
    T get(int index);
    boolean remove(T item);
    boolean remove(Predicate<T> predicate);
    int removeAll(Predicate<T> predicate);

    int clear();

    T find(Predicate<T> predicate);
    Iterable<T> findAll(Predicate<T> predicate);
    int findIndex(Predicate<T> predicate);
    int count();
    <R> Iterable<R> map(Function<T, R> map);




    T first();
    T last();

    <R extends Comparable<R>> T min(Function<T, R> func);
    <R extends Comparable<R>> T max(Function<T, R> func);

    Iterable<T> orderBy(Function<Comparable<?>, T>... funcs);
}
