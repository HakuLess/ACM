package leetcode.contest.c368

import utils.print

fun main() {
    val s = SolutionC()
    s.minGroupsForValidAssignment(intArrayOf(10, 10, 10, 3, 1, 1)).print()
//    s.minGroupsForValidAssignment(intArrayOf(1, 1, 1, 3, 1, 2, 2, 2, 3)).print()
//    s.minGroupsForValidAssignment(intArrayOf(1, 1, 3, 3, 1, 1, 2, 2, 3, 1, 3, 2)).print()
}

class SolutionC {
    fun minGroupsForValidAssignment(nums: IntArray): Int {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            val item = nums[i]
            map[item] = map.getOrDefault(item, 0) + 1
        }
        val l = ArrayList<Int>()
        map.forEach { k, v ->
            l.add(v)
        }
        val max = l.maxOrNull()!!
        var ans = nums.size

        // a 能否拆为 多个 b 与 b - 1 的和
        fun check(a: Int, b: Int): Int {
            val c = a % (b - 1)
            val d = a / (b - 1)
            if (d >= c) {
                return (a + b - 1) / b
            } else {
                return -1
            }
        }

        l.joinToString().print()
        for (i in max + 1 downTo 2) {
            print("$i:  ")
            println(l.map { "${check(it, i)}" })

            if (l.all { check(it, i) != -1 }) {
                var tmp = 0
                l.forEach {
                    tmp += check(it, i)
                }
                ans = minOf(ans, tmp)
            }
        }
        return ans
    }
}