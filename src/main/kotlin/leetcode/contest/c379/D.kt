package leetcode.contest.c379

import utils.print

fun main() {
    val s = SolutionD()
    // 3
//    s.maxPartitionsAfterOperations("accca", 2).print()
//    // 1
//    s.maxPartitionsAfterOperations("aabaab", 3).print()
//    // 6
//    s.maxPartitionsAfterOperations("baacccb", 1).print()
    // 4
    s.maxPartitionsAfterOperations("xxyz", 1).print()
}

class SolutionD {
    fun maxPartitionsAfterOperations(s: String, k: Int): Int {
        if (k == 26) return 1
        val set = HashSet<Char>()
        var ans = 0

        val bound = if (('a'..'z').all { it in s }) {
            'a'..'z'
        } else {
            'A'..'A'
        }
        for (c in bound) {
            for (j in s.indices) {
                var tmp = 0
                val sArr = s.toCharArray()
                sArr[j] = c

                for (i in sArr.indices) {
                    set.add(sArr[i])

                    if (set.size > k) {
                        tmp++
                        set.clear()
                        set.add(sArr[i])
                    }
                }

                if (set.isNotEmpty()) {
                    set.clear()
                    tmp++
                }

                ans = maxOf(ans, tmp)
            }
        }

        return ans
    }
}