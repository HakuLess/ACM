package leetcode.contest.c307

class SolutionB {
    fun largestPalindromic(num: String): String {
        if (num.all { it == '0' }) return "0"
        val arr = IntArray(10)
        num.forEach {
            arr[it - '0']++
        }
        var ex = -1
        val ans = StringBuilder()
        for (i in arr.indices.reversed()) {
            if (arr[i] % 2 != 0 && ex == -1) {
                ex = i
            }
            if (i == 0 && ans.isEmpty()) continue
            repeat(arr[i] / 2) {
                ans.append(i)
            }
        }
        val tmp = ans.toString()
        if (ex != -1) {
            ans.append(ex)
        }
        ans.append(tmp.reversed())
        return ans.toString()
    }
}