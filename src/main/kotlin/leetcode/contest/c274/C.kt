package leetcode.contest.c274

class SolutionC {
    fun asteroidsDestroyed(mass: Int, asteroids: IntArray): Boolean {
        var cur = mass.toLong()
        asteroids.sorted().forEach {
            if (it <= cur) {
                cur += it
            } else {
                return false
            }
        }
        return true
    }
}