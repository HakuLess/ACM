package leetcode.contest.c324

import utils.getPrime
import utils.print

fun main() {
    val s = SolutionB()
    s.smallestValue(15).print()
    s.smallestValue(4).print()
}

class SolutionB {
    fun smallestValue(n: Int): Int {
        val seen = HashSet<Int>()
        val primes = getPrime(n)
        fun dfs(c: Int): Int {
            if (c in seen) return c
            seen.add(c)
            if (primes[c] == 1) return c
            var ans = 0
            var cur = c / primes[c]
            ans += primes[c]
            while (primes[cur] != 1) {
                ans += primes[cur]
                cur /= primes[cur]
            }
            ans += cur
            return dfs(ans).also {
                println(it)
            }
        }
        return dfs(n)
    }
}