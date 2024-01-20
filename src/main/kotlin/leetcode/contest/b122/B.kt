package leetcode.contest.b122

class SolutionB {
    fun canSortArray(nums: IntArray): Boolean {
        val arr = ArrayList<Pair<Int, Int>>()
        nums.forEach {
            arr.add(Pair(it, it.toString(2).count { it == '1' }))
        }
        for (i in arr.indices) {
            for (j in arr.indices) {
                if (j + 1 in arr.indices && arr[j].first > arr[j + 1].first && arr[j].second == arr[j + 1].second) {
                    val tmp = arr[j + 1]
                    arr[j + 1] = arr[j]
                    arr[j] = tmp
                }
            }
        }

        val lst = arr.map { it.first }
        return lst.joinToString() == lst.sorted().joinToString()
    }
}