package leetcode.contest.c374

import utils.print

fun main() {
    val s = SolutionB()
    // 2
//    s.minimumAddedCoins(intArrayOf(1, 4, 10), 19).print()
    // 1
//    s.minimumAddedCoins(intArrayOf(1, 4, 10, 5, 7, 19), 19).print()
    // 3
//    s.minimumAddedCoins(intArrayOf(1, 1, 1), 19).print()
    // 4
    s.minimumAddedCoins(intArrayOf(15, 1, 12), 43).print()
}

// 构造连续值
class SolutionB {
    fun minimumAddedCoins(coins: IntArray, target: Int): Int {
        coins.sort()
        var sum = 0
        var i = 0
        var ans = 0

        while (sum < target) {
            if (i in coins.indices && coins[i] <= sum + 1) {
                sum += coins[i]
                i++
            } else {
                sum += sum + 1
                ans++
            }
        }

        return ans
    }
}