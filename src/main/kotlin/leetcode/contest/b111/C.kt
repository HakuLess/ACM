package leetcode.contest.b111

import utils.print

fun main() {
    val s = SolutionC()
    s.minimumOperations(listOf(1, 2)).print()
    s.minimumOperations(listOf(1, 2, 3)).print()
    s.minimumOperations(listOf(1, 1, 2)).print()
    s.minimumOperations(listOf(1, 1, 3)).print()
}

class SolutionC {
    fun minimumOperations(nums: List<Int>): Int {
        val n = nums.size
        var ans = n
        for (i in 0..nums.size) {
            for (j in i - 1 until nums.size) {
                // 只有i -> j是2
                // 若j<i 则无2
                var tmp = 0
                for (a in 0..i - 1) {
                    if (a in nums.indices && nums[a] != 1) tmp++
                }
                for (a in i..j) {
                    if (a in nums.indices && nums[a] != 2) tmp++
                }
                for (a in j + 1..nums.lastIndex) {
                    if (a in nums.indices && nums[a] != 3) tmp++
                }
                ans = minOf(ans, tmp)
            }
        }
        return ans
    }
}