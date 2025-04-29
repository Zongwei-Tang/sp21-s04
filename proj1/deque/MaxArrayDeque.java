package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> a;

    public MaxArrayDeque(Comparator<T> c){
        super();
        a = c;
    }

    public T max(){
        if (isEmpty()){
            return null;
        }
        T maxi = get(0);
        for (int i = 0; i < size(); i++){
            if (a.compare(maxi, get(i)) < 0){
                maxi = get(i);
            }
        }
        return maxi;
    }

    public T max(Comparator<T> c){
        a = c;
        return max();
    }
}
