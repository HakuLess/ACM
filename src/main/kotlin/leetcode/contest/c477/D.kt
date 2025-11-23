package leetcode.contest.c477

import utils.print

fun main() {
    val s = SolutionD()
    // 3
    s.countEffective(intArrayOf(1, 2, 3)).print()
    // 4
    s.countEffective(intArrayOf(7, 4, 6)).print()
    // 1
    s.countEffective(intArrayOf(8, 8)).print()
    // 5
    s.countEffective(intArrayOf(2, 2, 1)).print()
}

// Not Finished TLE
class SolutionD {
    fun countEffective(nums: IntArray): Int {

        val mod = 1_000_000_007L
        val n = nums.size

        // all bits
        var all = 0
        for (x in nums) all = all or x

        // 对每个 bit 收集 indices
        val groups = Array(21) { mutableListOf<Int>() }
        for (i in nums.indices) {
            for (b in 0..20) {
                if ((all shr b) and 1 == 1 && ((nums[i] shr b) and 1 == 1)) {
                    groups[b].add(i)
                }
            }
        }

        // active bits
        val bits = (0..20).filter { ((all shr it) and 1) == 1 }
        val k = bits.size

        // Precompute pow2
        val pow2 = LongArray(n + 1)
        pow2[0] = 1
        for (i in 1..n) pow2[i] = (pow2[i - 1] * 2) % mod

        var ans = 0L

        // 对 bits 的每个非空子集做容斥
        val totalMasks = 1 shl k
        for (mask in 1 until totalMasks) {
            var cntBits = 0
            val unionSet = HashSet<Int>()

            for (i in 0 until k) {
                if ((mask shr i) and 1 == 1) {
                    cntBits++
                    unionSet.addAll(groups[bits[i]])
                }
            }

            val mustPick = unionSet.size
            val ways = pow2[n - mustPick]

            if (cntBits % 2 == 1) {
                ans = (ans + ways) % mod
            } else {
                ans = (ans - ways + mod) % mod
            }
        }

        return ans.toInt()
    }
}