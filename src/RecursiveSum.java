import java.util.List;
import java.util.concurrent.RecursiveTask;

class RecursiveSum extends RecursiveTask<Integer> {
    private final List<Integer> arr;
    private final Integer start;
    private final Integer end;
    private final int PARTS = 2;

    public RecursiveSum(Integer start, Integer end, List<Integer> arr) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        final int diff = end - start;
        if (diff < PARTS) {
            return arr.get(start);
        } else {
            return ForkJoinPoolArraySum();
        }
    }

    private Integer ForkJoinPoolArraySum() {
        final int middle = (end - start) / 2 + start;
        RecursiveSum task1 = new RecursiveSum(start, middle, arr);
        RecursiveSum task2 = new RecursiveSum(middle, end, arr);
        invokeAll(task1, task2);
        return task1.join() + task2.join();
    }
}