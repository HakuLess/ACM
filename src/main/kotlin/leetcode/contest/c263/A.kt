package leetcode.contest.c263

class Solution5902 {
    fun areNumbersAscending(s: String): Boolean {
        val arr = s.split(" ").map {
            it.toIntOrNull()
        }.filterNotNull()
        for (i in 1 until arr.size) {
            if (arr[i] <= arr[i - 1])
                return false
        }
        return true
    }
}