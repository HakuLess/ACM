package kickstart.round2022.roundA

import utils.gcd
import utils.print

fun main() {
    val seen = HashMap<String, Long>()

    // 计算可被整除数量
    fun dfs(
        str: String,
        i: Int = 0,
        preSum: Long = 0L,
        prePro: Long = 1L,
        all: Boolean = false,
        meetNoZero: Boolean = false
    ): Long {
        val key = "$i,$preSum,$prePro,$all"
        if (key in seen) {
            return seen[key]!!
        }
        if (i !in str.indices) {
            if (preSum == 0L) return 0
            if (prePro % preSum == 0L) {
                return 1
            } else {
                return 0
            }
        }
        var ans = 0L
        if (all) {
            if (meetNoZero) {
                ans += dfs(str, i + 1, preSum + 0, prePro * 0, true, true)
            } else {
                ans += dfs(str, i + 1, preSum, prePro, true, false)
            }
            for (c in 1..9) {
                ans += dfs(str, i + 1, preSum + c, prePro * c, true, true)
            }
        } else {
            val max = str[i] - '0'
            for (c in 0..max) {
                if (c == 0) {
                    if (meetNoZero) {
                        ans += dfs(str, i + 1, preSum + c, prePro * c, c != max, true)
                    } else {
                        ans += dfs(str, i + 1, preSum, prePro, c != max, false)
                    }
                } else {
                    ans += dfs(str, i + 1, preSum + c, prePro * c, c != max, true)
                }
            }
        }
        return ans.also {
            seen[key] = it
        }
    }

    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (a, b) = readLine()!!.split(" ").map { it.toLong() }
        seen.clear()
        val max = dfs(b.toString())
        seen.clear()
        val min = dfs((a - 1).toString())
        seen.clear()
        val ans = max - min
        println("Case #${it + 1}: $ans")
    }
}