package leetcode.contest.c352

import utils.TypedUFS
import utils.print

fun main() {
    val s = SolutionD()
    s.sumImbalanceNumbers(intArrayOf(2, 3, 1, 4)).print()
}

class SolutionD {
    fun sumImbalanceNumbers(nums: IntArray): Int {
        var ans = 0
        for (i in nums.indices) {
            val ufs = TypedUFS<Int>(1000)
            val set = hashSetOf<Int>(nums[i])
            set.add(nums[i])
            ufs.union(nums[i], nums[i])
            for (j in i + 1 until nums.size) {
                set.add(nums[j])
                ufs.union(nums[j], nums[j])
                if (nums[j] - 1 in set) {
//                    println("union ${nums[j]} with ${nums[j] - 1}")
                    ufs.union(nums[j], nums[j] - 1)
                }
                if (nums[j] + 1 in set) {
//                    println("union ${nums[j]} with ${nums[j] + 1}")
                    ufs.union(nums[j], nums[j] + 1)
                }

//                println("$i $j ============")
                val c = ufs.keys.size
//                println("get count $c")
                ans += c - 1
            }
        }
        return ans
    }
}