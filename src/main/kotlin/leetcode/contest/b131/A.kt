package leetcode.contest.b131

import utils.print

fun main() {
    val s = SolutionA()
    s.duplicateNumbersXOR(intArrayOf(1, 2, 2, 1)).print()
}

class SolutionA {
    fun duplicateNumbersXOR(nums: IntArray): Int {
        var a = 0
        nums.filter { c ->
            nums.count { it == c } == 2
        }.toHashSet().toTypedArray().forEach {
            a = a xor it
        }
        return a
    }
}