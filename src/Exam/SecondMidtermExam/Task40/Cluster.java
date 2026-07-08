package Exam.SecondMidtermExam.Task40;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Cluster<T extends ICluster<T>> {
    private Map<Long, T> elements;

    public Cluster() {
        this.elements = new HashMap<>();
    }


    public void addItem(T element) {
        elements.putIfAbsent(element.getId(), element);
    }

    public void near(long id, int top) {
        T element = elements.get(id);

        List<T> result = elements.values()
                .stream()
                .filter(e -> e.getId() != id)
                .sorted(Comparator.comparingDouble(left -> left.getDistance(element)))
                .limit(top)
                .collect(Collectors.toList());

        for (int i = 0; i < result.size(); i++) {
            T res = result.get(i);
            System.out.printf("%d. %d -> %.3f\n", i+1, res.getId(), res.getDistance(element));
        }
    }
}