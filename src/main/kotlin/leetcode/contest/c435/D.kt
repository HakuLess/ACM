package leetcode.contest.c435

import utils.print


fun main() {
    val s = SolutionD()
    // -1
    s.maxDifference("12233", 4).print()
//    // 1
    s.maxDifference("1122211", 3).print()
    // -1
    s.maxDifference("110", 3).print()
}

class SolutionD {
    fun maxDifference(s: String, k: Int): Int {
        var ans = Int.MIN_VALUE

        val arr = charArrayOf('0', '1', '2', '3', '4')
        val count = IntArray(s.length)
        val mark = IntArray(s.length)
        // 状态压缩10代表l出现奇数次，r出现偶数次
        // 00代表l出现偶数次，r出现偶数次
        // 01代表l出现偶数次，r出现奇数次
        // 00代表l出现奇数次，r出现奇数次
        for (l in arr) {
            for (r in arr) {
                if (l == r) continue
                var sum = 0
                var currentMark = 0
                // lMet 和 rMet 确保区间内必须存在一个对应的数字。
                var lMet = -1
                var rMet = -1
                var pos = 0

                val minMem = IntArray(4) { Int.MAX_VALUE }
                minMem[0] = 0
                for (i in s.indices) {
                    if (s[i] == l) {
                        ++sum
                        lMet = i
                        currentMark = currentMark xor 2
                    } else if (s[i] == r) {
                        --sum
                        rMet = i
                        currentMark = currentMark xor 1
                    }
                    count[i] = sum
                    mark[i] = currentMark
                    if (i >= k - 1 && lMet != -1 && rMet != -1) {
                        for (j in 0..3) {
                            // 必须确保左数出现奇数次，右数出现偶数次。
                            if (currentMark xor j == 2 && minMem[j] != Int.MAX_VALUE) {
                                ans = maxOf(ans, sum - minMem[j])
                            }
                        }
                        var min = minOf(i - k + 1, lMet - 1)
                        min = minOf(min, rMet - 1)
                        while (pos <= min) {
                            minMem[mark[pos]] = minOf(minMem[mark[pos]], count[pos])
                            ++pos
                        }
                    }
                }
            }
        }
        return ans
    }
}