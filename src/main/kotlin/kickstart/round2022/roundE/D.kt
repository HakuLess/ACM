package kickstart.round2022.roundE

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, p, m, ar, ac) = readLine()!!.trim().split(" ").map { it.toInt() }
        val dir4 = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(0, 1),
            intArrayOf(0, -1),
            intArrayOf(1, 0)
        )
        val func = Array<Pair<Char, Int>>(4) { Pair(' ', 0) }
        repeat(4) {
            val tmp = readLine()!!.trim().split(" ")
            func[it] = Pair(tmp[0].toCharArray()[0], tmp[1].toInt())
        }

        val customs = HashMap<Pair<Int, Int>, Int>()
        repeat(p) {
            val (x, y, c) = readLine()!!.trim().split(" ").map { it.toInt() }
            customs[Pair(x, y)] = c
        }
        var ans = Int.MIN_VALUE / 2
        val s = HashSet<String>()
        fun dfs(x: Int, y: Int, cur: Int, step: Int, p: Int, seen: HashSet<Pair<Int, Int>>) {
            if (x !in 1 until n + 1 || y !in 1 until n + 1) return

//            println("$x $y with $cur $step")
            val key = Pair(x, y)
            if (step == m) {
                if (p == 0) {
                    ans = maxOf(ans, cur)
                }
                if (p == 1 && key !in seen && key in customs.keys) {
                    ans = maxOf(ans, cur + customs[key]!!)
                }
                return
            }

            val k = "$x,$y,$p,$cur,$step,${seen.joinToString()}"
            if (k in s) {
                return
            }
            s.add(k)

            // stop
            dfs(x, y, cur, step + 1, p, seen)
//            dfs(x, y, nextV, step, nextP, nextSeen)

            dir4.forEachIndexed { index, it ->
                var next = cur
                when (func[index].first) {
                    '+' -> next += func[index].second
                    '-' -> next -= func[index].second
                    '*' -> next *= func[index].second
                    '/' -> {
                        val minus = next < 0 && next % func[index].second != 0
                        next /= func[index].second
                        if (minus) next--
                    }
                }
                dfs(x + it[0], y + it[1], next, step + 1, p, seen)
            }

            var nextV = cur
            val nextSeen = seen.clone() as HashSet<Pair<Int, Int>>
            if (key in customs.keys && key !in seen) {
                nextV += customs[key]!!
                nextSeen.add(key)

                dir4.forEachIndexed { index, it ->
                    var next = nextV
                    when (func[index].first) {
                        '+' -> next += func[index].second
                        '-' -> next -= func[index].second
                        '*' -> next *= func[index].second
                        '/' -> {
                            val minus = next < 0 && next % func[index].second != 0
                            next /= func[index].second
                            if (minus) next--
                        }
                    }
                    dfs(x + it[0], y + it[1], next, step + 1, p - 1, nextSeen)
                }
            }
        }
        dfs(ar, ac, 0, 0, p, hashSetOf<Pair<Int, Int>>())

        println(
            "Case #${it + 1}: ${
                if (ans == Int.MIN_VALUE / 2) {
                    "IMPOSSIBLE"
                } else {
                    ans
                }
            }"
        )
    }
}