package com.github.lucassklp.jinq;

import com.github.lucassklp.jinq.exceptions.NoElementException;

import java.util.List;
import java.util.function.Function;

public class Group<TKey, TElement> {
    private TKey key;
    private QueryableList<TElement> elements;

    public Group(TKey key){
        this.key = key;
        this.elements = new QueryableList<TElement>();
    }

    public TKey getKey() {
        return key;
    }

    public void setKey(TKey key) {
        this.key = key;
    }

    public QueryableList<TElement> getElements() {
        return elements;
    }

    public void add(TElement elem){
        this.elements.add(elem);
    }

    public static <TKey, TElem> List<Group<TKey, TElem>> of(List<TElem> elems, Function<TElem, TKey> fn){
        QueryableList<Group<TKey, TElem>> groups = new QueryableList<>();

        for(TElem item : elems){
            TKey key = fn.apply(item);

            Group group;
            try{
                group = groups.find(x -> x.getKey() == key);
            }catch (NoElementException e){
                group = new Group<TKey, TElem>(key);
                groups.add(group);
            }
            group.add(item);
        }

        return groups;
    }
}
