package leetcode.contest.b110

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumSeconds(listOf(1, 2, 1, 2)).print()
    s.minimumSeconds(listOf(1, 2, 3, 1, 2, 3)).print()
    s.minimumSeconds(listOf(5, 5, 5, 5)).print()
}

class SolutionC {
    fun minimumSeconds(nums: List<Int>): Int {
        val n = nums.size
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in nums.indices) {
            val item = nums[i]
            map[item] = map.getOrDefault(item, arrayListOf())
            map[item]!!.add(i)
        }
        var ans = Int.MAX_VALUE
        map.forEach { key, value ->
            var tmp = 0
            for (i in 0 until value.lastIndex) {
                tmp = maxOf(tmp, value[i + 1] - value[i] - 1)
            }
            tmp = maxOf(tmp, n + value.first() - value.last() - 1)
//            println("$key $value with $tmp")

            ans = minOf((tmp + 1) / 2, ans)
        }
        return ans
    }
}