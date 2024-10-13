package leetcode.contest.c419

import utils.print
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    // [6,10,12]
//    s.findXSum(intArrayOf(1, 1, 2, 2, 3, 4, 2, 3), 6, 2).print()
    // [11,15,15,15,12]
//    s.findXSum(intArrayOf(3, 8, 7, 8, 7, 5), 2, 2).print()
    // [18,16]
//    s.findXSum(intArrayOf(5, 5, 3, 5, 3), 4, 4).print()
    // [9,9,8,5]
//    s.findXSum(intArrayOf(5, 9, 8, 5, 1), 2, 1).print()
    // [12,10,10]
    s.findXSum(intArrayOf(6, 4, 5, 5, 1, 3, 6, 3, 4), 7, 1).print()
}

class SolutionD {
    fun findXSum(nums: IntArray, k: Int, x: Int): LongArray {
        val res = LongArray(nums.size - k + 1)
        val count = TopCount(x)

        for (i in nums.indices) {
            count.addNum(nums[i])
            if (i >= k - 1) {
                res[i - k + 1] = count.getResult()
                count.removeNum(nums[i - k + 1])
            }
        }
        return res
    }
}

class TopCount(val x: Int) {
    val valid: TreeSet<IntArray> = TreeSet(compareBy({ it[1] }, { it[0] }))
    val buffer: TreeSet<IntArray> = TreeSet(compareBy({ it[1] }, { it[0] }))
    val count = HashMap<Int, Int>()
    var res: Long = 0

    fun addNum(num: Int) {
        val old = count.getOrDefault(num, 0)
        count[num] = old + 1

        if (old > 0) {
            val key = intArrayOf(num, old)
            if (buffer.contains(key)) {
                buffer.remove(key)
            } else if (valid.contains(key)) {
                // 历史值在计算范围内，直接增加
                valid.remove(key)
                res += num
                key[1] += 1
                valid.add(key)
                return
            }
        }

        // 优先级最低数据拿出，重新导入
        if (valid.isNotEmpty()) {
            val tmp = valid.pollFirst()
            res -= (tmp[0].toLong() * tmp[1])
            buffer.add(tmp)
        }

        buffer.add(intArrayOf(num, old + 1))
        while (valid.size < x && buffer.isNotEmpty()) {
            val tmp = buffer.pollLast()
            res += (tmp[0].toLong() * tmp[1])
            valid.add(tmp)
        }
    }

    fun removeNum(num: Int) {
        val old = count[num] ?: return
        if (old == 1) {
            count.remove(num)
        } else {
            count[num] = old - 1
        }

        val key = intArrayOf(num, old)
        if (buffer.contains(key)) {
            buffer.remove(key)
            key[1] -= 1
            if (key[1] > 0) {
                buffer.add(key)
            }
            return
        } else if (valid.contains(key)) {
            valid.remove(key)
            res -= (key[0].toLong() * key[1])
        }

        if (old - 1 > 0) {
            key[1] -= 1
            buffer.add(key)
        }

        while (valid.size < x && buffer.isNotEmpty()) {
            val tmp = buffer.pollLast()
            res += (tmp[0].toLong() * tmp[1])
            valid.add(tmp)
        }
    }

    fun getResult(): Long {
        return res
    }
}