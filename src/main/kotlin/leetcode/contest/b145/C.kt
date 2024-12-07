package leetcode.contest.b145

import java.util.*

class SolutionC {
    fun minOperations(n: Int, m: Int): Int {

        fun isPrime(num: Int): Boolean {
            if (num <= 1) return false
            for (i in 2..Math.sqrt(num.toDouble()).toInt()) {
                if (num % i == 0) return false
            }
            return true
        }

        fun bfs(start: Int, target: Int): Int {
            if (isPrime(start) || isPrime(target)) return -1

            val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
            queue.add(Triple(start, 0, start))
            val visited = mutableSetOf<Int>()
            visited.add(start)

            while (queue.isNotEmpty()) {
                val (current, cost, total) = queue.poll()

                if (current == target) return total

                val digits = current.toString().map { it - '0' }.toMutableList()
                for (i in digits.indices) {
                    val original = digits[i]

                    if (original < 9) {
                        digits[i]++
                        val next = digits.joinToString("").toInt()
                        if (next !in visited && !isPrime(next)) {
                            visited.add(next)
                            queue.add(Triple(next, cost + 1, total + next))
                        }
                        digits[i] = original
                    }

                    if (original > 0) {
                        digits[i]--
                        val next = digits.joinToString("").toInt()
                        if (next !in visited && !isPrime(next)) {
                            visited.add(next)
                            queue.add(Triple(next, cost + 1, total + next))
                        }
                        digits[i] = original
                    }
                }
            }

            return -1
        }

        return bfs(n, m)
    }
}