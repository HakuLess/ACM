package leetcode.contest.c492

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.minOperations("omo").print()
    // -1
    s.minOperations("gf").print()
    // 2
    s.minOperations("card").print()
    // 3
    s.minOperations("edc").print()
}

class SolutionC {
    fun minOperations(s: String): Int {

        val n = s.length

        if (n <= 1) return 0

        var isSorted = true
        for (i in 0 until n - 1) {
            if (s[i] > s[i + 1]) {
                isSorted = false
                break
            }
        }
        if (isSorted) return 0

        // 首 or 尾 正常，那 1 次把 else 排序即可
        var maxPrefix = 'a'
        for (i in 0 until n - 1) {
            if (s[i] > maxPrefix) maxPrefix = s[i]
        }
        if (s[n - 1] >= maxPrefix) return 1

        var minSuffix = 'z'
        for (i in 1 until n) {
            if (s[i] < minSuffix) minSuffix = s[i]
        }
        if (s[0] <= minSuffix) return 1


        var overallMax = 'a'
        var overallMin = 'z'
        var maxCount = 0
        var minCount = 0

        for (c in s) {
            if (c > overallMax) {
                overallMax = c
                maxCount = 1
            } else if (c == overallMax) {
                maxCount++
            }

            if (c < overallMin) {
                overallMin = c
                minCount = 1
            } else if (c == overallMin) {
                minCount++
            }
        }

        val uniqueMaxAt0 = (s[0] == overallMax && maxCount == 1)
        val uniqueMinAtNMinus1 = (s[n - 1] == overallMin && minCount == 1)

        // 被迫整体排序 返回 -1
        if (uniqueMaxAt0 && uniqueMinAtNMinus1) {
            if (n < 3) return -1
            return 3
        }

        return 2
    }
}