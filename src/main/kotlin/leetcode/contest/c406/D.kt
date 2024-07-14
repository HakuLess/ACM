package leetcode.contest.c406

class SolutionD {
    fun minimumCost(m: Int, n: Int, horizontalCut: IntArray, verticalCut: IntArray): Long {
        data class Cut(val cost: Int, val type: Char)

        val cuts = mutableListOf<Cut>()
        for (i in horizontalCut.indices) {
            cuts.add(Cut(horizontalCut[i], 'H'))
        }
        for (j in verticalCut.indices) {
            cuts.add(Cut(verticalCut[j], 'V'))
        }

        // 对切割线按开销排序
        cuts.sortBy { -it.cost }

        var ans = 0L
        var hCount = 1L
        var vCount = 1L

        for (cut in cuts) {
            if (cut.type == 'H') {
                // 对于水平切割，开销乘以当前垂直分段的数量
                ans += cut.cost * vCount
                hCount++
            } else {
                // 对于垂直切割，开销乘以当前水平分段的数量
                ans += cut.cost * hCount
                vCount++
            }
        }

        return ans
    }
}