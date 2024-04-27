package leetcode.contest.b129

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    // 14
    s.numberOfStableArrays(3, 3, 2).print()
    // 587893473
//    s.numberOfStableArrays(200, 200, 200).print()
    // 51895935
//    s.numberOfStableArrays(17, 12, 96).print()
}

class SolutionC {
    fun numberOfStableArrays(zero: Int, one: Int, limit: Int): Int {
        val mod = 1000000007L

        var ans = 0L
        val seen = HashMap<Long, Long>()

        fun dfs(cur: Int, leftZero: Int, leftOne: Int, times: Int): Long {

            if (leftZero < 0 || leftOne < 0 || abs(times) > limit) return 0L
            if (leftZero == 0 && leftOne == 0) return 1L

            val key = if (limit - times > leftZero + leftOne) {
                leftZero + leftOne * 201L + 201L * 201L * times
            } else {
                leftZero + leftOne * 201L + times * 201L * 201L
            }
            if (key in seen) return seen[key]!!

//            println("$cur $leftZero $leftOne $times")

            var tmp = 0L
            if (cur == 0 && abs(times) < limit || cur == 1) {
                tmp += dfs(0, leftZero - 1, leftOne, if (cur == 0) times - 1 else -1)
                tmp %= mod
            }
            if (cur == 1 && abs(times) < limit || cur == 0) {
                tmp += dfs(1, leftZero, leftOne - 1, if (cur == 1) times + 1 else 1)
                tmp %= mod
            }

            return tmp.also {
                seen[key] = (it % mod)
            }
        }

        ans += dfs(0, zero - 1, one, -1)
        ans %= mod
        ans += dfs(1, zero, one - 1, 1)

//        println(seen.size)
        return (ans % mod).toInt()
    }

}
