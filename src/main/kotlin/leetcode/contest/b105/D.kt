package leetcode.contest.b105

import utils.*

fun main() {
    val s = SolutionD()
    s.canTraverseAllPairs(intArrayOf(2, 3, 6)).print()
    s.canTraverseAllPairs(intArrayOf(3, 9, 5)).print()
}

class SolutionD {
    fun canTraverseAllPairs(nums: IntArray): Boolean {
        if (nums.size == 1) return true
        if (nums.any { it == 1 }) return false
        val max = nums.maxOrNull()!!
        val primes = getPrime(max)

        val ufs = TypedUFS<Int>(100005)
        for (i in nums.indices) {
            var c = nums[i]
            while (c != 1 && primes[c] != 1) {
                ufs.union(nums[i], primes[c])
                c /= primes[c]
            }
            ufs.union(nums[i], c)
        }

        return nums.all { ufs.typedFind(it) == ufs.typedFind(nums[0]) }
    }

}