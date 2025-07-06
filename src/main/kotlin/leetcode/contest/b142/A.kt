package leetcode.contest.b142

class SolutionA {
    fun possibleStringCount(word: String): Int {
        var ans = 1
        val arr = word.toCharArray()
        for (i in 0 until arr.lastIndex) {
            if (arr[i] == arr[i + 1]) {
                ans++
            }
        }
        return ans
    }
}