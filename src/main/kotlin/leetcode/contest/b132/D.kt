package leetcode.contest.b132

import utils.print

fun main() {
    val s = SolutionD()
    s.maximumLength(intArrayOf(1, 2, 1, 1, 3), 2).print()
//    s.maximumLength(intArrayOf(1, 2, 3, 4, 5, 1), 0).print()
}

class SolutionD {
    fun maximumLength(nums: IntArray, k: Int): Int {
        // 已有K个 num结尾的最大 长度
        val mapArr = Array<HashMap<Int, Int>>(k + 1) { HashMap() }
        var ans = 0
        for (i in nums.indices) {
            val cur = nums[i]
            for (j in mapArr.indices.reversed()) {
                if (j + 1 in mapArr.indices) {
                    mapArr[j].forEach { (k, v) ->
                        // 不相同，尝试增加
                        if (k != cur) {
                            mapArr[j + 1][cur] = maxOf(mapArr[j + 1].getOrDefault(cur, 0), v + 1)
                            ans = maxOf(ans, mapArr[j + 1][cur]!!)
                        }
                    }
                }

                mapArr[j][cur] = mapArr[j].getOrDefault(cur, 0) + 1
                ans = maxOf(ans, mapArr[j][cur]!!)
            }
        }
        return ans
    }
}