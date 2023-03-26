package leetcode.contest.c338

import utils.biFirstIndexOf
import utils.biLastIndexOf
import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
//    s.minOperations(intArrayOf(3, 1, 6, 8), intArrayOf(1, 5)).joinToString().print()
//    s.minOperations(intArrayOf(2, 9, 6, 3), intArrayOf(10)).joinToString().print()
    s.minOperations(
        intArrayOf(
            47,
            50,
            97,
            58,
            87,
            72,
            41,
            63,
            41,
            51,
            17,
            21,
            7,
            100,
            69,
            66,
            79,
            92,
            84,
            9,
            57,
            26,
            26,
            28,
            83,
            38
        ),
        intArrayOf(9)
    ).joinToString().print()
}

class SolutionC {
    fun minOperations(nums: IntArray, queries: IntArray): List<Long> {
        val n = nums.size
        val s1 = nums.sorted().toIntArray()
        val p1 = s1.preSumArray(false)

        val s2 = nums.sortedDescending().toIntArray()
        val p2 = s2.preSumArray(false)

//        p1.print()
//        p2.print()
        val ans = ArrayList<Long>()
        for (i in queries.indices) {
            var cur = 0L
            val q = queries[i].toLong()
            if (q < s1[0]) {
                ans.add(p1.last() - q * n)
                continue
            }

            if (q > s2[0]) {
                ans.add(q * n - p2.last())
                continue
            }

            val l = s1.biFirstIndexOf { it >= q }
            if (l >= 1) {
                cur += l * q - p1[l - 1]
            }
            val r = s2.biFirstIndexOf { it <= q }
            if (r >= 1) {
                cur += p2[r - 1] - r * q
            }
            ans.add(cur)
        }
        return ans
    }
}