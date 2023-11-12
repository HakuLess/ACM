package leetcode.contest.c371

import utils.*

fun main() {
    val s = SolutionD()
    s.maximumStrongPairXor(intArrayOf(1, 2, 3, 4, 5)).print()
    s.maximumStrongPairXor(intArrayOf(10, 100)).print()
    s.maximumStrongPairXor(intArrayOf(500, 520, 2500, 3000)).print()
}

class SolutionD {
    fun maximumStrongPairXor(nums: IntArray): Int {
        val trie = Trie<Int>()
        nums.sort()
        var l = 0
        var ans = 0
        for (i in nums.indices) {
            val cur = nums[i]
            while (nums[l] * 2 < cur) {
                trie.removeInt(nums[l])
                l++
            }
            trie.insertInt(nums[i])
            ans = maxOf(ans, trie.maxXor(nums[i]))
        }
        return ans
    }
}