package leetcode.contest.c459

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.countTrapezoids("[[1,0],[2,0],[3,0],[2,2],[3,2]]".toGrid()).print()
}

class SolutionB {
    fun countTrapezoids(points: Array<IntArray>): Int {
        val mod = 1_000_000_007L

        val map = HashMap<Int, Long>()
        points.forEach { (x, y) ->
            map[y] = map.getOrDefault(y, 0) + 1L
        }

        val combos = ArrayList<Long>()
        for (k in map.values) {
            if (k >= 2) {
                combos.add(k * (k - 1) / 2)
            }
        }

        var total = combos.fold(0L) { acc, v -> (acc + v) % mod }
        var ans = 0L
        for (v in combos) {
            total = (total - v + mod) % mod
            ans = (ans + v * total) % mod
        }

        return ans.toInt()
    }

//    fun countTrapezoids(points: Array<IntArray>): Int {
//
//        val mod = 1_000_000_007L.toBigInteger()
//
//        val map = HashMap<Int, Long>()
//        points.forEach { (x, y) ->
//            map[y] = map.getOrDefault(y, 0) + 1L
//        }
//        var total = BigInteger.ZERO
//        var ans = BigInteger.ZERO
//
//        map.values.forEach {
//            total += BigInteger.ONE * it.toBigInteger() * (it.toBigInteger() - BigInteger.ONE) / BigInteger.TWO
//        }
//        map.values.forEach {
//            val tmp = BigInteger.ONE * it.toBigInteger() * (it.toBigInteger() - BigInteger.ONE) / BigInteger.TWO
//            ans += tmp * (total - tmp)
//            ans %= mod
//        }
//
//        return ans.toInt() / 2
//    }
}