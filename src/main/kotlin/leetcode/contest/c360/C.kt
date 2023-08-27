package leetcode.contest.c360

import utils.print
import utils.printInt
import java.util.*

fun main() {
    val s = SolutionC()
//    s.minOperations(listOf(1, 2, 8), 7).print()
    // 2
    s.minOperations(listOf(1, 32, 1, 2), 12).print()

//    s.minOperations(listOf(1, 1, 1), 3).print()
}

class SolutionC {
    fun minOperations(nums: List<Int>, target: Int): Int {
        val map = TreeMap<Int, Int>()
        nums.map {
            it.toString(2).length
        }.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
        }

        var ans = 0
        target.toString(2).reversed().forEachIndexed { index, c ->

            val p = index + 1

//            println("$p with $c")
//            map.printInt()

            if (c == '1') {
                val ceil = map.ceilingKey(p)
                if (ceil == null) return -1

                var tmp = ceil
                while (tmp != p) {
                    map[tmp] = map[tmp]!! - 1
                    if (map[tmp]!! == 0) {
                        map.remove(tmp)
                    }
                    tmp--
                    map[tmp] = map.getOrDefault(tmp, 0) + 2
                }

                map[tmp] = map.getOrDefault(tmp, 0) - 1
                if (map.getOrDefault(tmp, 0) == 0) {
                    map.remove(tmp)
                }

                ans += ceil - p
            }

            map[p + 1] = map.getOrDefault(p + 1, 0) + map.getOrDefault(p, 0) / 2
            if (map[p + 1] == 0) {
                map.remove(p + 1)
            }
        }
        return ans
    }
}