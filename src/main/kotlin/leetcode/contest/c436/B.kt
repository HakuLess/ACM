package leetcode.contest.c436

fun main() {
    val s = SolutionB()
    s.assignElements(intArrayOf(8, 4, 3, 2, 4), intArrayOf(4, 2))
}

class SolutionB {
    fun assignElements(groups: IntArray, elements: IntArray): IntArray {

        val max = groups.maxOrNull()!! + 1

        val seen = HashSet<Int>()
        val arr = IntArray(max) { -1 }
        for (i in elements.indices) {
            val item = elements[i]
            if (item in seen) continue
            seen.add(item)
            var cur = item
            while (cur in arr.indices) {
                if (arr[cur] == -1) {
                    arr[cur] = i
                }
                cur += item
            }
        }

        return groups.map { arr[it] }.toIntArray()
    }
}