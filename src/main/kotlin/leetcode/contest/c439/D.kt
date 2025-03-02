package leetcode.contest.c439

class SolutionD {
    fun generateString(str1: String, str2: String): String {
        val n = str1.length
        val m = str2.length
        val len = n + m - 1
        val c = '*'

        val ans = CharArray(len) { c }
        val forced = BooleanArray(len) { false }

        for (i in 0 until n) {
            if (str1[i] == 'T') {
                for (j in 0 until m) {
                    val pos = i + j
                    if (ans[pos] == c) {
                        ans[pos] = str2[j]
                        forced[pos] = true
                    } else {
                        if (ans[pos] != str2[j]) return ""
                    }
                }
            }
        }

        for (i in 0 until len) {
            if (ans[i] == c) {
                ans[i] = 'a'
            }
        }

        for (i in 0 until n) {
            if (str1[i] == 'F') {
                var windowEqual = true
                for (j in 0 until m) {
                    if (ans[i + j] != str2[j]) {
                        windowEqual = false
                        break
                    }
                }
                if (windowEqual) {
                    var modified = false
                    for (j in m - 1 downTo 0) {
                        val pos = i + j
                        if (!forced[pos]) {
                            ans[pos] = 'b'
                            modified = true
                            break
                        }
                    }
                    if (!modified) return ""
                }
            }
        }
        return String(ans)
    }
}