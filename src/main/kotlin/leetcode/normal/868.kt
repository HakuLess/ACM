package leetcode.normal

class Solution868 {
    fun binaryGap(n: Int): Int {
        var num = n
        var lstPos = -1
        var curPos = 0
        var ans = 0

        while (num > 0) {
            if ((num and 1) == 1) {
                if (lstPos != -1) {
                    ans = maxOf(ans, curPos - lstPos)
                }
                lstPos = curPos
            }
            num = num shr 1
            curPos++
        }

        return ans
    }
}