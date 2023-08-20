package leetcode.contest.c359

import utils.print

fun main() {
    val s = SolutionD()
    s.longestEqualSubarray(listOf(1, 2, 1), 0).print()
}

class SolutionD {
    fun longestEqualSubarray(nums: List<Int>, k: Int): Int {
        var ans = 0
        // 第一个位置 总和 其余位置
        val map = HashMap<Int, Item>()
        for (i in nums.indices) {
            val key = nums[i]
            if (key !in map) {
                map[key] = Item(i, 0, arrayListOf(i))
            } else {
                val item = map[key]!!
                item.sum += i - item.l.last() - 1
                item.l.add(i)

//                println("key $key with $i: $item")
                while (item.sum > k) {
//                    println("$i: sum ${item.sum}")
                    item.l.removeAt(0)
                    item.sum -= item.l.first() - item.fPos - 1
                    item.fPos = item.l.first()
                }
            }
            ans = maxOf(ans, map[key]!!.l.size)
        }
        return ans
    }

    data class Item(var fPos: Int, var sum: Int, var l: ArrayList<Int>)
}