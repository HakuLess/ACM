package leetcode.contest.b124

import utils.print

fun main() {
    val s = SolutionD()
//    s.maxSelectedElements(intArrayOf(2, 1, 5, 1, 1)).print()
//    s.maxSelectedElements(intArrayOf(1, 4, 7, 10)).print()
    // 8
//    s.maxSelectedElements(intArrayOf(8, 10, 6, 12, 9, 12, 2, 3, 13, 19, 11, 18, 10, 16)).print()
    // 14
    s.maxSelectedElements(intArrayOf(8, 13, 18, 10, 16, 19, 11, 17, 15, 18, 9, 12, 15, 8, 9, 14, 7)).print()
    // 12
    s.maxSelectedElements(intArrayOf(4, 12, 8, 14, 14, 12, 11, 20, 17, 17, 4, 10, 10, 18, 1, 16)).print()
}

class SolutionD {
    fun maxSelectedElements(nums: IntArray): Int {
        nums.sort()
//        nums.joinToString().print()

        val cnt = IntArray(1000001)
        val set = nums.toHashSet()
        for (i in cnt.indices) {
            if (i in set) {
                cnt[i] = cnt[i - 1] + 1
            }
        }
//        cnt.filter { it != 0 }.joinToString().print()

        var start = nums[0]
        var cur = start
        var ans = 1
        var tmp = 1

        for (i in nums.indices) {
            if (i == 0) continue
            if (nums[i] == cur + 1 || nums[i] == cur) {
                tmp++
                cur++
            } else if (nums[i] < cur) {
                continue
            } else {
                tmp = 1
                start = nums[i]
                cur = start
            }

//            println("$i with $a $b  start $start with ${cnt[start - 2]}")

            ans = maxOf(ans, tmp + if (start - 2 in cnt.indices) cnt[start - 2] else 0)
        }
        return ans
    }
}