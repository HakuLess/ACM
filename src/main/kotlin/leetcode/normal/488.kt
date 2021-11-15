package leetcode.normal

import utils.print

fun main() {
    val s = Solution488()
//    s.findMinStep("WRRBBW", "RB").print()
//    s.findMinStep("WWRRBBWW", "WRBRW").print()
    s.findMinStep("RRWWRRBBRR", "WB").print()

//    s.findMinStep("BRWGWYY", "YGBWY").print()
}

class Solution488 {
    fun findMinStep(board: String, hand: String): Int {

        fun removeDup(cur: StringBuilder): Pair<Boolean, StringBuilder> {
            var i = 0
            while (i in cur.indices) {
                var j = 1
                var c = 1
                while (i + j in cur.indices) {
                    if (cur[i + j] == cur[i]) {
                        c++
                    } else {
                        break
                    }
                    j++
                }
                if (c >= 3) {
                    return Pair(true, StringBuilder(cur.removeRange(i, i + c)))
                } else {
                    i++
                }
            }
            return Pair(false, StringBuilder(cur))
        }

        var ans = -1

        val seen = HashSet<String>()
        fun dfs(o: StringBuilder, left: StringBuilder) {
            var origin = StringBuilder(o)
            while (removeDup(origin).first) {
                origin = removeDup(origin).second
            }
            if (origin.isEmpty()) {
                ans = if (ans == -1) hand.length - left.length
                else minOf(ans, hand.length - left.length)
                return
            }
            val key = "$origin,$left"
            if (key in seen) return
            seen.add(key)

            for (i in origin.indices) {
                for (j in left.indices) {
                    val next = StringBuilder()
                    next.append(origin.substring(0, i))
                    next.append(left[j])
                    next.append(origin.substring(i, origin.length))

                    val nextLeft = StringBuilder()
                    nextLeft.append(left.substring(0, j))
                    nextLeft.append(left.substring(j + 1, left.length))
                    dfs(next, nextLeft)
                }
            }
        }

        dfs(StringBuilder(board), StringBuilder(hand))
        return ans
    }
}