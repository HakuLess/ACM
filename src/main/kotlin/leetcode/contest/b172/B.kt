package leetcode.contest.b172

class SolutionB {
    fun maximumSum(nums: IntArray): Int {

        val g0 = ArrayList<Int>()
        val g1 = ArrayList<Int>()
        val g2 = ArrayList<Int>()

        for (x in nums) {
            when (x % 3) {
                0 -> g0.add(x)
                1 -> g1.add(x)
                2 -> g2.add(x)
            }
        }

        g0.sortDescending()
        g1.sortDescending()
        g2.sortDescending()

        var ans = 0

        if (g0.size >= 3) {
            ans = maxOf(ans, g0[0] + g0[1] + g0[2])
        }
        if (g1.size >= 3) {
            ans = maxOf(ans, g1[0] + g1[1] + g1[2])
        }
        if (g2.size >= 3) {
            ans = maxOf(ans, g2[0] + g2[1] + g2[2])
        }
        if (g0.isNotEmpty() && g1.isNotEmpty() && g2.isNotEmpty()) {
            ans = maxOf(ans, g0[0] + g1[0] + g2[0])
        }

        return ans
    }
}