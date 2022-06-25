package leetcode.contest.b81

import utils.print

fun main() {
    val s = SolutionC()
    s.maximumXOR(intArrayOf(3, 2, 4, 6)).print()
    s.maximumXOR(intArrayOf(1, 2, 3, 9, 2)).print()
}

class SolutionC {
    fun maximumXOR(nums: IntArray): Int {
        val max = 30
        val map0 = IntArray(max)
        val map1 = IntArray(max)
        nums.forEach {
            val cur = it.toString(2).padStart(max, '0')
            for (i in cur.indices) {
                if (cur[i] == '0') {
                    map0[i]++
                } else {
                    map1[i]++
                }
            }
        }
        val ans = IntArray(max)
        for (i in 0 until max) {
            if (map1[i] % 2 == 1) {
                ans[i] = 1
            } else if (map1[i] > 0 && (map1[i] - 1) % 2 == 1) {
                ans[i] = 1
            } else {
                ans[i] = 0
            }
        }
        return ans.joinToString("").toInt(2)
    }
}