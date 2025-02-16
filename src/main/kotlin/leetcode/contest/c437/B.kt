package leetcode.contest.c437

import utils.print
import java.util.ArrayDeque

fun main() {
    val s = SolutionB()
    s.maxWeight(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8)).print()
    // 14
    s.maxWeight(intArrayOf(5, 2, 2, 4, 3, 3, 1, 3, 2, 5, 4, 2)).print()
}

class SolutionB {
    fun maxWeight(pizzas: IntArray): Long {
        pizzas.sortDescending()

        pizzas.print()
        val deque = ArrayDeque<Int>()
        deque.addAll(pizzas.toTypedArray())

        var ans = 0L
        var a = pizzas.size / 4
        var c = 0
        while (deque.isNotEmpty()) {
            if (c * 2 < a) {
                ans += deque.pollFirst()
//                println("a $ans")
                repeat(3) {
                    deque.pollLast()
                }
            } else {
                deque.pollFirst()
                ans += deque.pollFirst()
//                println("!a $ans")
                repeat(2) {
                    deque.pollLast()
                }
            }
            c++
        }
        return ans
    }
}