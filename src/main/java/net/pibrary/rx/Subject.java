package net.pibrary.rx;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class Subject<T> implements ISubject<T> {
    private Set<Observer<T>> observers = new HashSet<>();

    @Override
    public void subscribe(Observer<T> observer) {
        observers.add(observer);
    }

    // ラムダ式でSubscribe
    public void subscribe(Consumer<T> function) {
        Observer<T> observer = function::accept;
        subscribe(observer);
    }

    @Override
    public void onNext(T value) {
        for (Observer<T> observer : observers) {
            observer.onNext(value);
        }
    }
}
