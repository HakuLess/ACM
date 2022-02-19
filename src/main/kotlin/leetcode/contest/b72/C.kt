package leetcode.contest.b72

class SolutionC {
    fun maximumEvenSplit(finalSum: Long): List<Long> {
        if (finalSum % 2L != 0L) return emptyList()
        var cur = finalSum
        var step = 2L
        val ans = ArrayList<Long>()
        while (cur >= step) {
            ans.add(step)
            cur -= step
            step += 2L
        }
        val lst = ans.removeAt(ans.lastIndex)
        ans.add(lst + cur)
        return ans
    }
}