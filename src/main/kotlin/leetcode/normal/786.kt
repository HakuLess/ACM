package leetcode.normal

class Solution786 {
    fun kthSmallestPrimeFraction(arr: IntArray, k: Int): IntArray {
        val ans = ArrayList<Triple<Int, Int, Double>>()
        for (i in 0 until arr.lastIndex) {
            for (j in i + 1 until arr.size) {
                ans.add(Triple(arr[i], arr[j], arr[i].toDouble() / arr[j]))
            }
        }
        ans.sortBy { it.third }
        return ans[k - 1].let {
            intArrayOf(it.first, it.second)
        }
    }
}