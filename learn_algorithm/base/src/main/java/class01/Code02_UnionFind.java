package class01;

import java.io.*;

public class Code02_UnionFind {

    public static int MAXN = 1000001;

    public static int[] father = new int[MAXN];

    public static int[] size = new int[MAXN];

    public static int[] help = new int[MAXN];

    // 初始化并查集
    public static void init(int n) {
        for (int i = 0; i <= n; i++) {
            father[i] = i; // 每个father是自己
            size[i] = 1;  // 每个元素自己是一个小集合
        }
    }

    // 从i开始寻找集合代表点
    public static int find(int i) {
        int hi = 0; // 维护help数组的index
        while (i != father[i]) {
            help[hi++] = i;
            i = father[i];
        }
        for (hi--; hi >= 0; hi--) {
            father[help[hi]] = i;
        }
        return i;
    }

    // 查询x和y是不是同一个集合
    public static boolean isSameSet(int x, int y) {
        return find(x) == find(y);
    }

    // x所在的集合， 和y所在的集合，合并成一个集合
    public static void union(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            if (size[fx] > size[fy]) {
                size[fx] += size[fy];
                father[fy] = fx;
            } else {
                size[fy] += size[fx];
                father[fx] = fy;
            }
        }
    }

    // 算法课中没体现
    public int size() {
        int count = 0;
        for (int i = 0; i < father.length; i++) {
            if (i == father[i]) {
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while (in.nextToken() != StreamTokenizer.TT_EOF) {
            int n = (int) in.nval;
            init(n);
            in.nextToken();
            int m = (int) in.nval;
            for (int i = 0; i < m; i++) {
                in.nextToken();
                int op = (int) in.nval;
                in.nextToken();
                int x = (int) in.nval;
                in.nextToken();
                int y = (int) in.nval;
                if (op == 1) {
                    out.println(isSameSet(x, y) ? "Yes" : "No");
                    out.flush();
                } else {
                    union(x, y);
                }
            }
        }
    }


}
