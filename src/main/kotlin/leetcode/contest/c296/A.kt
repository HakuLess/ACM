package leetcode.contest.c296

class SolutionA {
    fun minMaxGame(nums: IntArray): Int {
        var arr = nums
        while (arr.size != 1) {
            val next = IntArray(arr.size / 2)
            for (i in next.indices) {
                if (i % 2 == 0) {
                    next[i] = minOf(arr[i * 2], arr[i * 2 + 1])
                } else {
                    next[i] = maxOf(arr[i * 2], arr[i * 2 + 1])
                }
            }
            arr = next
        }
        return arr.first()
    }
}