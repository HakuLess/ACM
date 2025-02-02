package leetcode.contest.c435

import utils.gcd
import utils.lcm
import utils.permute
import utils.print
import java.util.*
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    // 1
//    s.minimumIncrements(intArrayOf(1, 2, 3), intArrayOf(4)).print()
//    // 2
//    s.minimumIncrements(intArrayOf(8, 4), intArrayOf(10, 5)).print()
//
//    // 3
//    s.minimumIncrements(intArrayOf(8, 10, 9), intArrayOf(10, 6, 6)).print()

    // 8
    s.minimumIncrements(intArrayOf(10, 4, 5, 10), intArrayOf(6, 8, 7, 3)).print()
}

class SolutionC {
    fun minimumIncrements(nums: IntArray, target: IntArray): Int {


        fun helper(target: TreeSet<Int>): Int {

            val ts = TreeSet<Int>()
            ts.addAll(nums.toHashSet())

//            println("enter ${target.joinToString()}")

            var ans = Int.MAX_VALUE

            target.toIntArray().permute().forEach {

                var tmp = 0

                it.forEach {
                    var min = Int.MAX_VALUE
                    var cur = it
                    var toRemove = 0

                    while (cur <= 10000) {

                        ts.floor(cur)?.let {
                            if (cur - it < min) {
                                min = cur - it
                                toRemove = it
                            }
                        }

                        cur += it
                    }

                    ts.floor(cur)?.let {
                        if (cur - it < min) {
                            min = cur - it
                            toRemove = it
                        }
                    }

                    // 被其他倍数消费掉，不可改变
                    ts.remove(toRemove)

//                    if (min == Int.MAX_VALUE) {
//                        return@forEach
//                    }
//                    println("find $it with $min   pre ans is $ans")
                    tmp += min
//                    println("ans is $ans")
                }
                if (tmp > 0) {
                    ans = minOf(ans, tmp)
                }
            }

            return ans
        }

        // 最多4个数字 各种组合最小公倍数
        val n = target.size
        val targetSet = TreeSet<Int>()

        var ans = Int.MAX_VALUE
        when (n) {
            1 -> {
                targetSet.addAll(target.toHashSet())
                ans = helper(targetSet)
            }
            2 -> {
                val (a, b) = target
                targetSet.addAll(target.toHashSet())
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, b))
                ans = minOf(ans, helper(targetSet))
            }

            3 -> {
                val (a, b, c) = target
                targetSet.addAll(target.toHashSet())
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, b))
                targetSet.add(c)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, c))
                targetSet.add(b)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(b, c))
                targetSet.add(a)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(c, lcm(a, b)))
                ans = minOf(ans, helper(targetSet))
            }

            4 -> {
                val (a, b, c, d) = target
                targetSet.addAll(target.toHashSet())
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, b))
                targetSet.add(c)
                targetSet.add(d)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, b))
                targetSet.add(lcm(c, d))
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, c))
                targetSet.add(b)
                targetSet.add(d)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(a, c))
                targetSet.add(lcm(b, d))
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(b, c))
                targetSet.add(a)
                targetSet.add(d)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(b, c))
                targetSet.add(lcm(a, d))
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(c, lcm(a, b)))
                targetSet.add(d)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(d, lcm(a, b)))
                targetSet.add(c)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(d, lcm(a, c)))
                targetSet.add(b)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(d, lcm(b, c)))
                targetSet.add(a)
                ans = minOf(ans, helper(targetSet))

                targetSet.clear()
                targetSet.add(lcm(lcm(d, lcm(b, c)), a))
                ans = minOf(ans, helper(targetSet))
            }
        }

        return ans
    }
}