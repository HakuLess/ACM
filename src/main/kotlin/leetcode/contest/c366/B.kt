package leetcode.contest.c366

class SolutionB {
    fun minProcessingTime(processorTime: List<Int>, tasks: List<Int>): Int {
        val t = tasks.sorted()
        val p = processorTime.sortedDescending()
        var ans = 0
        for (i in p.indices) {
            val item = p[i]
            for (j in i * 4 until i * 4 + 4) {
                val task = t[j]
                ans = maxOf(ans, task + item)
            }
        }
        return ans
    }
}