package leetcode.contest.b68

class SolutionC {
    fun canBeValid(s: String, locked: String): Boolean {
        if (s.length % 2 != 0) return false
        var l = 0
        var r = 0
        var a = 0
        for (i in s.indices) {
            if (locked[i] == '1') {
                if (s[i] == '(') {
                    l++
                } else {
                    r++
                    if (l > 0) l--
                    else if (a > 0) a--
                    else return false
                }
            } else {
                a++
            }
        }
        l = 0
        r = 0
        a = 0
        for (i in s.indices.reversed()) {
            if (locked[i] == '1') {
                if (s[i] == '(') {
                    l++
                    if (r > 0) r--
                    else if (a > 0) a--
                    else return false
                } else {
                    r++
                }
            } else {
                a++
            }
        }
        return true
    }
}