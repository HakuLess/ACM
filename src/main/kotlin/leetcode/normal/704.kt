package leetcode.normal

import utils.biFirstIndexOf
import utils.print

fun main() {
    val s = Solution704()
    s.search(intArrayOf(-1, 0, 5), -1).print()
}

class Solution704 {
    fun search(nums: IntArray, target: Int): Int {
        return nums.binarySearch(target).let {
            if (it < 0) -1 else it
        }
    }
}