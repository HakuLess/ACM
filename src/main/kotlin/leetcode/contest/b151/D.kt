package leetcode.contest.b151

import utils.print
import utils.toBigInteger
import java.math.BigInteger

fun main() {
    val s = SolutionD()
    s.permute(4, 6).print()
    s.permute(29, 184032881360446).print()
}

class SolutionD {
    fun permute(n: Int, k: Long): IntArray {
        val sortedOdds = (1..n).filter { it % 2 == 1 }.sorted().toMutableList()
        val sortedEvens = (1..n).filter { it % 2 == 0 }.sorted().toMutableList()

        if (n == 0) return intArrayOf()

        fun factorialProduct(a: Int, b: Int, limit: BigInteger): BigInteger {
            var product = 1L.toBigInteger()
            for (i in 1..a) {
                product *= i.toBigInteger()
                if (product > limit) return limit + 1.toBigInteger()
            }
            var productB = 1L.toBigInteger()
            for (i in 1..b) {
                productB *= i.toBigInteger()
                if (productB > limit) return limit + 1.toBigInteger()
            }
            val total = product * productB
            return if (total > limit) limit + 1.toBigInteger() else total
        }

        var currentK = k.toBigInteger()
        val currentPrefix = mutableListOf<Int>()
        var currentOdds = sortedOdds.toMutableList()
        var currentEvens = sortedEvens.toMutableList()

        while (currentPrefix.size < n) {
            val pos = currentPrefix.size
            val candidates = if (pos == 0) {
                (currentOdds + currentEvens).sorted()
            } else {
                val nextParity = 1 - (currentPrefix.last() % 2)
                if (nextParity == 1) currentOdds.sorted() else currentEvens.sorted()
            }.filter { !currentPrefix.contains(it) }

            var found = false
            for (c in candidates) {
                val newPrefix = currentPrefix.toMutableList().apply { add(c) }
                val newOdds = currentOdds.toMutableList().apply { if (c % 2 == 1) remove(c) }
                val newEvens = currentEvens.toMutableList().apply { if (c % 2 == 0) remove(c) }

                if (newPrefix.size == n) {
                    if (currentK == 1L.toBigInteger()) {
                        currentPrefix.add(c)
                        return currentPrefix.toIntArray()
                    } else {
                        currentK--
                        continue
                    }
                }

                val nextParity = 1 - (c % 2)
                val t = n - newPrefix.size
                var requiredOdds = 0
                var requiredEvens = 0
                if (nextParity == 1) {
                    requiredOdds = (t + 1) / 2
                    requiredEvens = t / 2
                } else {
                    requiredOdds = t / 2
                    requiredEvens = (t + 1) / 2
                }

                val m = if (newOdds.size != requiredOdds || newEvens.size != requiredEvens) {
                    0L.toBigInteger()
                } else {
                    factorialProduct(newOdds.size, newEvens.size, currentK)
                }

                if (m >= currentK) {
                    currentPrefix.add(c)
                    currentOdds = newOdds
                    currentEvens = newEvens
                    found = true
                    break
                } else {
                    currentK -= m
                }
            }

            if (!found) {
                return intArrayOf()
            }
        }

        return currentPrefix.toIntArray()
    }
}