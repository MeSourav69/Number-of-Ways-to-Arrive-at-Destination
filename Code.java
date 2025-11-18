//Number of Ways to Arrive at Destination
class Code{
    static final int MOD = 1000000007;

    public int countPaths(int V, int[][] edges) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(new int[]{e[1], e[2]});
            graph.get(e[1]).add(new int[]{e[0], e[2]});
        }

        long[] dist = new long[V];
        long[] ways = new long[V];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[1]));
        
        dist[0] = 0;
        ways[0] = 1;
        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            int node = (int) cur[0];
            long d = cur[1];

            if (d > dist[node]) continue;

            for (int[] nxt : graph.get(node)) {
                int nei = nxt[0];
                long wt = nxt[1];
                long newDist = d + wt;

                if (newDist < dist[nei]) {
                    dist[nei] = newDist;
                    ways[nei] = ways[node];
                    pq.add(new long[]{nei, newDist});
                } else if (newDist == dist[nei]) {
                    ways[nei] = (ways[nei] + ways[node]) % MOD;
                }
            }
        }
        return (int) ways[V - 1];
    }
          }
