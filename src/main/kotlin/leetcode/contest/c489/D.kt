package leetcode.contest.c489

import utils.Trie
import utils.insertInt
import utils.maxXor
import utils.print
import utils.removeInt

fun main() {
    val s = SolutionD()
    // 7
    s.maxXor(intArrayOf(5, 4, 5, 6), 2).print()
    // 6
    s.maxXor(intArrayOf(5, 4, 5, 6), 1).print()
}

class SolutionD {
    fun maxXor(nums: IntArray, k: Int): Int {

        val n = nums.size

        // 前缀异或
        val px = IntArray(n + 1)
        for (i in 0 until n) {
            px[i + 1] = px[i] xor nums[i]
        }

        // 单调队列：存下标
        val minQ = ArrayDeque<Int>()
        val maxQ = ArrayDeque<Int>()

        val trie = Trie<Int>()
        var ans = 0
        var l = 0

        // 初始放入 px[0]
        trie.insertInt(px[0])

        for (r in 0 until n) {
            val v = nums[r]

            // 维护最小值队列
            while (minQ.isNotEmpty() && nums[minQ.last()] >= v)
                minQ.removeLast()
            minQ.addLast(r)

            // 维护最大值队列
            while (maxQ.isNotEmpty() && nums[maxQ.last()] <= v)
                maxQ.removeLast()
            maxQ.addLast(r)

            // 收缩左端，保证 max - min <= k
            while (nums[maxQ.first()] - nums[minQ.first()] > k) {
                trie.removeInt(px[l])
                if (minQ.first() == l) minQ.removeFirst()
                if (maxQ.first() == l) maxQ.removeFirst()
                l++
            }

            // 查询最大 XOR
            // 分段 XOR 等价为 0..A XOR 0..B == A..B
            val cur = trie.maxXor(px[r + 1])
            if (cur != -1) ans = maxOf(ans, cur)

            // 插入当前前缀
            trie.insertInt(px[r + 1])
        }

        return ans
    }
}