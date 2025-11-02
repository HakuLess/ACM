package leetcode.contest.c474

import utils.biMin
import utils.gcd
import utils.print

fun main() {
    val s = SolutionC()
    s.minimumTime(intArrayOf(1, 3), intArrayOf(2, 2)).print()
}

class SolutionC {
    fun minimumTime(d: IntArray, r: IntArray): Long {
        fun check(t: Long): Boolean {
            val d1 = d[0].toLong()
            val d2 = d[1].toLong()
            val r1 = r[0].toLong()
            val r2 = r[1].toLong()

            // 每台无人机各自能工作的小时数（排除自身充电小时）
            val deliver1 = t - t / r1
            val deliver2 = t - t / r2

            // 小心 lcm 可能溢出，先用 gcd
            val g = gcd(r1, r2)
            val l = (r1 / g) * r2  // 用 Long 保证不溢出（r_i <= 3e4，所以安全）

            // 在 bothDown 小时内两台都在充电，这些小时完全不可用
            val bothDown = t / l
            val usable = t - bothDown

            // 条件：每台能完成自己所需 && 总可用小时够两台总和
            return deliver1 >= d1 && deliver2 >= d2 && usable >= d1 + d2
        }

        return biMin(1L, 10_000_000_000L) {
            check(it)
        }
    }
}