package leetcode.other.ubiquant2022

import utils.lcm
import utils.print

fun main() {
    val s = SolutionC()
    s.minOperations(intArrayOf(50, 75, 100)).print()
}

class SolutionC {
    fun minOperations(numbers: IntArray): Int {
        var lcm = 1L
        for (i in numbers.indices) {
            lcm = lcm(lcm, numbers[i].toLong())
        }
        var ans = 0
        for (i in numbers.indices) {
            var step = lcm / numbers[i]
            while (step % 2 == 0L) {
                ans++
                step /= 2
            }
            while (step % 3 == 0L) {
                ans++
                step /= 3
            }
            if (step != 1L) return -1
        }
        return ans
    }
}