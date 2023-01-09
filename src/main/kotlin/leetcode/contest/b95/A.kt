package leetcode.contest.b95

class SolutionA {
    fun categorizeBox(length: Int, width: Int, height: Int, mass: Int): String {
        val a = 10000
        var bulky = false
        if (length >= a || width >= a || height >= a) {
            bulky = true
        }
        if (1L * length * width * height >= 1000000000L) {
            bulky = true
        }
        var heavy = false
        if (mass >= 100) heavy = true

        if (heavy && bulky) {
            return "Both"
        }
        if (!heavy && !bulky) {
            return "Neither"
        }
        if (heavy) {
            return "Heavy"
        }
        return "Bulky"
    }
}