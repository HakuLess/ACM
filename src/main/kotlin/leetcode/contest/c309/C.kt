package leetcode.contest.c309

import utils.print
import utils.printInt
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val s = SolutionC()
    s.longestNiceSubarray(intArrayOf(1, 3, 8, 48, 10)).print()
}

class SolutionC {
    fun longestNiceSubarray(nums: IntArray): Int {
        var l = 0
        var r = 0
        val cur0 = HashMap<Int, Int>()
        val cur1 = HashMap<Int, Int>()
        var ans = 0
        while (r in nums.indices) {
//            nums[r].toString(2).print()
            // 最右侧加入
            nums[r].toString(2).reversed().mapIndexed { index, c ->
                if (c == '0')
                    cur0[index] = cur0.getOrDefault(index, 0) + 1
                else
                    cur1[index] = cur1.getOrDefault(index, 0) + 1
            }
            var k = r - l + 1
            // 最左侧排出
//            println("in ${cur1}")
            while (cur1.values.any { it > 1 }) {
                if (l !in nums.indices) {
                    return ans
                }

                nums[l].toString(2).reversed().mapIndexed { index, c ->
                    if (c == '0')
                        cur0[index] = cur0.getOrDefault(index, 0) - 1
                    else
                        cur1[index] = cur1.getOrDefault(index, 0) - 1
                }
                l++
                k = r - l + 1
            }
//            println("out ${cur1}")
            ans = maxOf(ans, k)
            r++
        }
        return ans
    }
}