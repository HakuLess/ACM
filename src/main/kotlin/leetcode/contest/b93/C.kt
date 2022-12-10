package leetcode.contest.b93

import utils.biMin
import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.maxJump(intArrayOf(0, 2, 5, 6, 7)).print()
}

class SolutionC {
    fun maxJump(stones: IntArray): Int {

        val target = stones.last()
        return biMin(1L, stones.maxOrNull()!!.toLong()) {
//            println("check $it =============")
            val ts = TreeSet<Int>()
            ts.addAll(stones.toList())

            var cur = 0
            while (cur < target) {
//                println("in $cur with $ts")
                ts.floor(cur + it.toInt()).let {
                    if (it == null) {
                        return@biMin false
                    } else {
                        cur = it
                        ts.remove(cur)
                    }
                }
            }
            cur = target
            while (cur > 0) {
                ts.ceiling(cur - it.toInt()).let {
//                    println("out $cur with $ts  to $it")
                    if (it == null) {
                        return@biMin false
                    } else {
                        cur = it
                        ts.remove(cur)
                    }
                }
            }
            cur <= 0
        }.toInt()
    }
}