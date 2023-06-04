package leetcode.contest.c348

import utils.print

fun main() {
    val s = SolutionD()
//    s.count("1", "10000000", 1, 10).print()
//    s.count("1", "12", 1, 8).print()
//    s.count("1", "5", 1, 5).print()

    // 114
//    s.count("6312", "9416", 29, 30).print()
//    s.count("1000000007", "2000000014", 1, 400).print()

    // 1778
//    s.count("664", "4906", 17, 24).print()

    // 614
    s.count("767", "3481", 15, 17).print()
}

class SolutionD {
    fun count(num1: String, num2: String, min_sum: Int, max_sum: Int): Int {

        val mod = 1000000007L

        val seen = HashMap<String, Long>()

        fun dfs(cur: String, sum: Int): Long {


            if (sum > max_sum) return 0
            if (cur.isNotEmpty() && cur.toBigInteger() > num2.toBigInteger()) return 0

            var type = 0
            val n = cur.length
            if (cur.isEmpty()) {
                type = 0
            } else if (cur.toBigInteger() < num1.take(n).toBigInteger()) {
                type = 1
                if (cur.toBigInteger() > num2.take(n).toBigInteger()) {
                    type = 11
                } else if (cur.toBigInteger() == num2.take(n).toBigInteger()) {
                    type = 12
                } else if (cur.toBigInteger() < num2.take(n).toBigInteger()) {
                    type = 13
                }
//                type = cur.toIntOrNull() ?: 0
            } else if (cur.toBigInteger() > num2.take(n).toBigInteger()) {
                type = 2
                if (cur.toBigInteger() > num1.take(n).toBigInteger()) {
                    type = 21
                } else if (cur.toBigInteger() == num1.take(n).toBigInteger()) {
                    type = 22
                } else if (cur.toBigInteger() < num1.take(n).toBigInteger()) {
                    type = 23
                }
//                type = cur.toIntOrNull() ?: 0
            } else if (cur.toBigInteger() == num1.take(n).toBigInteger()) {
                type = 3
            } else if (cur.toBigInteger() == num2.take(n).toBigInteger()) {
                type = 4
            } else {
                type = 5
            }

//            type = cur.toIntOrNull() ?: 0

            val key = "${sum}_${n}_${type}"
            if (key in seen) return seen[key]!!.also {
//                println("return seen $key with $it  cur is $cur")
            }

            var ans = 0L
            if (cur.isNotEmpty() && cur.toBigInteger() >= num1.toBigInteger() && sum >= min_sum) ans++

            var start = 0
            if (cur.isEmpty()) {
                start = 1
            }
            for (i in start..9) {
                ans += dfs("$cur$i", sum + i)
                ans %= mod
            }
            return ans.also {
                seen[key] = it
//                println("cur $cur key $key with $it")
            }
        }

        return (dfs("", 0) % mod).toInt()
    }
}