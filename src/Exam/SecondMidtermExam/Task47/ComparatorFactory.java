package Exam.SecondMidtermExam.Task47;

import java.util.Comparator;

public class ComparatorFactory {
    public static Comparator<Product> OLDEST_FIRST = Comparator.comparing(Product::getCreatedAt);
    //    public static Comparator<Product> NEWEST_FIRST = OLDEST_FIRST.reversed();
    public static Comparator<Product> LOWEST_PRICE_FIRST = Comparator.comparing(Product::getPrice);
    //    public static Comparator<Product> HIGHEST_PICE_FIRST = LOWEST_PRICE_FIRST.reversed();
    public static Comparator<Product> LEAST_SOLD_FIRST = Comparator.comparing(Product::getSales);
//    public static Comparator<Product> MOST_SOLD_FIRST = LEAST_SOLD_FIRST.reversed();

    public static Comparator<Product> getComparator(COMPARATOR_TYPE type) {
        switch (type) {
            case NEWEST_FIRST:
                return OLDEST_FIRST.reversed();
            case OLDEST_FIRST:
                return OLDEST_FIRST;
            case LOWEST_PRICE_FIRST:
                return LOWEST_PRICE_FIRST;
            case HIGHEST_PRICE_FIRST:
                return LOWEST_PRICE_FIRST.reversed();
            case MOST_SOLD_FIRST:
                return LEAST_SOLD_FIRST.reversed();
            case LEAST_SOLD_FIRST:
                return LEAST_SOLD_FIRST;
            default:
                throw new RuntimeException("NO COMPARATOR WAS GIVEN");
        }
    }
}