package leetcode.normal

class Solution638 {
    fun shoppingOffers(price: List<Int>, special: List<List<Int>>, needs: List<Int>): Int {

        val n = price.size
        val seen = HashMap<String, Int>()

        fun dfs(cur: ArrayList<Int>): Int {
            val key = cur.joinToString()
            if (key in seen) return seen[key]!!

            var min = 0
            for (i in cur.indices) {
                min += cur[i] * price[i]
            }

            special.forEach { sp ->
                if ((0 until n).all { sp[it] <= cur[it] }) {
                    val next = ArrayList<Int>()
                    for (i in 0 until n) {
                        next.add(cur[i] - sp[i])
                    }
                    min = minOf(min, dfs(next) + sp.last())
                }
            }
            return min.also {
                seen[key] = it
            }
        }

        return dfs(ArrayList(needs))
    }
}