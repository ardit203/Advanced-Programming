package Exercises._05_Intro_to_Maps.Media;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Ledger<T extends Quantifiable & Comparable<? super T>> implements Comparable<Ledger<T>>{
    private final List<T> list;

    public Ledger(){
        this.list = new ArrayList<>();
    }

    public void put(T item){
        list.add(item);
    }

    public <R> Set<R> project(Function<? super T, ? extends R> mapper){
        return list.stream().map(mapper).collect(Collectors.toCollection(HashSet::new));
    }

    public void forEachIf(Predicate<? super T> condition, Consumer<? super T> action){
        list.stream().filter(condition).forEach(action);
    }

    public double sum(){
        return list.stream().mapToDouble(Quantifiable::amount).sum();
    }


    @Override
    public int compareTo(Ledger<T> other) {
        return Double.compare(other.sum(), this.sum());
    }
}
