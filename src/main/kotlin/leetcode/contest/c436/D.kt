package leetcode.contest.c436

class SolutionD {
    fun maxScore(points: IntArray, m: Int): Long {
        var l: Long = 0
        var r = 1e15.toLong()
        while (l < r) {
            val mid = (l + r + 1) / 2
            if (check(points, mid) > m) {
                r = mid - 1
            } else {
                l = mid
            }
        }
        return l
    }

    fun check(points: IntArray, minReq: Long): Long {
        val n = points.size
        val steps = LongArray(n + 1)
        for (i in 0 until n) {
            steps[i] = (minReq + points[i] - 1) / points[i]
        }
        var ans: Long = 0
        var stopAt = -1
        for (i in 0 until n) {
            if (steps[i] <= 0) {
                continue
            }
            ans += (i - stopAt - 1).toLong()
            val accessI = steps[i]
            val accessI1 = steps[i] - 1
            ans += accessI + accessI1
            steps[i + 1] -= accessI1
            stopAt = i
        }
        return ans
    }

}
