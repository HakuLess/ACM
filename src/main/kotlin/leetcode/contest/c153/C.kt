package leetcode.contest.c153

class SolutionC {
    fun minimumCost(nums: IntArray, cost: IntArray, k: Int): Long {
        val n = nums.size

        val preNum = LongArray(n + 1)
        val preCost = LongArray(n + 1)
        for (i in 0 until n) {
            preNum[i + 1] = preNum[i] + nums[i]
            preCost[i + 1] = preCost[i] + cost[i]
        }

        val inf = Long.MAX_VALUE / 2
        // dp[i][j]：将前 i 个元素分割为 j 个子数组的最小总代价
        val dp = Array(n + 1) { LongArray(n + 1) { inf } }
        dp[0][0] = 0L

        for (j in 1..n) {
            val cht = ConvexHullTrick()
            for (l in (j - 1) until n) {
                if (dp[l][j - 1] < inf) {
                    cht.addLine(-preCost[l], dp[l][j - 1])
                }
            }
            for (i in j..n) {
                val query = cht.query(preNum[i] + k.toLong() * j)
                dp[i][j] = (preNum[i] + k.toLong() * j) * preCost[i] + query
            }
        }

        var ans = inf
        for (j in 1..n) {
            ans = minOf(ans, dp[n][j])
        }
        return ans
    }
}


data class Line(val m: Long, val b: Long, var xLeft: Double = Double.NEGATIVE_INFINITY) {
    fun intersect(other: Line): Double {
        return (other.b - b).toDouble() / (m - other.m)
    }
}

class ConvexHullTrick {
    private val lines = ArrayList<Line>()
    private var pointer = 0

    fun addLine(m: Long, b: Long) {
        val newLine = Line(m, b)
        while (lines.isNotEmpty()) {
            val last = lines.last()
            val x = newLine.intersect(last)
            if (x <= last.xLeft) {
                lines.removeAt(lines.size - 1)
            } else {
                break
            }
        }
        if (lines.isEmpty()) {
            newLine.xLeft = Double.NEGATIVE_INFINITY
        } else {
            newLine.xLeft = newLine.intersect(lines.last())
        }
        lines.add(newLine)
    }

    fun query(x: Long): Long {
        if (pointer >= lines.size) pointer = lines.size - 1
        while (pointer < lines.size - 1 && lines[pointer + 1].xLeft <= x.toDouble()) {
            pointer++
        }
        return lines[pointer].m * x + lines[pointer].b
    }
}