package leetcode.lccup.a

import utils.print

fun main() {
    val s = SolutionE()
//    s.trafficCommand(arrayOf("W", "N", "ES", "W")).print()
    s.trafficCommand(arrayOf("NS", "WE", "SE", "EW")).print()
//    s.trafficCommand(arrayOf("S", "", "", "E")).print()
//    s.trafficCommand(arrayOf("NSNSNSNSNSNSNSNS", "WEWEWEWEWEWE", "SESESESESESESE", "EWEWEWEWEWEWEW")).print()
}

class SolutionE {
    fun trafficCommand(directions: Array<String>): Int {
        val dir = charArrayOf(' ', 'N', 'E', 'S', 'W')
        val cans = arrayListOf<CharArray>()
        // 东南西北的顺序
        for (a in dir) {
            if (a == 'E') continue
            for (b in dir) {
                if (b == 'S') continue
                for (c in dir) {
                    if (c == 'W') continue
                    for (d in dir) {
                        if (d == 'N') continue
                        // 排除掉有冲突的
                        // 左转压弯
                        if (a == 'S' && (b == 'W' || b == 'N')) continue
                        if (b == 'W' && (c == 'N' || c == 'E')) continue
                        if (c == 'N' && (d == 'E' || d == 'S')) continue
                        if (d == 'E' && (a == 'S' || a == 'W')) continue
                        // 同时驶入
                        if (a != ' ' && (a == b || a == c || a == d)) continue
                        if (b != ' ' && (b == a || b == c || b == d)) continue
                        if (c != ' ' && (c == a || c == b || c == d)) continue
                        if (d != ' ' && (d == a || d == b || d == c)) continue
                        // 直行冲突
                        if (a == 'W' && (b == 'N' || c == 'N' || d == 'S' || d == 'E')) continue
                        if (b == 'N' && (c == 'E' || d == 'E' || a == 'W' || a == 'S')) continue
                        if (c == 'E' && (d == 'S' || a == 'S' || b == 'N' || b == 'W')) continue
                        if (d == 'S' && (a == 'W' || b == 'W' || c == 'E' || c == 'N')) continue
                        cans.add(charArrayOf(a, b, c, d))
                    }
                }
            }
        }

        println(cans.size)
        cans.removeAt(0)
//        cans.forEach {
//            println(it.joinToString())
//        }

        val a = directions[0].length
        val b = directions[1].length
        val c = directions[2].length
        val d = directions[3].length

        val seen = HashMap<String, Int>()
        fun dfs(cur: IntArray): Int {
            val key = cur.joinToString()
            if (key in seen) return seen[key]!!
            if (cur[0] == a && cur[1] == b && cur[2] == c && cur[3] == d) return 0
//            cur.joinToString().print()
            val state = CharArray(4)
            for (i in 0 until 4) {
                state[i] = directions[i].getOrElse(cur[i]) { ' ' }
            }
            var ans = Int.MAX_VALUE / 2
            cans.forEach { can ->
                val next = cur.clone()
                if ((0..3).all { can[it] == state[it] || can[it] == ' ' }) {
                    for (i in 0 until 4) {
                        if (can[i] != ' ' && can[i] == state[i]) {
                            next[i]++
                        }
                    }
                    ans = minOf(ans, dfs(next) + 1)
                }
            }
            return ans.also {
                seen[key] = it
            }
        }
        return dfs(intArrayOf(0, 0, 0, 0))
    }
}