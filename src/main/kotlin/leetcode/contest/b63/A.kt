package leetcode.contest.b63

import kotlin.math.abs

class Solution5885 {
    fun minMovesToSeat(seats: IntArray, students: IntArray): Int {
        seats.sort()
        students.sort()
        var ans = 0
        for (i in seats.indices) {
            ans += abs(seats[i] - students[i])
        }
        return ans
    }
}