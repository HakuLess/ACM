package leetcode.contest.c338

import utils.getPrimes
import utils.print

fun main() {
    val s = SolutionB()
//    s.primeSubOperation(intArrayOf(4, 9, 6, 10)).print()
    s.primeSubOperation(intArrayOf(8, 19, 3, 4, 9)).print()
}

class SolutionB {
    fun primeSubOperation(nums: IntArray): Boolean {
        val primes = getPrimes(1000)
//        primes.joinToString().print()

        var pre = 0
        for (i in nums.indices) {
//            println("enter $i")
            if (i > 0) pre = nums[i - 1]
            for (j in primes.indices.reversed()) {
                if (nums[i] - primes[j] > pre) {
                    nums[i] -= primes[j]

//                    println("$i: $pre")
                    break
                }
            }
//            println("$i cmp ${nums[i]} with ${nums[i - 1]}")
            if (i > 0 && nums[i] <= nums[i - 1]) return false
        }

        return true
    }
}