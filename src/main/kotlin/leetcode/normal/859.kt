package leetcode.normal

class Solution859 {
    fun buddyStrings(s: String, goal: String): Boolean {
        if (s.length != goal.length) return false
        val l = ArrayList<Int>()
        for (i in s.indices) {
            if (s[i] != goal[i]) {
                l.add(i)
            }
        }
        return (l.size == 2 && s[l[0]] == goal[l[1]] && s[l[1]] == goal[l[0]]) ||
               (l.size == 0 && ('a'..'z').any { s.count { ch -> ch == it } >= 2 })
    }
}