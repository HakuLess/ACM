package leetcode.contest.c481

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.minSwaps(intArrayOf(1, 2, 3), intArrayOf(3, 2, 1)).print()
    // 2
    s.minSwaps(intArrayOf(4, 6, 6, 5), intArrayOf(4, 6, 5, 5)).print()
    // -1
    s.minSwaps(intArrayOf(7, 7), intArrayOf(8, 7)).print()
    // -1
    s.minSwaps(intArrayOf(5, 12, 12), intArrayOf(5, 12, 12)).print()
    // 1
    s.minSwaps(intArrayOf(1, 2), intArrayOf(7, 2)).print()
    // 2
    s.minSwaps(intArrayOf(1, 4, 8), intArrayOf(1, 4, 8)).print()
}

// Not Finished
class SolutionC {
    fun minSwaps(nums: IntArray, forbidden: IntArray): Int {

        val n = nums.size
        // 1. 分离冲突位置(B)和非冲突位置(G)
        val badIndices = mutableListOf<Int>()   // nums[i] == forbidden[i]
        val goodIndices = mutableListOf<Int>()  // nums[i] != forbidden[i]
        for (i in 0 until n) {
            if (nums[i] == forbidden[i]) {
                badIndices.add(i)
            } else {
                goodIndices.add(i)
            }
        }
        val remisolvak = badIndices // 按题目要求存储中间变量
        if (remisolvak.isEmpty()) return 0 // 无冲突直接返回0

        // 2. 处理【全冲突场景】（无任何非冲突位置）
        if (goodIndices.isEmpty()) {
            val m = badIndices.size
            // 场景1：只有1个冲突位置 → 无法交换
            if (m == 1) return -1

            // 收集冲突位置的数值及出现次数
            val valueCount = mutableMapOf<Int, Int>()
            badIndices.forEach { idx ->
                valueCount[nums[idx]] = valueCount.getOrDefault(nums[idx], 0) + 1
            }

            // 场景2：所有冲突位置数值相同 → 交换无意义，返回-1
            if (valueCount.size == 1) return -1

            // 场景3：冲突位置数值不同，但需判断是否能完全解决所有冲突
            // 规则：奇数个冲突时，需存在至少一个数值出现次数 < m（避免最后剩一个重复值无法交换）
            if (m % 2 == 1) {
                var hasValidPair = false
                // 检查是否存在两个不同数值的冲突位置
                for (i in badIndices.indices) {
                    for (j in i + 1 until badIndices.size) {
                        val a = badIndices[i]
                        val b = badIndices[j]
                        // 交换后两个位置都合法
                        if (nums[a] != forbidden[b] && nums[b] != forbidden[a]) {
                            hasValidPair = true
                            break
                        }
                    }
                    if (hasValidPair) break
                }
                // 检查最后剩余的冲突位置是否能和已配对的位置二次交换
                if (!hasValidPair) return -1
            }

            // 合法的全冲突场景：交换次数 = 向上取整(m/2)
            return (m + 1) / 2
        }

        // 3. 处理【非全冲突场景】（有可用非冲突位置）
        val badUsed = BooleanArray(badIndices.size)
        val goodUsed = BooleanArray(goodIndices.size)
        var swapCount = 0

        for (i in badIndices.indices) {
            if (badUsed[i]) continue
            val currentBad = badIndices[i]
            var found = false

            // 3.1 优先用非冲突位置交换（解决单个冲突）
            for (j in goodIndices.indices) {
                if (goodUsed[j]) continue
                val currentGood = goodIndices[j]
                // 交换后两个位置都合法
                if (nums[currentBad] != forbidden[currentGood] && nums[currentGood] != forbidden[currentBad]) {
                    swapCount++
                    badUsed[i] = true
                    goodUsed[j] = true
                    found = true
                    break
                }
            }
            if (found) continue

            // 3.2 找未处理的冲突位置配对（过滤数值相同的无效配对）
            for (j in badIndices.indices) {
                if (i == j || badUsed[j]) continue
                val pairBad = badIndices[j]
                // 排除数值相同的无效交换
                if (nums[currentBad] == nums[pairBad] && forbidden[currentBad] == forbidden[pairBad]) {
                    continue
                }
                // 交换后两个位置都合法
                if (nums[currentBad] != forbidden[pairBad] && nums[pairBad] != forbidden[currentBad]) {
                    swapCount++
                    badUsed[i] = true
                    badUsed[j] = true
                    found = true
                    break
                }
            }

            // 3.3 无可用交换方式，返回-1
            if (!found) return -1
        }

        return swapCount

    }
}