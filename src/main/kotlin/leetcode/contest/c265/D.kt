package leetcode.contest.c265

import utils.print

fun main() {
    val s = Solution2060()
    s.possiblyEquals("internationalization", "i18n").print()
//    s.possiblyEquals("l123e", "44").print()

//    s.possiblyEquals("64g97q959g531q54g576g491q611g362g", "9g157q83q57q9g465q92q554g23g41q47").print()
}

class Solution2060 {
    fun possiblyEquals(s1: String, s2: String): Boolean {
        val seen = HashSet<String>()

        var ans = false

        fun dfs(i: Int, j: Int, rest1: Int, rest2: Int) {
            if (ans) return
            val key = "$i,$j,$rest1,$rest2"
            if (key in seen) return
            seen.add(key)

            // 刚好匹配完成
            if (i == s1.length && j == s2.length && rest1 == 0 && rest2 == 0) {
                ans = true
                return
            }

            // 有一方有剩余没用完，则互相消耗掉
            if (rest1 != 0 && rest2 != 0) {
                val min = minOf(rest1, rest2)
                dfs(i, j, rest1 - min, rest2 - min)
                return
            }

            // 无可消耗，需要字符匹对进行判断
            if (rest1 == 0 && rest2 == 0 && i in s1.indices && s1[i] in 'a'..'z' && j in s2.indices && s2[j] in 'a'..'z') {
                if (s1[i] == s2[j]) {
                    dfs(i + 1, j + 1, 0, 0)
                } else return
            } else if (rest1 > 0 && j in s2.indices && s2[j] in 'a'..'z') {
                return dfs(i, j + 1, rest1 - 1, 0)
            } else if (rest2 > 0 && i in s1.indices && s1[i] in 'a'..'z') {
                return dfs(i + 1, j, 0, rest2 - 1)
            } else if (j in s2.indices && s2[j] in '1'..'9') {
                var rest = 0
                for (k in 0 until 3) {
                    if (j + k in s2.indices && s2[j + k].isDigit()) {
                        rest = rest * 10 + (s2[j + k] - '0')
                        dfs(i, j + k + 1, rest1, rest2 + rest)
                    } else break
                }
                return
            } else if (i in s1.indices && s1[i] in '1'..'9') {
                var rest = 0
                for (k in 0 until 3) {
                    if (i + k in s1.indices && s1[i + k].isDigit()) {
                        rest = rest * 10 + (s1[i + k] - '0')
                        dfs(i + k + 1, j, rest1 + rest, rest2)
                    } else break
                }
                return
            } else {
                return
            }
        }

        dfs(0, 0, 0, 0)
        return ans
    }
}