package leetcode.normal

import utils.MultiSet
import utils.print
import utils.toGrid

fun main() {
    val s = Solution632()
//    s.smallestRange("[[4,10,15,24,26],[0,9,12,20],[5,18,22,30]]"
//                        .toGrid().toList().map { it.toTypedArray().toList() }).print()

    s.smallestRange("[[1,2,3],[1,2,3],[1,2,3]]"
                        .toGrid().toList().map { it.toTypedArray().toList() }).print()
}

class Solution632 {

    class Item(var index: Int, var value: Int, var subIndex: Int) : Comparable<Item> {
        override fun compareTo(other: Item): Int {
            if (value > other.value) return 1
            if (value < other.value) return -1
            if (index > other.index) return 1
            if (index < other.index) return -1
            return 0
        }
    }

    fun smallestRange(nums: List<List<Int>>): IntArray {
        val multiSet = MultiSet<Item>()
        for (i in nums.indices) {
            multiSet.add(Item(index = i, value = nums[i][0], subIndex = 1))
        }
        var ans = Pair(Int.MIN_VALUE / 4, Int.MAX_VALUE / 4)
        var cnt = 0
        val sum = nums.sumBy { it.size }
        var left = Int.MAX_VALUE
        var right = Int.MIN_VALUE
        while (cnt != sum) {
            println("${multiSet.min.value} ${multiSet.max.value}")
            left = multiSet.min.value
            right = multiSet.max.value
            if (right - left < ans.second - ans.first) {
                ans = Pair(left, right)
            }
            val item = multiSet.popLeft()
            if (item.subIndex !in nums[item.index].indices) {
                return intArrayOf(ans.first, ans.second)
            }
            multiSet.add(Item(item.index, nums[item.index][item.subIndex], item.subIndex + 1))
            cnt++
        }
        return intArrayOf(ans.first, ans.second)
    }
}