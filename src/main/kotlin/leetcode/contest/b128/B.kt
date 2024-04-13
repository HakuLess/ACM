package leetcode.contest.b128

class SolutionB {
    fun minRectanglesToCoverPoints(points: Array<IntArray>, w: Int): Int {
        var ans = 0
        val l = points.map { it[0] }.distinct().sorted()
        var pre = -1
        for (i in l.indices) {
            if (pre == -1) {
                pre = l[i]
                ans++
            } else if (l[i] - pre > w) {
                ans++
                pre = l[i]
            }
        }
        return ans
    }
}