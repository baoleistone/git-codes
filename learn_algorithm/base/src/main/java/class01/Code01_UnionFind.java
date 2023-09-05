package class01;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集: 用map实现
 * 笔试或者平时用的并查集一律用数组实现
 */
public class Code01_UnionFind {

    public static class UnionFind<V> {
        public HashMap<V, V> father;
        public HashMap<V, Integer> size;

        // 课上讲的时候
        // 包了一层
        // 其实不用包一层哦
        public UnionFind(List<V> values) {
            father = new HashMap<>();
            size = new HashMap<>();
            for (V cur : values) {
                father.put(cur, cur);
                size.put(cur, 1);
            }
        }

        // 给你一个节点，请你往上到不能再往上，把代表返回
        public V findFather(V cur) {
            Stack<V> path = new Stack<>();
            while (cur != father.get(cur)) {
                path.push(cur);
                cur = father.get(cur);
            }
            while (!path.empty()) {
                father.put(path.pop(), cur);
            }
            return cur;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(a) == findFather(b);
        }

        public void union(V a, V b) {
            V aFather = findFather(a);
            V bFather = findFather(b);
            if (aFather != bFather) {
                int aSize = size.get(aFather);
                int bSize = size.get(bFather);
                if (aSize > bSize) {
                    father.put(bFather, aFather);
                    size.put(aFather, aSize + bSize);
                    size.remove(bFather);
                } else {
                    father.put(aFather, bFather);
                    size.put(bFather, aSize + bSize);
                    size.remove(aFather);
                }
            }
        }

        public int size() {
            return size.size();
        }
    }
}
