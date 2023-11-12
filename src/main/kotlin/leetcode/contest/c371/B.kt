package leetcode.contest.c371

class SolutionB {
    fun findHighAccessEmployees(access_times: List<List<String>>): List<String> {
        val map = HashMap<String, ArrayList<Int>>()
        access_times.sortedBy { it[1] }.forEach {
            val key = it[0]
            val value = it[1].toInt()
            map[key] = map.getOrDefault(key, arrayListOf())
            map[key]!!.add(value)
        }
        val ans = ArrayList<String>()
        map.keys.forEach {
            val l = map[it]!!
            for (i in 2 until l.size) {
                if (l[i] - l[i - 2] < 100) {
                    ans.add(it)
                    break
                }
            }
        }
        return ans
    }
}