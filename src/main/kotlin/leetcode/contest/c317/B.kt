package leetcode.contest.c317

class SolutionB {
    fun mostPopularCreator(creators: Array<String>, ids: Array<String>, views: IntArray): List<List<String>> {
        val map = HashMap<String, ArrayList<Pair<String, Int>>>()
        val map0 = HashMap<String, Int>()
        for (i in ids.indices) {
            val creator = creators[i]
            val id = ids[i]
            val c = views[i]
            // 总播放量
            map0[creator] = map0.getOrDefault(creator, 0) + c
            map[creator] = map.getOrDefault(creator, arrayListOf())
            map[creator]!!.add(Pair(id, c))
        }
//        val max = map0.values.max()!!
        val max = map0.values.maxOrNull()!!
        val ans = ArrayList<List<String>>()
        map0.forEach { t, u ->
            if (u == max) {
                val item = map[t]!!.sortedWith(compareBy({ -it.second }, { it.first }))[0]
                ans.add(listOf(t, item.first))
            }
        }
        return ans
    }
}