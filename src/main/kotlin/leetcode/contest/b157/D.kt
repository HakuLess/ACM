package leetcode.contest.b157

import utils.*

fun main() {
    val s = SolutionD()
    s.assignEdgeWeights("[[1,2]]".toGrid(), "[[1,1],[1,2]]".toGrid()).print()
    s.assignEdgeWeights("[[1,2],[1,3],[3,4],[3,5]]".toGrid(), "[[1,4],[3,4],[2,5]]".toGrid()).print()
}

class SolutionD {
    fun assignEdgeWeights(edges: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val mod = 1_000_000_007L
        val n = edges.size + 1
        val tree = Tree(n + 1, edges, root = 1)

        fun power(a: Long, b: Long): Long {
            var res = 1L
            var base = a
            var exp = b
            while (exp > 0) {
                if (exp % 2 == 1L) res = res * base % mod
                base = base * base % mod
                exp /= 2
            }
            return res
        }

        val maxDep = 100002

        // 预处理阶乘和逆元
        val fac = LongArray(maxDep + 2) { 1L }
        val inv = LongArray(maxDep + 2) { 1L }

        for (i in 1..maxDep) fac[i] = fac[i - 1] * i % mod
        inv[maxDep] = power(fac[maxDep], mod - 2)
        for (i in maxDep - 1 downTo 1) inv[i] = inv[i + 1] * (i + 1) % mod

        fun comb(n: Int, k: Int): Long {
            if (k < 0 || k > n) return 0
            return fac[n] * inv[k] % mod * inv[n - k] % mod
        }

        val ans = ArrayList<Int>()

        queries.forEach {
            val (u, v) = it

            val distance = tree.distance(u, v)
//            println("$u $v with $distance")

            var tmp = 0L
            for (i in 1..distance step 2) {
//                println("$ans += C($distance, $i) with ${comb(distance, i)}")
                tmp = (tmp + comb(distance, i)) % mod
            }
            ans.add(tmp.toInt())
        }


        return ans.toIntArray()
    }
}