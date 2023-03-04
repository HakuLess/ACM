package leetcode.contest.b99

class SolutionA {
    fun splitNum(num: Int): Int {
        var a = ""
        var b = ""
        val sorted = num.toString().toCharArray().sorted()
        for (i in sorted.indices step 2) {
            a += sorted[i]
            if (i + 1 in sorted.indices) {
                b += sorted[i + 1]
            }
        }
        return a.toInt() + b.toInt()
    }
}