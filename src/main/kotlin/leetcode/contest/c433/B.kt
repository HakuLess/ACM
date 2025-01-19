package leetcode.contest.c433

import utils.originC
import utils.print

fun main() {
    val s = SolutionB()
    // 24
    s.minMaxSums(intArrayOf(1, 2, 3), 2).print()
    // 22
    s.minMaxSums(intArrayOf(5, 0, 6), 1).print()
    // 12
    s.minMaxSums(intArrayOf(1, 1, 1), 2).print()
}

class SolutionB {
    fun minMaxSums(nums: IntArray, k: Int): Int {
        val mod = 1000000007L
        var ans = 0L
        nums.sort()

        var left = 0
        var right = nums.size

        for (i in nums.indices) {

            right--

            for (limit in 1..minOf(left + 1, k)) {
                ans += originC(left, limit - 1) * nums[i]
                ans %= mod

//                println("${nums[i]} 最大值贡献 ${originC(left, limit - 1)} 次")
            }

            for (limit in 1..minOf(right + 1, k)) {
                ans += originC(right, limit - 1) * nums[i]
                ans %= mod

//                println("${nums[i]} 最小值贡献 ${originC(right, limit - 1)} 次")
            }

            left++
        }

        return (ans % mod).toInt()
    }

//    fun minMaxSums(nums: IntArray, k: Int): Int {
//        val mod = 1000000007L
//        val count = IntArray(101)
//
//        for (num in nums) {
//            count[num]++
//        }
//
//        var ans = 0L
//
//        // <= 当前值
//        var lessCnt = 0
//
//        // >= 当前值
//        var moreCnt = 0
//
//        // 最大值贡献
//        for (i in 1..100) {
//            lessCnt += count[i]
//            if (count[i] != 0) {
//                for (limit in 1..minOf(k, lessCnt)) {
//                    ans += originC(lessCnt - 1, limit - 1) * i
//                    ans %= mod
//
////                    println("$i 最大值贡献 ${count[i]} * ${originC(lessCnt - 1, limit - 1)} 次  $lessCnt with ${limit - 1}")
//                }
//            }
//        }
//
//        for (i in 100 downTo 1) {
//            moreCnt += count[i]
//            if (count[i] != 0) {
//                for (limit in 1..minOf(k, moreCnt)) {
//                    ans += originC(moreCnt - 1, limit - 1) * i
//                    ans %= mod
//
////                    println("$i 最小值贡献 ${count[i]} * ${originC(moreCnt - 1, limit - 1)} 次  $moreCnt with ${limit - 1}")
//                }
//            }
//        }
//
//        return (ans % mod).toInt()
//    }
}