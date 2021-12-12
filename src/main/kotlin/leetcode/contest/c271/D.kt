package leetcode.contest.c271

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.maxTotalFruits("[2,8],[6,3],[8,6]".toGrid(), 5, 4).print()
//    s.maxTotalFruits("[[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]]".toGrid(), 5, 4).print()
    s.maxTotalFruits("[200000,10000]".toGrid(), 200000, 0).print()
}

class SolutionD {
    fun maxTotalFruits(fruits: Array<IntArray>, startPos: Int, k: Int): Int {
        val f = IntArray(200005)
        fruits.forEach {
            f[it[0]] = it[1]
        }
        val left = IntArray(k + 1)
        for (i in 1..k) {
            left[i] = left[i - 1]
            if (startPos - i in f.indices) {
                left[i] += f[startPos - i]
            }
        }
        val right = IntArray(k + 1)
        for (i in 1..k) {
            right[i] = right[i - 1]
            if (startPos + i in f.indices) {
                right[i] += f[startPos + i]
            }
        }

        var max = f[startPos]
        var ans = 0
        for (i in 1..k) {
            ans = f[startPos]
            ans += left[i]
            if (k - 2 * i in right.indices)
                ans += right[k - 2 * i]
            max = maxOf(max, ans)
        }
        for (i in 1..k) {
            ans = f[startPos]
            ans += right[i]
            if (k - 2 * i in left.indices)
                ans += left[k - 2 * i]
            max = maxOf(max, ans)
        }

        return max
    }
}