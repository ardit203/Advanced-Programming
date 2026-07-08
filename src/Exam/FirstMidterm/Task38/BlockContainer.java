package Exam.FirstMidterm.Task38;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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