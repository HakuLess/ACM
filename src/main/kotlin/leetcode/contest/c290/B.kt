package leetcode.contest.c290

class SolutionB {
    fun countLatticePoints(circles: Array<IntArray>): Int {
        val seen = HashSet<Int>()
        circles.forEach { item ->
            val (x, y, r) = item
            val minX = x - r
            val maxX = x + r
            val minY = y - r
            val maxY = y + r

            for (a in minX..maxX) {
                for (b in minY..maxY) {
                    val key = a + b * 1000
                    if (key !in seen && (a - x) * (a - x) + (b - y) * (b - y) <= r * r) {
                        seen.add(key)
                    }
                }
            }
        }
        return seen.size
    }
}