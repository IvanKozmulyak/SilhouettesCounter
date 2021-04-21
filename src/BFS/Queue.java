package BFS;

public interface Queue <T>{
    void add(T item);
    T remove();
    boolean isEmpty();
    int size();
    void clear();
}
