package leetcode.contest.b97

import utils.print


fun main() {
    val s = SolutionC()
    s.maximizeWin(intArrayOf(1, 1, 2, 2, 3, 3, 5), 2).print()
}

class SolutionC {
    fun maximizeWin(prizePositions: IntArray, k: Int): Int {
        val pre = IntArray(prizePositions.size + 1)
        var ans = 0
        var left = 0
        prizePositions.forEachIndexed { right, p ->
            // 与左侧距离缩减到K
            while (p - prizePositions[left] > k)
                left += 1
            ans = maxOf(ans, right - left + 1 + pre[left])
            pre[right + 1] = maxOf(pre[right], right - left + 1)
        }
        return ans
    }
}