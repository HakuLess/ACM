package leetcode.contest.c399

class SolutionB {
    fun compressedString(word: String): String {
        val ans = StringBuilder()
        var c = word[0]
        var cnt = 0
        for (i in word.indices) {
            if (c == word[i]) {
                cnt++

                if (cnt == 9) {
                    ans.append("${cnt}${c}")
                    cnt = 0
                }
            } else {
                if (cnt != 0) {
                    ans.append("${cnt}${c}")
                }
                c = word[i]
                cnt = 1
            }
        }
        if (cnt != 0) {
            ans.append("${cnt}${c}")
        }
        return ans.toString()
    }
}