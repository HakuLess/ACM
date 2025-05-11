package leetcode.contest.c449

class SolutionA {
    fun minDeletion(s: String, k: Int): Int {
        val map = HashMap<Char, Int>()
        for (c in s) {
            map[c] = map.getOrDefault(c, 0) + 1
        }
        val l = ArrayList<Int>()
        l.addAll(map.values.sortedDescending())
        var ans = 0
        while (l.size > k) {
            ans += l.removeLast()
        }
        return ans
    }
}