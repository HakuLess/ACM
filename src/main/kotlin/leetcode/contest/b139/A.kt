package leetcode.contest.b139

class SolutionA {
    fun stableMountains(height: IntArray, threshold: Int): List<Int> {
        val stableMountains = mutableListOf<Int>()

        for (i in 1 until height.size) {
            if (height[i - 1] > threshold) {
                stableMountains.add(i)
            }
        }

        return stableMountains
    }
}