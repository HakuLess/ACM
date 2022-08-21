package leetcode.other.ubiquant2022

import utils.print
import java.util.*

fun main() {
    val s = SolutionD()
    s.chipGame(intArrayOf(1, 1), 2).print()
    s.chipGame(intArrayOf(1, 2), 4).print()
}

// todo not finished
class SolutionD {
    fun chipGame(nums: IntArray, kind: Int): Double {
        nums.sortDescending()
        val target = IntArray(kind)
        for (i in nums.indices) {
            target[i] = nums[i]
        }

        var ans = 0.0
        val queue: Queue<Pair<IntArray, Double>> = LinkedList<Pair<IntArray, Double>>()
        queue.offer(Pair(IntArray(kind), 1.0))
        while (queue.isNotEmpty()) {
            val size = queue.size
            var rate = 0.0
            var other = 0.0
            for (i in 0 until size) {
                val item = queue.poll()
                for (j in 0 until kind) {
                    item.first[j]++
                    if (target.indices.all { target[it] >= item.first.sortedDescending()[it] }) {
                        val next = IntArray(kind)
                        for (k in next.indices) {
                            next[k] = item.first[k]
                        }
                        queue.offer(Pair(next, item.second / kind))
                    } else if (target.sum() == item.first.sum() + 1) {

                    } else {
                        other += item.second / kind
                    }
                    item.first[j]--
                }
                rate += item.second
            }
            ans += 1.0 / rate
            println("step $rate $other")
        }
        return ans
    }
}