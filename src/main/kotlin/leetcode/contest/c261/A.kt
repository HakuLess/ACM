package leetcode.contest.c261

class Solution5890 {
    fun minimumMoves(s: String): Int {
        val arr = s.toCharArray()
        var ans = 0
        for (i in arr.indices) {
            if (arr[i] != 'O') {
                arr[i] = 'O'
                if (i + 1 in arr.indices)
                    arr[i + 1] = 'O'
                if (i + 2 in arr.indices)
                    arr[i + 2] = 'O'
                ans++
            }
        }
        return ans
    }
}