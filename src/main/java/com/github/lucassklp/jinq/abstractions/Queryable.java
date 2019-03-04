package com.github.lucassklp.jinq.abstractions;

import com.github.lucassklp.jinq.QueryableList;
import com.github.lucassklp.jinq.exceptions.NoElementException;

import java.util.function.Function;
import java.util.function.Predicate;

public interface Queryable<T> extends Iterable<T> {

    boolean add(T item);
    T get(int index) throws NoElementException;
    boolean remove(T item);
    boolean remove(Predicate<T> predicate);
    int removeAll(Predicate<T> predicate);

    void clear();

    T find(Predicate<T> predicate) throws NoElementException;
    QueryableList<T> findAll(Predicate<T> predicate);
    int findIndex(Predicate<T> predicate) throws NoElementException;
    int count();
    <R> QueryableList<R> map(Function<T, R> map);

    T first() throws NoElementException;
    T last() throws NoElementException;

    <R extends Comparable<R>> T min(Function<T, R> func) throws NoElementException;
    <R extends Comparable<R>> T max(Function<T, R> func) throws NoElementException;
}
