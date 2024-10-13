package leetcode.contest.c419

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.findXSum(intArrayOf(1, 1, 2, 2, 3, 4, 2, 3), 6, 2).print()
}

class SolutionD {
    fun findXSum(nums: IntArray, k: Int, x: Int): LongArray {
        val map = mutableMapOf<Int, Long>()
        val ans = ArrayList<Long>()

        val pqRight = PriorityQueue<Pair<Int, Long>>(compareBy({ -it.second }, { -it.first }))
        // 计算和的堆
        val pqLeft = PriorityQueue<Pair<Int, Long>>(compareBy({ it.first }, { it.second }))

        var curSum = 0L

        fun updatePQ() {
            // 将候选塞入左侧计算和
            while (pqLeft.size < x && pqRight.isNotEmpty()) {
                pqLeft.offer(pqRight.poll())
            }

            if (pqRight.isNotEmpty()) {
                val r = pqRight.peek()
                val l = pqLeft.peek()

                // 需要交换
                if ((l.second == r.second && l.first < r.first) || (l.second < r.second)) {
                    pqLeft.offer(pqRight.poll())
                    pqRight.offer(pqLeft.poll())
                }
            }
        }

        fun initSum() {
            pqLeft.forEach {
                curSum += it.first * it.second
            }
        }

        for (i in nums.indices) {

            // 每次先将数据加入到小堆中
            pqRight.remove(Pair(nums[i], map[nums[i]]))
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
            pqRight.offer(Pair(nums[i], map[nums[i]]!!))

            if (i == k - 1) {
                initSum()
            }

            if (i >= k - 1) {
                ans.add(curSum)

                pqRight.remove(Pair(nums[i - k + 1], map[nums[i - k + 1]]))
                map[nums[i - k + 1]] = map[nums[i - k + 1]]!! - 1
                pqRight.offer(Pair(nums[i - k + 1], map[nums[i - k + 1]]!!))
            }
        }


        return ans.toLongArray()
    }
}