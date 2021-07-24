package net.pibrary.rx;

public interface Observer<T> {
    void onNext(T value);
}
