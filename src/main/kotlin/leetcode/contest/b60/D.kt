package leetcode.contest.b60

import utils.gcd
import utils.print
import utils.quickPower

fun main() {
    val s = Solution5849()
    s.numberOfGoodSubsets(intArrayOf(1, 2, 3, 4)).print()
}

class Solution5849 {
    fun numberOfGoodSubsets(nums: IntArray): Int {
        val arr = LongArray(32)
        val mod = 1000000007L
        nums.forEach {
            arr[it]++
        }
        val ans = HashSet<String>()
        val black = intArrayOf(4, 8, 12, 16, 20, 24, 28, 9, 18, 27, 25)
        fun select(cur: Int, list: ArrayList<Int> = arrayListOf()) {
            if (cur == 1) {
                ans.add(list.joinToString(" "))
                list.remove(1)
                ans.add(list.joinToString(" "))
                list.add(1)
                return
            }
            for (i in cur - 1 downTo 1) {
                if (i !in black && (arr[i] != 0L || i == 1) && list.all { gcd(it, i) == 1 }) {
                    list.add(i)
                    select(i, list)
                    list.remove(i)
                }
            }
        }
        for (i in 30 downTo 1) {
            if (arr[i] != 0L && i !in black) {
                select(i, arrayListOf(i))
            }
        }
        var res = 0L
        ans.forEach {
            if (it.isNotEmpty()) {
                var temp = 1L
                it.split(" ").forEach {
                    if (it == "1") {
                        temp = (temp * (quickPower(2L, arr[1]) - 1L)) % mod
                    } else {
                        temp = (temp * arr[it.toInt()]) % mod
                    }
                }
                res += temp
            }
        }
        res -= quickPower(2L, arr[1]) - 1L
        res += mod
        return (res % mod).toInt()
    }
}