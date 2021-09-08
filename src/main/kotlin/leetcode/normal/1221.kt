package leetcode.normal

class Solution1221 {
    fun balancedStringSplit(s: String): Int {
        var ans = 0
        var c = 0
        s.forEach {
            if (it == 'R') c++
            else c--
            if (c == 0) ans++
        }
        return ans
    }
}