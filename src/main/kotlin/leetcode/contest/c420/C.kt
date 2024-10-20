package leetcode.contest.c420

import utils.getPrime
import utils.print

fun main() {
    val s = SolutionC()
    s.minOperations(intArrayOf(105, 11)).print()
}

class SolutionC {
    fun minOperations(nums: IntArray): Int {
        val primeList = getPrime(nums.maxOrNull()!!)
//        primeList.print()
        var ans = 0

        // 从倒数第二个元素开始，往前遍历
        for (i in nums.size - 2 downTo 0) {
            // 当前数字大于下一个数字
            while (nums[i] > nums[i + 1]) {
                val minFactor = primeList[nums[i]]
//                println("minFactor is $minFactor")

                // 如果最大真因数为1，说明无法再继续操作
                if (minFactor == 1 ) return -1

                // 将当前数字除以最大真因数
                nums[i] = minFactor
                ans++
            }

            // 如果当前数字还是大于下一个，返回 -1
            if (nums[i] > nums[i + 1]) return -1
        }

        return ans
    }
}