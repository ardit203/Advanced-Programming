package Lab.Lab2.Task4;

import java.util.function.Function;
import java.util.function.Predicate;


//Additional requirement
public class ArrayTransformer {

    public static <T, R> ResizableArray<R> map(ResizableArray<T> source, Function<T, R> mapper) {
        ResizableArray<R> newArray = new ResizableArray<>();
        for (int i = 0; i < source.count(); i++) {
            newArray.addElement(mapper.apply(source.elementAt(i)));
        }
        return newArray;
    }

    public static <T> ResizableArray<T> filter(ResizableArray<T> source, Predicate<T> predicate){
        ResizableArray<T> newArray = new ResizableArray<>();

        for (int i = 0; i < source.count(); i++) {
            if(predicate.test(source.elementAt(i))){
                newArray.addElement(source.elementAt(i));
            }
        }
        return newArray;
    }
}
