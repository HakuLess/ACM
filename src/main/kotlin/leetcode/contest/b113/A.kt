package leetcode.contest.b113

class SolutionA {
    fun minimumRightShifts(nums: List<Int>): Int {
        val arr = ArrayList<Int>()
        arr.addAll(nums)
        if (arr.joinToString() == arr.sorted().joinToString()) return 0
        repeat(arr.size) {
            arr.add(0, arr.removeAt(arr.lastIndex))
            if (arr.joinToString() == arr.sorted().joinToString()) return it + 1
        }
        return -1
    }
}