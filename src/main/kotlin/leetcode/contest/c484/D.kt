package leetcode.contest.c484

import utils.print

fun main() {
    val s = SolutionD()
    // 6
    s.maximumAND(intArrayOf(3, 1, 2), 8, 2).print()
    // 4
    s.maximumAND(intArrayOf(1, 2, 8, 4), 7, 3).print()
    // 2
    s.maximumAND(intArrayOf(1, 1), 3, 2).print()
    // 23
    s.maximumAND(intArrayOf(17, 20), 3, 1).print()
}

class SolutionD {
    fun maximumAND(nums: IntArray, k: Int, m: Int): Int {

        var candidates = nums.map { it.toLong() to 0L }
        var ans = 0
        val kLong = k.toLong()

        // 从高位到低位尝试
        for (b in 30 downTo 0) {
            val bitVal = 1L shl b
            val mask = bitVal - 1

            val nextCandidates = ArrayList<Pair<Long, Long>>(candidates.size)
            // 纯代价列表，用于排序判断是否可行
            val costs = ArrayList<Long>(candidates.size)

            for ((currentVal, costSoFar) in candidates) {
                // 判断当前数字是否有第 b 位
                if ((currentVal and bitVal) != 0L) {
                    if (costSoFar <= kLong) {
                        nextCandidates.add(currentVal to costSoFar)
                        costs.add(costSoFar)
                    }
                } else {
                    // 没有该位：需要进位
                    // 代价 = (1 << b) - (当前数值的低位部分)
                    // 进位后，比 b 低的位会被清零
                    val needed = bitVal - (currentVal and mask)
                    val newCost = costSoFar + needed

                    if (newCost <= kLong) {
                        // 新数值 = 原数值 + needed
                        nextCandidates.add((currentVal + needed) to newCost)
                        costs.add(newCost)
                    }
                }
            }

            // 如果有效的候选人不足 m 个，这一位绝对不可能点亮
            if (costs.size < m) continue

            // 检查代价最小的 m 个的总代价是否 <= k
            costs.sort()

            var sumTopM = 0L
            for (i in 0 until m) {
                sumTopM += costs[i]
            }

            if (sumTopM <= kLong) {
                // 可以负担得起这一位
                ans = ans or (1 shl b)
                // 保留数组变化进入下一轮
                candidates = nextCandidates
            } else {
                // 失败 Cost不足
            }
        }

        return ans
    }
}