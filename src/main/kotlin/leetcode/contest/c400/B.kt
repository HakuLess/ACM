package leetcode.contest.c400

class SolutionB {
    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        // 按会议开始时间排序
        meetings.sortBy { it[0] }

        var ans = 0
        var last = 0

        for (meeting in meetings) {
            val start = meeting[0]
            val end = meeting[1]

            if (start > last + 1) {
                ans += start - last - 1
            }

            last = maxOf(last, end)
        }

        ans += maxOf(0, days - last)

        return ans
    }
}