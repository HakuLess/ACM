package leetcode.contest.c436

fun main() {
    val s = SolutionB()
    s.assignElements(intArrayOf(8, 4, 3, 2, 4), intArrayOf(4, 2))
}

class SolutionB {
    fun assignElements(groups: IntArray, elements: IntArray): IntArray {

        val maxGroupSize = groups.maxOrNull() ?: 0
        val minIndexForGroupSize = IntArray(maxGroupSize + 1) { -1 }

        val elementToMinIndex = mutableMapOf<Int, Int>()
        for (j in elements.indices) {
            val element = elements[j]
            if (element == 0) continue
            if (!elementToMinIndex.containsKey(element) || j < elementToMinIndex[element]!!) {
                elementToMinIndex[element] = j
            }
        }

        for ((element, j) in elementToMinIndex) {
            for (k in element..maxGroupSize step element) {
                if (minIndexForGroupSize[k] == -1 || j < minIndexForGroupSize[k]) {
                    minIndexForGroupSize[k] = j
                }
            }
        }

        val assigned = IntArray(groups.size) { -1 }
        for (i in groups.indices) {
            val groupSize = groups[i]
            if (groupSize <= maxGroupSize) {
                assigned[i] = minIndexForGroupSize[groupSize]
            }
        }

        return assigned
    }
}