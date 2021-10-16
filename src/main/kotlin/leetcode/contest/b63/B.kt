package leetcode.contest.b63

class Solution5886 {
    fun winnerOfGame(colors: String): Boolean {
        var a = 0
        var b = 0
        for (i in colors.indices) {
            if (colors[i] == 'A' &&
                colors.getOrElse(i + 1) { ' ' } == 'A' &&
                colors.getOrElse(i - 1) { ' ' } == 'A'
            ) a++
            if (colors[i] == 'B' &&
                colors.getOrElse(i + 1) { ' ' } == 'B' &&
                colors.getOrElse(i - 1) { ' ' } == 'B'
            ) b++
        }
        if (a > b) return true
        return false
    }
}