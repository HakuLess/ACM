package leetcode.contest.b136

// 换根DP
class SolutionD {
    fun timeTaken(edges: Array<IntArray>): IntArray {
        val n = edges.size + 1
        val e = Array(n) { mutableListOf<Int>() }
        for (edge in edges) {
            e[edge[0]].add(edge[1])
            e[edge[1]].add(edge[0])
        }

        val f = IntArray(n)
        fun dfs1(sn: Int, fa: Int): Int {
            f[sn] = 0
            for (fn in e[sn]) {
                if (fn != fa) {
                    val t = dfs1(fn, sn) + if (fn % 2 == 1) 1 else 2
                    f[sn] = maxOf(f[sn], t)
                }
            }
            return f[sn]
        }
        dfs1(0, -1)

        val ans = IntArray(n)
        fun dfs2(sn: Int, fa: Int, fv: Int) {
            ans[sn] = maxOf(fv, f[sn])
            var mx1 = -1
            var fn1 = -1
            var mx2 = -1
            var fn2 = -1
            for (fn in e[sn]) {
                if (fn != fa) {
                    val t = f[fn] + if (fn % 2 == 1) 1 else 2
                    if (t > mx1) {
                        fn2 = fn1
                        mx2 = mx1
                        fn1 = fn
                        mx1 = t
                    } else if (t > mx2) {
                        fn2 = fn
                        mx2 = t
                    }
                }
            }
            for (fn in e[sn]) {
                if (fn != fa) {
                    val t = if (fn == fn1) {
                        maxOf(fv, mx2)
                    } else {
                        maxOf(fv, mx1)
                    }
                    dfs2(fn, sn, t + if (sn % 2 == 1) 1 else 2)
                }
            }
        }
        dfs2(0, -1, 0)

        return ans
    }
}