package leetcode.contest.c485

class SolutionB {
    fun maxCapacity(costs: IntArray, capacity: IntArray, budget: Int): Int {
        val n = costs.size

        val list = ArrayList<Pair<Int, Int>>(n)
        for (i in 0 until n) {
            list.add(costs[i] to capacity[i])
        }

        list.sortBy { it.first }

        val prefixMaxCap = IntArray(n)
        prefixMaxCap[0] = list[0].second
        for (i in 1 until n) {
            prefixMaxCap[i] = maxOf(prefixMaxCap[i - 1], list[i].second)
        }

        var ans = 0

        for (i in 0 until n) {
            val costI = list[i].first
            val capI = list[i].second

            if (costI < budget) {
                ans = maxOf(ans, capI)
            }

            val remaining = budget - costI - 1
            if (remaining < 0) continue

            var left = 0
            var right = i - 1
            var idx = -1

            while (left <= right) {
                val mid = (left + right) ushr 1
                if (list[mid].first <= remaining) {
                    idx = mid
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }

            if (idx != -1) {
                ans = maxOf(ans, capI + prefixMaxCap[idx])
            }
        }

        return ans
    }
}