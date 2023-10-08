import java.util.*;

public class GreedySample {

    static class Job implements Comparable<Job> {
        int start;
        int end;

        public Job(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Job other) {
            return this.end - other.end; // Sort based on end time
        }
    }

    public static int maxScheduledJobs(List<Job> jobs) {
        Collections.sort(jobs);

        int count = 0;
        int lastEndTime = 0;

        for (Job job : jobs) {
            if (job.start >= lastEndTime) {
                count++;
                lastEndTime = job.end;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt(); // Number of instances

        while (t-- > 0) {
            int numJobs = scanner.nextInt(); // Number of jobs in the current instance
            List<Job> jobs = new ArrayList<>();

            for (int i = 0; i < numJobs; i++) {
                int start = scanner.nextInt();
                int end = scanner.nextInt();
                jobs.add(new Job(start, end));
            }

            System.out.println(maxScheduledJobs(jobs));
        }

        scanner.close();
    }
}
