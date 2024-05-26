package leetcode.contest.c399

import utils.print

fun main() {
    val s = SolutionC()

    val a = IntArray(100000) { 1 }
    s.numberOfPairs(a, a, 1).print()
}

class SolutionC {
    fun numberOfPairs(nums1: IntArray, nums2: IntArray, k: Int): Long {
        // 创建 nums2 的频率哈希表
        val freqMap = mutableMapOf<Int, Int>()
        for (num in nums2) {
            freqMap[num] = freqMap.getOrDefault(num, 0) + 1
        }

        var ans = 0L

        // 遍历 nums1 的每个元素
        for (num1 in nums1) {
            // 找到 num1 的所有因子
            var factor = 1
            while (factor * factor <= num1) {
                if (num1 % factor == 0) {
                    // 检查因子 factor 是否符合条件
                    if ((factor % k == 0) && freqMap.contains(factor / k)) {
                        ans += freqMap[factor / k]!!
                    }
                    // 检查 num1 / factor 是否符合条件（避免重复因子）
                    if (factor != num1 / factor) {
                        if ((num1 / factor % k == 0) && freqMap.contains(num1 / factor / k)) {
                            ans += freqMap[num1 / factor / k]!!
                        }
                    }
                }
                factor++
            }
        }

        return ans
    }
}