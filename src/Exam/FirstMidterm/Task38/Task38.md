Implement a generic class for a block container `BlockContainer`.
The container should have a block structure, where each block contains N elements.
The container should satisfy the following conditions:

* constant-time access to each block *O(1)*
* logarithmic-time access to the elements in the block *O(log N)*
* the elements in each block must be sorted.

The class should implement the following methods:

* `public BlockContainer(int n)` – constructor with one argument, the maximum number of elements in a block
* `public void add(T a)` – method for adding an element to the last block of the container (if the block is full, a new block is added)
* `public boolean remove(T a)` – method for deleting an element from the last block (if all elements from a block are deleted, then the block itself is deleted)
* `public void sort()` – method for sorting all elements in the container
* `public String toString()` – override of the method to return a `String` in the following format:
  example: `[7, 8, 9],[1, 2, 3],[5, 6, 12],[4, 10, 8]`

### Starter code
```java
import java.util.*;

public class BlockContainerTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int size = scanner.nextInt();
		BlockContainer<Integer> integerBC = new BlockContainer<Integer>(size);
		scanner.nextLine();
		Integer lastInteger = null;
		for(int i = 0; i < n; ++i) {
			int element = scanner.nextInt();
			lastInteger = element;
			integerBC.add(element);
		}
		System.out.println("+++++ Integer Block Container +++++");
		System.out.println(integerBC);
		System.out.println("+++++ Removing element +++++");
		integerBC.remove(lastInteger);
		System.out.println("+++++ Sorting container +++++");
		integerBC.sort();
		System.out.println(integerBC);
		BlockContainer<String> stringBC = new BlockContainer<String>(size);
		String lastString = null;
		for(int i = 0; i < n; ++i) {
			String element = scanner.next();
			lastString = element;
			stringBC.add(element);
		}
		System.out.println("+++++ String Block Container +++++");
		System.out.println(stringBC);
		System.out.println("+++++ Removing element +++++");
		stringBC.remove(lastString);
		System.out.println("+++++ Sorting container +++++");
		stringBC.sort();
		System.out.println(stringBC);
	}
}
```

### Solution
```java
// package Exam.FirstMidterm.Task38;

import java.util.*;
import java.util.stream.Collectors;

class Block<T extends Comparable<T>> {
    private Set<T> elements;

    public Block() {
        this.elements = new TreeSet<>();
    }

    public Set<T> getElements() {
        return elements;
    }

    public void addElement(T element) {
        elements.add(element);
    }

    public void remove(T element) {
        elements.remove(element);
    }

    public int size() {
        return elements.size();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}

class BlockContainer<T extends Comparable<T>> {
    private List<Block<T>> blocks;
    int blockSize;
    int currentBlock;

    public BlockContainer(int blockSize) {
        this.blockSize = blockSize;
        this.blocks = new ArrayList<>();
        this.currentBlock = 0;
    }


    public void add(T element) {
        if(blocks.isEmpty()){
            blocks.add(new Block<>());
        }

        if(blocks.get(currentBlock).size() == blockSize){
            blocks.add(new Block<>());
            currentBlock++;
        }

        blocks.get(currentBlock).addElement(element);
    }


    public void remove(T element) {
        if (blocks.isEmpty()) {
            return;
        }
        Block<T> block = blocks.get(currentBlock);
        block.remove(element);
        if (block.isEmpty()) {
            blocks.remove(currentBlock--);
        }
    }

    public void sort() {
        List<T> elements = blocks
                .stream()
                .flatMap(b -> b.getElements().stream())
                .sorted()
                .collect(Collectors.toList());

        currentBlock = 0;
        blocks.clear();
        elements.forEach(this::add);

    }

    @Override
    public String toString() {
        return blocks
                .stream()
                .map(Block::toString)
                .collect(Collectors.joining(","));
    }
}

public class BlockContainerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int size = scanner.nextInt();
        BlockContainer<Integer> integerBC = new BlockContainer<Integer>(size);
        scanner.nextLine();
        Integer lastInteger = null;
        for (int i = 0; i < n; ++i) {
            int element = scanner.nextInt();
            lastInteger = element;
            integerBC.add(element);
        }
        System.out.println("+++++ Integer Block Container +++++");
        System.out.println(integerBC);
        System.out.println("+++++ Removing element +++++");
        integerBC.remove(lastInteger);
        System.out.println("+++++ Sorting container +++++");
        integerBC.sort();
        System.out.println(integerBC);
        BlockContainer<String> stringBC = new BlockContainer<String>(size);
        String lastString = null;
        for (int i = 0; i < n; ++i) {
            String element = scanner.next();
            lastString = element;
            stringBC.add(element);
        }
        System.out.println("+++++ String Block Container +++++");
        System.out.println(stringBC);
        System.out.println("+++++ Removing element +++++");
        stringBC.remove(lastString);
        System.out.println("+++++ Sorting container +++++");
        stringBC.sort();
        System.out.println(stringBC);
    }
}
```