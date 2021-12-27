package leetcode.normal

class Solution686 {
    fun repeatedStringMatch(a: String, b: String): Int {
        val s = StringBuilder()
        var ans = 0
        while (s.length < b.length) {
            s.append(a)
            ans++
        }
        if (s.contains(b)) {
            return ans
        } else if (s.append(a).contains(b)) {
            return ans + 1
        } else {
            return -1
        }
    }
}