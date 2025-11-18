# Lab Exercise 2 - Advanced Programming

## Task 4

Write a class `ResizableArray` that represents an array with a variable length.
The class should be able to store elements of any type (it must be generic with one type parameter `T`) and should define the following methods:
- `ResizableArray()` – creates a new empty array.

- `addElement(T element)` – adds a new element to the array.
(If there’s not enough space, the capacity of the array should increase.)

- `removeElement(T element): boolean` –
if such an element exists, remove one instance of it and return `true`; otherwise return `false`.
(If there’s too much unused space, reduce the array’s capacity.)

- `contains(T element): boolean` – returns `true` if the element exists in the array.

- `toArray(): Object[]` – returns all elements as a regular array.

- `isEmpty()` – returns true if the array has no elements.

- `count(): int` – returns the number of elements currently in the array.

- `elementAt(int idx): T` – returns the element at the given position;
if such an element does not exist, throw an `ArrayIndexOutOfBoundsException`.
(The elements in the array are positioned at indices [0, count()].)

**Note:** To store the elements, you must use a regular array of type `T[]` elements.
**You are not allowed to use** `ArrayList<T>`, and the array must be declared as `private`.

Additionally, the class `ResizableArray` should include one static method:
- `static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src)`

This method must copy all elements from `src` (the source array) into `dest` (the destination array).
The source (`src`) remains unchanged, while the destination (`dest`) keeps its existing elements and adds all elements from the source.

Next, you need to write a class `IntegerArray` that inherits from the class `ResizableArray` `IntegerArray extends ResizableArray<Integer>`. This class is used for storing integer values and should provide the following methods:

- `sum(): double` – returns the sum of all elements in the array.
- `mean(): double` – returns the average of all elements in the array.
- `countNonZero(): int` – returns the number of elements in the array that have a value different from zero.
- `distinct(): IntegerArray` – returns a new object that contains the same elements as this, but without duplicate elements.
- `increment(int offset): IntegerArray` – returns a new object that contains all the elements of this, but with each element increased by the given offset.

Starter Code:

```java
import java.util.Scanner;
import java.util.LinkedList;

public class ResizableArrayTest {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int test = jin.nextInt();
		if ( test == 0 ) { //test ResizableArray on ints
			ResizableArray<Integer> a = new ResizableArray<Integer>();
			System.out.println(a.count());
			int first = jin.nextInt();
			a.addElement(first);
			System.out.println(a.count());
			int last = first;
			while ( jin.hasNextInt() ) {
				last = jin.nextInt();
				a.addElement(last);
			}
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(a.removeElement(first));
			System.out.println(a.contains(first));
			System.out.println(a.count());
		}
		if ( test == 1 ) { //test ResizableArray on strings
			ResizableArray<String> a = new ResizableArray<String>();
			System.out.println(a.count());
			String first = jin.next();
			a.addElement(first);
			System.out.println(a.count());
			String last = first;
			for ( int i = 0 ; i < 4 ; ++i ) {
				last = jin.next();
				a.addElement(last);
			}
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(a.removeElement(first));
			System.out.println(a.contains(first));
			System.out.println(a.count());
			ResizableArray<String> b = new ResizableArray<String>();
			ResizableArray.copyAll(b, a);
			System.out.println(b.count());
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(b.contains(first));
			System.out.println(b.contains(last));
			ResizableArray.copyAll(b, a);
			System.out.println(b.count());
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(b.contains(first));
			System.out.println(b.contains(last));
			System.out.println(b.removeElement(first));
			System.out.println(b.contains(first));
			System.out.println(b.removeElement(first));
			System.out.println(b.contains(first));

			System.out.println(a.removeElement(first));
			ResizableArray.copyAll(b, a);
			System.out.println(b.count());
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(b.contains(first));
			System.out.println(b.contains(last));
		}
		if ( test == 2 ) { //test IntegerArray
			IntegerArray a = new IntegerArray();
			System.out.println(a.isEmpty());
			while ( jin.hasNextInt() ) {
				a.addElement(jin.nextInt());
			}
			jin.next();
			System.out.println(a.sum());
			System.out.println(a.mean());
			System.out.println(a.countNonZero());
			System.out.println(a.count());
			IntegerArray b = a.distinct();
			System.out.println(b.sum());
			IntegerArray c = a.increment(5);
			System.out.println(c.sum());
			if ( a.sum() > 100 )
				ResizableArray.copyAll(a, a);
			else
				ResizableArray.copyAll(a, b);
			System.out.println(a.sum());
			System.out.println(a.removeElement(jin.nextInt()));
			System.out.println(a.sum());
			System.out.println(a.removeElement(jin.nextInt()));
			System.out.println(a.sum());
			System.out.println(a.removeElement(jin.nextInt()));
			System.out.println(a.sum());
			System.out.println(a.contains(jin.nextInt()));
			System.out.println(a.contains(jin.nextInt()));
		}
		if ( test == 3 ) { //test insanely large arrays
			LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
			for ( int w = 0 ; w < 500 ; ++w ) {
				ResizableArray<Integer> a = new ResizableArray<Integer>();
				int k =  2000;
				int t =  1000;
				for ( int i = 0 ; i < k ; ++i ) {
					a.addElement(i);
				}
				
				a.removeElement(0);
				for ( int i = 0 ; i < t ; ++i ) {
					a.removeElement(k-i-1);
				}
				resizable_arrays.add(a);
			}
			System.out.println("You implementation finished in less then 3 seconds, well done!");
		}
	}
	
}
```

Solution:
```java
import java.util.Scanner;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;

class IntegerArray extends ResizableArray<Integer> {
    public double sum() {
        double sum = 0.0;
        for (int i = 0; i < count(); i++) {
            sum += elementAt(i);
        }
        return sum;
    }

    public double mean() {
        return sum() / count();
    }

    public int countNonZero() {
        int count = 0;
        for (int i = 0; i < count(); i++) {
            if (elementAt(i) != 0) {
                count++;
            }
        }
        return count;
    }


//    public IntegerArray distinct() {
//        IntegerArray distinct = new IntegerArray();
//        for (int i = 0; i < count(); i++) {
//            int element = elementAt(i);
//            boolean exists = false;
//            for (int j = 0; j < distinct.count(); j++) {
//                if (distinct.elementAt(j) == element) {
//                    exists = true;
//                    break;
//                }
//            }
//            if (exists) {
//                continue;
//            }
//            distinct.addElement(element);
//        }
//        return distinct;
//    }

    public IntegerArray distinct() {
        IntegerArray distinct = new IntegerArray();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < count(); i++) {
            int element = elementAt(i);
            if (set.add(element)){
                distinct.addElement(element);
            }
        }
        return distinct;
    }

    public IntegerArray increment(int offset) {
        IntegerArray array = new IntegerArray();

        for (int i = 0; i < count(); i++) {
            array.addElement(elementAt(i) + offset);
        }
        return array;
    }
}


class ResizableArray<T> {
    private T[] elements;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ResizableArray() {
        this.size = 0;
        this.capacity = 10;
        this.elements = (T[]) new Object[this.capacity];
    }

    private void resize(int n) {
        capacity += n;
        elements = Arrays.copyOf(elements, capacity);
    }

    private int find(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void addElement(T element) {
        if (size == capacity) {
            resize(20);
        }
        elements[size++] = element;
    }

    public boolean removeElement(T element) {
        int index = find(element);
        if (index == -1) {
            return false;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);

        elements[--size] = null;
        if (size + 40 < capacity) {
            resize(-20);
        }
        return true;
    }

    public boolean contains(T element) {
        return find(element) != -1;
    }

    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int count() {
        return size;
    }

    public T elementAt(int idx) {
        if (idx < 0 || idx > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elements[idx];
    }

    public static <T> void copyAll(ResizableArray<? super T> dest, ResizableArray<? extends T> src) {
        int n = src.count();

        for (int i = 0; i < n; i++) {
            dest.addElement(src.elementAt(i));
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]).append(" ");
        }
        sb.append("\n");
        return sb.toString();
    }
}

public class ResizableArrayTest {
	
	public static void main(String[] args) {
		Scanner jin = new Scanner(System.in);
		int test = jin.nextInt();
		if ( test == 0 ) { //test ResizableArray on ints
			ResizableArray<Integer> a = new ResizableArray<Integer>();
			System.out.println(a.count());
			int first = jin.nextInt();
			a.addElement(first);
			System.out.println(a.count());
			int last = first;
			while ( jin.hasNextInt() ) {
				last = jin.nextInt();
				a.addElement(last);
			}
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(a.removeElement(first));
			System.out.println(a.contains(first));
			System.out.println(a.count());
		}
		if ( test == 1 ) { //test ResizableArray on strings
			ResizableArray<String> a = new ResizableArray<String>();
			System.out.println(a.count());
			String first = jin.next();
			a.addElement(first);
			System.out.println(a.count());
			String last = first;
			for ( int i = 0 ; i < 4 ; ++i ) {
				last = jin.next();
				a.addElement(last);
			}
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(a.removeElement(first));
			System.out.println(a.contains(first));
			System.out.println(a.count());
			ResizableArray<String> b = new ResizableArray<String>();
			ResizableArray.copyAll(b, a);
			System.out.println(b.count());
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(b.contains(first));
			System.out.println(b.contains(last));
			ResizableArray.copyAll(b, a);
			System.out.println(b.count());
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(b.contains(first));
			System.out.println(b.contains(last));
			System.out.println(b.removeElement(first));
			System.out.println(b.contains(first));
			System.out.println(b.removeElement(first));
			System.out.println(b.contains(first));

			System.out.println(a.removeElement(first));
			ResizableArray.copyAll(b, a);
			System.out.println(b.count());
			System.out.println(a.count());
			System.out.println(a.contains(first));
			System.out.println(a.contains(last));
			System.out.println(b.contains(first));
			System.out.println(b.contains(last));
		}
		if ( test == 2 ) { //test IntegerArray
			IntegerArray a = new IntegerArray();
			System.out.println(a.isEmpty());
			while ( jin.hasNextInt() ) {
				a.addElement(jin.nextInt());
			}
			jin.next();
			System.out.println(a.sum());
			System.out.println(a.mean());
			System.out.println(a.countNonZero());
			System.out.println(a.count());
			IntegerArray b = a.distinct();
			System.out.println(b.sum());
			IntegerArray c = a.increment(5);
			System.out.println(c.sum());
			if ( a.sum() > 100 )
				ResizableArray.copyAll(a, a);
			else
				ResizableArray.copyAll(a, b);
			System.out.println(a.sum());
			System.out.println(a.removeElement(jin.nextInt()));
			System.out.println(a.sum());
			System.out.println(a.removeElement(jin.nextInt()));
			System.out.println(a.sum());
			System.out.println(a.removeElement(jin.nextInt()));
			System.out.println(a.sum());
			System.out.println(a.contains(jin.nextInt()));
			System.out.println(a.contains(jin.nextInt()));
		}
		if ( test == 3 ) { //test insanely large arrays
			LinkedList<ResizableArray<Integer>> resizable_arrays = new LinkedList<ResizableArray<Integer>>();
			for ( int w = 0 ; w < 500 ; ++w ) {
				ResizableArray<Integer> a = new ResizableArray<Integer>();
				int k =  2000;
				int t =  1000;
				for ( int i = 0 ; i < k ; ++i ) {
					a.addElement(i);
				}
				
				a.removeElement(0);
				for ( int i = 0 ; i < t ; ++i ) {
					a.removeElement(k-i-1);
				}
				resizable_arrays.add(a);
			}
			System.out.println("You implementation finished in less then 3 seconds, well done!");
		}
	}
}

```