import java.util.*;

public class Fif {
    private static int fif(int cacheSize, int[] requests) {
        // cache doesn't care about sequence
        HashSet<Integer> cache = new HashSet<>();
        int pageFaults = 0;
        for (int i = 0; i < requests.length; i++) {
            int request = requests[i];
            if (cache.contains(request)) continue;
            // cache is full then swap
            if (cache.size() == cacheSize) {
                int furthest = -1; // tag for the furthest request page or the cache won't be used in future
                int idx = -1; // last time the found furthest page in request
                for (int page : cache) { // find the furthest page
                    int nextIndex = i + 1;
                    // get the current furthest nextIndex for the request in future
                    while (nextIndex < requests.length && requests[nextIndex] != page) {
                        nextIndex++;
                    }
                    if (nextIndex == requests.length) { // it will not be visited at all in the future
                        furthest = page;
                        break;
                    }
                    if (nextIndex > idx) { // update the idx to make it the local furthest
                        idx = nextIndex;
                        furthest = page; // local furthest
                    }
                }
                cache.remove(furthest); // global furthest in cache and remove it
            }
            cache.add(request); // update cache
            pageFaults++;
        }
        return pageFaults;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        // traverse every test case
        while (t-- > 0) {
            int cacheSize = scanner.nextInt();
            int numberOfRequests = scanner.nextInt();
            int[] requests = new int[numberOfRequests];
            for (int i = 0; i < numberOfRequests; i++) {
                requests[i] = scanner.nextInt();
            }
            System.out.println(fif(cacheSize, requests));
        }
        // close Scanner
        scanner.close();
    }
}
