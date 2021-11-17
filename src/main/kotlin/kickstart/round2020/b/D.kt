package kickstart.round2020.b

// TODO 数学
fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val arr = readLine()!!.trim().split(' ').map { it.toInt() }
        val w = arr[0]
        val h = arr[1]
        val l = arr[2]
        val u = arr[3]
        val r = arr[4]
        val d = arr[5]

        val seen = HashMap<String, Double>()
        fun dfs(x: Int, y: Int): Double {
            var ans = 0.0
            if (x == w && y == h) {
                return 1.0
            }
            if (x in l..r && y in u..d) {
                return 0.0
            }
            val key = "$x, $y"
            if (key in seen) return seen[key]!!
            if (x == w) ans = dfs(x, y + 1)
            else if (y == h) ans = dfs(x + 1, y)
            else {
                ans += 0.5 * dfs(x + 1, y)
                ans += 0.5 * dfs(x, y + 1)
            }
            return ans.also {
                seen[key] = it
            }
        }

        println("Case #${it + 1}: ${dfs(1, 1)}")
    }
}