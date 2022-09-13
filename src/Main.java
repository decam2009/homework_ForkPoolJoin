import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static int recSumma(List<Integer> arr, int size) {
        if (size == 0) {
            return arr.get(size);
        }
        return arr.get(size) + recSumma(arr, size - 1);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        final List<Integer> arr = new ArrayList<>();
        final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        for (int i = 0; i < 1_000_000; i++) {
            arr.add(new Random().nextInt(5));
        }

       // System.out.println(arr);

        long start = System.currentTimeMillis();
        recSumma(arr, arr.size() - 1);
        System.out.printf("Однопоточный режим - %d милисекунд \n", System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        final Integer task = pool.invoke(new RecursiveSum(arr, arr.size() - 1));
        //System.out.println(task.intValue());
        System.out.printf("Многопоточный режим - %d милисекунд \n", System.currentTimeMillis() - start1);
    }
}
