package leetcode.contest.b101

import utils.biMin
import utils.gcd
import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.makeSubKSumEqual(intArrayOf(1, 4, 1, 3), 2).print()
    s.makeSubKSumEqual(intArrayOf(2, 5, 5, 7), 3).print()
    s.makeSubKSumEqual(intArrayOf(2, 10, 9), 1).print()
}

class SolutionC {
    fun makeSubKSumEqual(arr: IntArray, k: Int): Long {
        val n = arr.size
        if (n == k) return 0

        // 序列中位数，序列元素相等
        val gao = { vec: MutableList<Int> ->
            vec.sort()
            val mid = vec[vec.size / 2]
            var ret = 0L
            for (x in vec) ret += abs(x - mid)
            ret
        }

        var ans = 0L
        // 每隔g个一组
        val g = gcd(n, k)
        for (i in 0 until g) {
            val vec = mutableListOf<Int>()
            var j = i
            while (j < n) {
                vec.add(arr[j])
                j += g
            }
            ans += gao(vec)
        }
        return ans

    }
}