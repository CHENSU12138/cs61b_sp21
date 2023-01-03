package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmpT;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmpT = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        int maxItem = 0;
        for (int i = 0; i < size(); i++) {
            if (cmpT.compare(get(i), get(maxItem)) > 0) {
                maxItem = i;
            }
        }
        return get(maxItem);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (T i : this) {
            if (cmpT.compare(i, maxItem) > 0) {
                maxItem = i;
            }
        }
        return maxItem;
    }
}
