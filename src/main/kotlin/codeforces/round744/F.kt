package codeforces.round744

import utils.print

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, d) = readLine()!!.trim().split(" ").map { it.toInt() }
        val arr = readLine()!!.trim().split(" ").map { it.toInt() }
        val seen = BooleanArray(n) { false }
        var ans = 0
        for (i in arr.indices) {
            if (seen[i]) continue
            val set = HashSet<Int>()
            var cur = i
            var zero = false
            val temp = ArrayList<Int>()
            while (cur !in set) {
//                println("meet $cur $step")
                seen[cur] = true
                set.add(cur)
                temp.add(arr[cur])
                if (arr[cur] == 0) {
                    zero = true
                }
                cur = (cur + n - d) % n
            }
            if (!zero) {
                ans = -1
                break
            }
            temp.addAll(temp)
//            temp.joinToString().print()
            ans = maxOf(ans, temp.joinToString("").split("0").maxOf { it.length })
        }
        println(ans)
    }
}