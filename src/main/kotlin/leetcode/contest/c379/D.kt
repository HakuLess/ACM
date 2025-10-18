package leetcode.contest.c379

import utils.print

fun main() {
    val s = SolutionD()
    // 3
    s.maxPartitionsAfterOperations("accca", 2).print()
//    // 1
    s.maxPartitionsAfterOperations("aabaab", 3).print()
//    // 6
//    s.maxPartitionsAfterOperations("baacccb", 1).print()
    // 4
    s.maxPartitionsAfterOperations("xxyz", 1).print()
}

// Not Finished TLE
class SolutionD {

    fun maxPartitionsAfterOperations(s: String, k: Int): Int {
        val n = s.length
        val left = Array(n) { IntArray(3) }
        val right = Array(n) { IntArray(3) }

        var num = 0
        var mask = 0
        var count = 0

        // left[i+1]：表示前 i 的状态
        for (i in 0 until n - 1) {
            val binary = 1 shl (s[i] - 'a')
            if (mask and binary == 0) {
                count++
                if (count <= k) {
                    mask = mask or binary
                } else {
                    num++
                    mask = binary
                    count = 1
                }
            }
            left[i + 1][0] = num
            left[i + 1][1] = mask
            left[i + 1][2] = count
        }

        num = 0
        mask = 0
        count = 0

        // right[i-1]：表示后 i 的状态
        for (i in n - 1 downTo 1) {
            val binary = 1 shl (s[i] - 'a')
            if (mask and binary == 0) {
                count++
                if (count <= k) {
                    mask = mask or binary
                } else {
                    num++
                    mask = binary
                    count = 1
                }
            }
            right[i - 1][0] = num
            right[i - 1][1] = mask
            right[i - 1][2] = count
        }

        var maxVal = 0
        for (i in 0 until n) {
            var seg = left[i][0] + right[i][0] + 2
            val totMask = left[i][1] or right[i][1]
            val totCount = Integer.bitCount(totMask)

            if (left[i][2] == k && right[i][2] == k && totCount < 26) {
                seg++
            } else if (minOf(totCount + 1, 26) <= k) {
                seg--
            }
            maxVal = maxOf(maxVal, seg)
        }

        return maxVal
    }

//    fun maxPartitionsAfterOperations(s: String, k: Int): Int {
//        if (k == 26) return 1
//        val set = HashSet<Char>()
//        var ans = 0
//
//        val bound = if (('a'..'z').all { it in s }) {
//            'a'..'z'
//        } else {
//            'A'..'A'
//        }
//        for (c in bound) {
//            for (j in s.indices) {
//                var tmp = 0
//                val sArr = s.toCharArray()
//                sArr[j] = c
//
//                for (i in sArr.indices) {
//                    set.add(sArr[i])
//
//                    if (set.size > k) {
//                        tmp++
//                        set.clear()
//                        set.add(sArr[i])
//                    }
//                }
//
//                if (set.isNotEmpty()) {
//                    set.clear()
//                    tmp++
//                }
//
//                ans = maxOf(ans, tmp)
//            }
//        }
//
//        return ans
//    }
}