package leetcode.contest.c400

class SolutionB {
    fun countDays(days: Int, meetings: Array<IntArray>): Int {
        if (meetings.isEmpty()) return days

        // 排序会议
        meetings.sortBy { it[0] }

        var freeDays = 0
        var endOfLastMeeting = 0

        for (meeting in meetings) {
            val start = meeting[0]
            val end = meeting[1]

            // 计算从上一个会议结束到当前会议开始的空闲天数
            if (start > endOfLastMeeting + 1) {
                freeDays += start - endOfLastMeeting - 1
            }

            // 更新上一个会议的结束天数
            endOfLastMeeting = maxOf(endOfLastMeeting, end)
        }

        // 计算从最后一个会议结束到总天数结束的空闲天数
        if (endOfLastMeeting < days) {
            freeDays += days - endOfLastMeeting
        }

        return freeDays
    }
}