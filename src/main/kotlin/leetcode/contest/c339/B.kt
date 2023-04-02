package leetcode.contest.c339

class SolutionB {
    fun findMatrix(nums: IntArray): List<List<Int>> {
        val arr = ArrayList<Int>()
        arr.addAll(nums.toList())
        val ans = ArrayList<ArrayList<Int>>()
        while (arr.isNotEmpty()) {
            val c = ArrayList<Int>()
            for (i in arr.indices.reversed()) {
                if (arr[i] !in c) {
                    c.add(arr[i])
                    arr.removeAt(i)
                }
            }
            ans.add(c)
        }
        return ans
    }
}