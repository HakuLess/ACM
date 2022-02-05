package leetcode.contest.b71

class SolutionB {
    fun pivotArray(nums: IntArray, pivot: Int): IntArray {
        val a = ArrayList<Int>()
        val b = ArrayList<Int>()
        val c = ArrayList<Int>()
        for (it in nums) {
            if (it < pivot) {
                a.add(it)
            } else if (it == pivot) {
                b.add(it)
            } else {
                c.add(it)
            }
        }
        val ans = ArrayList<Int>()
        ans.addAll(a)
        ans.addAll(b)
        ans.addAll(c)
        return ans.toIntArray()
    }
}