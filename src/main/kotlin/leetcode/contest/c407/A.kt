package leetcode.contest.c407

class SolutionA {
    fun minChanges(n: Int, k: Int): Int {
        val a = n.toString(2).padStart(20, '0')
        val b = k.toString(2).padStart(20, '0')
        var ans = 0
        for (i in a.indices) {
            if (a[i] == '1' && b[i] == '0') {
                ans++
            } else if (a[i] == '0' && b[i] == '1') {
                return -1
            }
        }
        return ans
    }
}