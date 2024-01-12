import java.util.*;

public class WIS {

    static class Job {
        long start;
        long end;

        long weight;

        public Job(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

    }

    /**
     * weighted interval schedule to max the total value / weight
     *
     * @param jobs
     * @return the max cumulative weight
     */
    public static long wis(List<Job> jobs) {
        Collections.sort(jobs, Comparator.comparingLong((Job j) -> j.end));
        ArrayList<Long> res = new ArrayList<>();
        res.add(jobs.get(0).weight);

        for (int j = 1; j < jobs.size(); j++) {
            long preFitWeight = findFitLatestIdxWeight(res, jobs, jobs.get(j).start);
            // don't take jobs[j], just get the previous cached weight or value
            long skipVal = res.get(j - 1);
            long takeVal = jobs.get(j).weight + preFitWeight;
            res.add(Math.max(skipVal, takeVal));
        }
        return res.get(jobs.size() - 1);
    }


    // TODO: 把整个算法从O(n^2)优化成O(n log n)
    private static long findFitLatestIdxWeight(List<Long> res, List<Job> jobs, long startJ) {
        for (int i = jobs.size() - 1; i >= 0; i--) {
            if (jobs.get(i).end <= startJ) {
                return res.get(i);  // Return the cumulative value from res, not from the job directly
            }
        }
        return 0;
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
                int weight = scanner.nextInt();
                jobs.add(new Job(start, end, weight));
            }

            System.out.println(wis(jobs));
        }

        scanner.close();
    }
}
