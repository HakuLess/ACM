package leetcode.contest.b60

import utils.gcd
import utils.print
import utils.quickPower

fun main() {
    val s = Solution5849()
    s.numberOfGoodSubsets(intArrayOf(1, 2, 3, 4)).print()
    s.numberOfGoodSubsets(intArrayOf(4, 2, 3, 15)).print()
}

class Solution5849 {
    fun numberOfGoodSubsets(nums: IntArray): Int {
        val arr = IntArray(32)
        val mod = 1000000007L
        val black = intArrayOf(4, 8, 12, 16, 20, 24, 28, 9, 18, 27, 25)
        nums.forEach {
            if (it !in black)
                arr[it]++
        }
        val ans = HashSet<String>()
        fun select(cur: Int, list: ArrayList<Int> = arrayListOf()) {
            if (cur == 1) {
                ans.add(list.joinToString(" "))
                return
            }
            for (i in cur - 1 downTo 1) {
                if ((arr[i] != 0 || i == 1) && list.all { gcd(it, i) == 1 }) {
                    list.add(i)
                    select(i, list)
                    list.remove(i)
                }
            }
        }
        for (i in 30 downTo 1) {
            if (arr[i] != 0) {
                select(i, arrayListOf(i))
            }
        }
        var res = 0L
        ans.forEach {
            if (it.isNotEmpty()) {
                var temp = 1L
                it.split(" ").forEach {
                    temp = if (it == "1") {
                        (temp * (quickPower(2L, arr[1].toLong()))) % mod
                    } else {
                        (temp * arr[it.toInt()]) % mod
                    }
                }
                res += temp
            }
        }
        if (arr[1] != 0) {
            res -= quickPower(2L, arr[1].toLong())
        }
        res += mod
        return (res % mod).toInt()
    }
}