package leetcode.contest.c441

class SolutionD {
    fun beautifulNumbers(l: Int, r: Int): Int {

        fun toDigits(n: Int): List<Int> = n.toString().map { it - '0' }

        fun dp(digits: List<Int>): Long {
            val n = digits.size

            data class State(
                val pos: Int,
                val tight: Boolean,
                val started: Boolean,
                val s: Int,
                val p: Int,
                val hasZero: Boolean
            )

            val seen = HashMap<State, Long>()

            fun dfs(pos: Int, tight: Boolean, started: Boolean, s: Int, p: Int, hasZero: Boolean): Long {
                if (pos == n) {
                    if (!started) return 0L
                    return if (hasZero || (s != 0 && p % s == 0)) 1L else 0L
                }
                val key = State(pos, tight, started, s, p, hasZero)
                if (key in seen) return seen[key]!!

                var ans = 0L
                val limit = if (tight) digits[pos] else 9
                for (d in 0..limit) {
                    val newTight = tight && (d == limit)
                    if (!started && d == 0) {
                        ans += dfs(pos + 1, newTight, false, 0, 1, false)
                    } else {
                        val newStarted = true
                        if (d == 0) {
                            ans += dfs(pos + 1, newTight, newStarted, s + d, 0, true)
                        } else {
                            val newP = if (!started) d else p * d
                            ans += dfs(pos + 1, newTight, newStarted, s + d, newP, hasZero)
                        }
                    }
                }
                seen[key] = ans
                return ans
            }
            return dfs(0, tight = true, started = false, s = 0, p = 1, hasZero = false)
        }

        fun countUpTo(x: Int): Long = if (x < 0) 0L else dp(toDigits(x))
        return (countUpTo(r) - countUpTo(l - 1)).toInt()
    }
}