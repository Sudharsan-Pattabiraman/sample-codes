import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * @author spattabiraman 2021-05-07
 */
public class Java8Streams {
    public static void main(String[] args) {

        final Supplier<IntStream> integerStreamSupplier = () -> IntStream.rangeClosed(0, 10);

        System.out.println(integerStreamSupplier.get().filter(i -> i % 2 == 0).reduce(1, Integer::sum));
    }
}
