package leetcode.contest.c295

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    // 1
    s.totalSteps(intArrayOf(10, 6, 5, 10, 15)).print()
    // 3
    s.totalSteps(intArrayOf(5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11)).print()
}

// not finished
class SolutionC {

    fun totalSteps(nums: IntArray): Int {
        val eat = BooleanArray(nums.size)
        val queue: Queue<Triple<Int, Int, Int>> = LinkedList<Triple<Int, Int, Int>>()
        for (i in nums.indices) {
            queue.offer(Triple(i, nums[i], i))
        }
        var step = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (i in 0 until size) {
                val item = queue.poll()
                var j = item.first + 1
                while (j in nums.indices) {
                    if (eat[j]) {
                        j++
                    } else {
                        if (nums[j] < item.second) {
                            eat[j] = true
                            if (!eat[item.third]) {
                                queue.offer(Triple(j, item.second, item.third))
                            }
                        }
                        break
                    }
                }
            }
        }
        return step
    }
}