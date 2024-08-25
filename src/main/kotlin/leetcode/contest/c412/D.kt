package leetcode.contest.c412

import utils.print

fun main() {
    val s = SolutionD()
    s.countPairs(intArrayOf(1023, 2310, 2130, 213)).print()
}

class SolutionD {
    fun countPairs(nums: IntArray): Int {
        fun almostEqual(a: Int, b: Int): Boolean {
            var nDiff = 0
            val v1 = IntArray(4)
            val v2 = IntArray(4)
            var aVar = a
            var bVar = b

            while (aVar != 0 || bVar != 0) {
                if (aVar % 10 != bVar % 10) {
                    if (nDiff < 4) {
                        v1[nDiff] = aVar % 10
                        v2[nDiff] = bVar % 10
                        nDiff++
                    } else {
                        return false
                    }
                }
                aVar /= 10
                bVar /= 10
            }

            return when (nDiff) {
                0 -> true
                1 -> false
                2 -> v1[0] == v2[1] && v1[1] == v2[0]
                3 -> (v1[0] == v2[1] && v1[1] == v2[2] && v1[2] == v2[0])
                        || (v1[0] == v2[2] && v1[1] == v2[0] && v1[2] == v2[1])
                else -> (v1[0] == v2[1] && v1[1] == v2[0] && v1[2] == v2[3] && v1[3] == v2[2])
                        || (v1[0] == v2[2] && v1[2] == v2[0] && v1[1] == v2[3] && v1[3] == v2[1])
                        || (v1[0] == v2[3] && v1[3] == v2[0] && v1[1] == v2[2] && v1[2] == v2[1])
            }
        }

        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                val a = nums[i]
                val b = nums[j]
                if (almostEqual(a, b)) {
                    ans++
                }
            }
        }
        return ans
    }
}