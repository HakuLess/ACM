package leetcode.contest.c374

class SolutionA {
    fun findPeaks(mountain: IntArray): List<Int> {
        val ans = ArrayList<Int>()
        for (i in 1 until mountain.lastIndex) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1])
                ans.add(i)
        }
        return ans
    }
}