import java.util.List;
import java.util.concurrent.RecursiveTask;

class RecursiveSum extends RecursiveTask<Integer> {
    private final List<Integer> arr;
    private final Integer size;

    RecursiveSum(List<Integer> arr, Integer size) {
        this.arr = arr;
        this.size = size;
    }

    @Override
    protected Integer compute() {
        if (size == 0) {
            return arr.get(size);
        }
        RecursiveSum previous = new RecursiveSum(arr, size - 1);
        previous.fork();
        return arr.get(size) + previous.join();
    }
}