package leetcode.contest.b145

class SolutionB {
    fun findMinimumTime(strength: List<Int>, K: Int): Int {
        val n = strength.size
        var minTime = Int.MAX_VALUE

        fun calculateTime(order: List<Int>, k: Int): Int {
            var totalTime = 0
            var currentX = 1
            for (lock in order) {
                val timeToReach = (lock + currentX - 1) / currentX
                totalTime += timeToReach
                currentX += k
            }
            return totalTime
        }

        fun permute(sequence: List<Int>, start: Int = 0) {
            if (start == sequence.size) {
                minTime = minOf(minTime, calculateTime(sequence, K))
                return
            }
            for (i in start until sequence.size) {
                val swapped = sequence.toMutableList()
                swapped[start] = sequence[i].also { swapped[i] = sequence[start] }
                permute(swapped, start + 1)
            }
        }

        permute(strength.toList())
        return minTime
    }
}