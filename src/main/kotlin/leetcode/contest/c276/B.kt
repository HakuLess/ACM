package leetcode.contest.c276


class SolutionB {
    fun minMoves(target: Int, maxDoubles: Int): Int {
        var cur = target
        var left = maxDoubles
        var ans = 0
        while (cur != 1) {
            if (cur % 2 == 0 && left > 0) {
                left--
                cur /= 2
                ans++
            } else if (left == 0) {
                ans += cur - 1
                return ans
            } else {
                cur--
                ans++
            }
        }
        return ans
    }
}