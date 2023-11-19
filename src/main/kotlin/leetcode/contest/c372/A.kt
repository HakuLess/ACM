package leetcode.contest.c372

class SolutionA {
    fun findMinimumOperations(s1: String, s2: String, s3: String): Int {
        var c = 0
        for (i in s1.indices) {
            if (i in s2.indices && i in s3.indices && s1[i] == s2[i] && s2[i] == s3[i]) {
                c++
            } else {
                break
            }
        }
        if (c == 0) return -1
        return s1.length + s2.length + s3.length - c * 3
    }
}