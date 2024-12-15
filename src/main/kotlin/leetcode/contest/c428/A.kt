package leetcode.contest.c428

class SolutionA {
    fun buttonWithLongestTime(events: Array<IntArray>): Int {
        var maxTime = events[0][1]
        var resultIndex = events[0][0]

        for (i in 1 until events.size) {
            val pressTime = events[i][1] - events[i - 1][1]
            if (pressTime > maxTime || (pressTime == maxTime && events[i][0] < resultIndex)) {

                maxTime = pressTime
                resultIndex = events[i][0]
            }
        }

        return resultIndex
    }
}