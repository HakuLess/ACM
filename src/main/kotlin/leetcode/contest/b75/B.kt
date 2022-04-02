package leetcode.contest.b75

class SolutionB {
    fun triangularSum(nums: IntArray): Int {
        var list = ArrayList<Int>()
        list.addAll(nums.toList())
        while (list.size > 1) {
            val next = ArrayList<Int>()
            for (i in 0 until list.lastIndex) {
                next.add((list[i] + list[i + 1]) % 10)
            }
            list = next
        }
        return list[0]
    }
}