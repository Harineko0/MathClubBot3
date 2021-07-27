package net.pibrary.rx;

public interface Observable<T> {
    void subscribe(Observer<T> observer);
}
