package leetcode.contest.c422

import utils.permute
import utils.permuteUnique
import utils.print

fun main() {
    val s = SolutionD()
    s.countBalancedPermutations("123").print()
}

class SolutionD {
    fun countBalancedPermutations(num: String): Int {

//        val arr = num.toCharArray().map { ((it - '0') % 2) }.toIntArray()
//        val a = arr.count { it % 2 == 0 }
//        val b = arr.count { it % 2 != 0 }
//
        val mod = 1_000_000_007L
//
//        var tmp = 1L
//        for (i in 1..a) {
//            tmp *= a
//            tmp %= mod
//        }
//        for (i in 1..b) {
//            tmp *= b
//            tmp %= mod
//        }
//
//        println("tmp is $tmp")

        var ans = 0L
        num.toCharArray().map { it - '0' }.toIntArray().permuteUnique().forEach {
            var aSum = 0
            var bSum = 0
            for (i in it.indices) {
                if (i % 2 == 0) {
                    aSum += it[i]
                } else {
                    bSum += it[i]
                }
            }

//            println("${it.joinToString()}  for  $aSum == $bSum")
            if (aSum == bSum) {
                ans ++
                ans %= mod
            }
        }

        return (ans % mod).toInt()
    }
}