package leetcode.contest.c322

class SolutionB {
    fun dividePlayers(skill: IntArray): Long {
        var sum = 0L
        val n = skill.size
        val map = HashMap<Long, Int>()
        for (i in skill.indices) {
            sum += skill[i]
            val key = skill[i].toLong()
            map[key] = map.getOrDefault(key, 0) + 1
        }
        if (sum * 2 % n != 0L) return -1L
        val avg = sum * 2 / n
        var ans = 0L
        for (i in 0 until n / 2) {
            val a = map.keys.first()
            val b = avg - a
            if (b !in map.keys) return -1
            ans += a * b

            if (map[a] == 1) {
                map.remove(a)
            } else {
                map[a] = map[a]!! - 1
            }

            if (map[b] == 1) {
                map.remove(b)
            } else {
                map[b] = map[b]!! - 1
            }
        }
        return ans
    }
}