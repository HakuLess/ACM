package leetcode.contest.b111

import utils.print

fun main() {
    val s = SolutionD()
//    s.numberOfBeautifulIntegers(10, 20, 3).print()
    s.numberOfBeautifulIntegers(1, 10, 1).print()
//    s.numberOfBeautifulIntegers(5, 5, 2).print()
}

class SolutionD {
    fun numberOfBeautifulIntegers(low: Int, high: Int, k: Int): Int {
        val seen = HashMap<String, Int>()

        fun getRes(v: Int): Int {
            val s = v.toString()

            fun dfs(idx: Int, start: Boolean, bound: Boolean, mod: Int, diff: Int): Int {
                val key = "$idx,$start,$bound,$mod,$diff"
                if (key in seen) return seen[key]!!
                if (idx == s.length) {
                    return if (mod == 0 && diff == 0 && start) 1
                    else 0
                }

                var v = if (start) 0 else dfs(idx + 1, false, false, 0, 0)

                for (i in if (bound) 0..(s[idx] - '0') else 0..9) {
                    if (!start && i == 0) continue

                    val newBound = bound && i == s[idx] - '0'
                    val newMod = (10 * mod + i) % k
                    val newDiff = if (i % 2 == 0) diff + 1 else diff - 1
                    v += dfs(idx + 1, true, newBound, newMod, newDiff)
                }
                return v.also {
                    seen[key] = it
                }
            }

            return dfs(0, false, true, 0, 0).also {
                seen.clear()
            }
        }

        val a = getRes(high)
        seen.clear()
        return a - getRes(low - 1)
    }
}