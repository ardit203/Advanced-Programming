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
