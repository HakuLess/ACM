package leetcode.contest.b157

import utils.*

fun main() {
    val s = SolutionC()
    s.assignEdgeWeights("[[1,2]]".toGrid()).print()
    s.assignEdgeWeights("[[1,2],[1,3],[3,4],[3,5]]".toGrid()).print()
}

class SolutionC {
    fun assignEdgeWeights(edges: Array<IntArray>): Int {
        val mod = 1_000_000_007L
        val tree = edges.toNTree(1)
        val maxDep = tree.depth() - 1

        // 预处理阶乘和逆元
        val fac = LongArray(maxDep + 2) { 1L }
        val inv = LongArray(maxDep + 2) { 1L }

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

        for (i in 1..maxDep) fac[i] = fac[i - 1] * i % mod
        inv[maxDep] = power(fac[maxDep], mod - 2)
        for (i in maxDep - 1 downTo 1) inv[i] = inv[i + 1] * (i + 1) % mod

        fun comb(n: Int, k: Int): Long {
            if (k < 0 || k > n) return 0
            return fac[n] * inv[k] % mod * inv[n - k] % mod
        }

        var ans = 0L
        for (i in 1..maxDep step 2) {
//            println("$ans += C($maxDep, $i) with ${longComb(maxDep.toLong(), i.toLong())}")
            ans = (ans + comb(maxDep, i)) % mod
        }

        return ans.toInt()
    }
}