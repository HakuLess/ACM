package leetcode.contest.c363

import utils.print

fun main() {
    val s = SolutionD()
    s.maximumSum(listOf(5, 10, 3, 10, 1, 13, 7, 9, 4)).print()
}

class SolutionD {
    fun maximumSum(nums: List<Int>): Long {

        val sq = ArrayList<Int>()
        var i = 1
        while (i * i <= nums.size) {
            sq.add(i * i)
            i++
        }

        var ans = 0L
        val map = HashMap<Int, Long>()
        for (i in nums.indices) {
            sq.forEach {
                if ((i + 1) % it == 0) {
                    val k = (i + 1) / it
                    map[k] = map.getOrDefault(k, 0L) + nums[i]
                }
            }
        }
        map.forEach { t, u ->
            ans = maxOf(ans, u)
        }

        return ans
    }
}