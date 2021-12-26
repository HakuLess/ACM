package leetcode.contest.c273

import utils.print

fun main() {
    val s = SolutionD()
//    s.recoverArray(intArrayOf(2, 10, 6, 4, 8, 12)).print()
    // [51,100,149]
//    s.recoverArray(intArrayOf(1, 50, 99, 101, 150, 199)).print()

    // [2,3,7,8,8,9,9,10]
    s.recoverArray(intArrayOf(11, 6, 3, 4, 8, 7, 8, 7, 9, 8, 9, 10, 10, 2, 1, 9)).print()
}

class SolutionD {
    fun recoverArray(nums: IntArray): IntArray {
        nums.sort()
        for (k in nums.indices) {
            val map = HashMap<Int, Int>()
            // 等差数列的差
            val d = nums[k] - nums[0]
            if (d == 0 || d % 2 != 0) continue
            val left = ArrayList<Int>()
            val right = ArrayList<Int>()
            for (i in nums.indices) {
                val key = nums[i]
                val lk = nums[i] - d
                if (lk in map.keys) {
                    map[lk] = map.getOrDefault(lk, 0) - 1
                    right.add(key)
                    if (map[lk] == 0) {
                        map.remove(lk)
                    }
                } else {
                    map[key] = map.getOrDefault(key, 0) + 1
                    left.add(key)
                }
            }
            if (left.size == right.size) {
                val ans = IntArray(nums.size / 2)
                for (i in ans.indices) {
                    ans[i] = (left[i] + right[i]) / 2
                }
                return ans
            }
        }
        return intArrayOf()
    }
}