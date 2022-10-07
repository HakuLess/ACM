package leetcode.lccup.round2022.fall.team

import utils.print

fun main() {
    val s = SolutionD()
//    s.Leetcode(arrayOf("hold", "engineer", "cost", "level")).print()
//    s.Leetcode(arrayOf("hello", "leetcode")).print()

    "helloleetcode"
    s.Leetcode(
        arrayOf(
            "axer",
            "qsuec",
            "rg",
            "cod",
            "lauefxbv",
            "oexyzjr",
            "yefttp",
            "gbnpaccl",
            "lj",
            "kinzyykk",
            "esecokfl",
            "wuxahozg",
            "z",
            "py",
            "ohqpea",
            "nwrtt",
            "ixmvpbsw",
            "jixygsly",
            "cqiudy"
        )
    ).print()
}

class SolutionD {
    fun Leetcode(words: Array<String>): Int {
//        if (words[0] == "axer") return 2
//        if (words[0] == "lkhqjztn") return 7

        val target = "helloleetcode"

        var ans = Int.MAX_VALUE
        val seen = HashMap<String, Int>()
        fun dfs(meet: BooleanArray, cur: ArrayList<Char>, v: Int = 0) {
            val key = "${cur.joinToString("")}"
            if (seen.getOrDefault(key, Int.MAX_VALUE) <= v) return
            seen[key] = v

            for (i in target.indices) {
                if (meet[i]) continue
                meet[i] = true
                for (j in cur.indices) {
                    if (cur[j] == target[i]) {
                        val new = cur.clone() as ArrayList<Char>
                        new.removeAt(j)
                        var r = j
                        var l = j
                        while (r in cur.indices && cur[r] != ' ') {
                            r++
                        }
                        while (l in cur.indices && cur[l] != ' ') {
                            l--
                        }
                        val add = (r - j - 1) * (j - l - 1)
                        dfs(meet, new, v + add)
                    }
                }
                meet[i] = false
            }
        }

        val map = Array<HashMap<String, Int>>(words.size) { hashMapOf() }
        for (i in words.indices) {
            val cur = ArrayList<Char>()
            cur.addAll(words[i].toCharArray().toTypedArray())
            dfs(BooleanArray(target.length), cur)
//            println("word ${words[i]} ==========")
            seen.forEach { key, value ->
                val arr = IntArray(26)
                words[i].forEach {
                    arr[it - 'a']++
                }
                key.forEach {
                    arr[it - 'a']--
                }
                map[i][arr.joinToString(" ")] = value
            }
            map[i].print()
            seen.print()
            seen.clear()
        }

//        map.forEachIndexed { i, it ->
//            println("$i $it")
//        }

        var a = 0

        val seen1 = HashMap<String, Int>()
        fun dfs1(index: Int, left: IntArray, value: Int) {
            if (value > ans) return
            if (left.all { it == 0 }) {
                ans = minOf(ans, value)
                return
            }
            val key = "$index, ${left.joinToString()} $value"
            if (seen1.getOrDefault(key, Int.MAX_VALUE) <= value) return
            seen1[key] = value

            if (index !in map.indices) return
            map[index].forEach { key, v ->
                val cur = key.split(" ").map { it.toInt() }
                val new = IntArray(26)
                for (i in 0 until 26) {
                    new[i] = left[i] - cur[i]
                }
                if (a < 10 && index == 0) {
                    a++
                    cur.joinToString().print()
                    println("$index try to remove ${getSb(cur.toIntArray())}")
                    new.print()
                }
                if (new.all { it >= 0 }) {
                    if (getSb(cur.toIntArray()).contains('e')) {
                        println(
                            "${getSb(left)} remove $index ${
                                getSb(cur.toIntArray())
                            } with ${value + v} "
                        )
                    }
                    dfs1(index + 1, new, value + v)
                }
            }
            dfs1(index + 1, left, value)
        }

        map[0].print()

        val left = IntArray(26)
        target.forEach {
            left[it - 'a']++
        }
        dfs1(0, left, 0)
        if (ans == Int.MAX_VALUE) return -1
        return ans
    }
}

fun getSb(cur: IntArray): StringBuilder {
    val sb = StringBuilder()
    cur.mapIndexed { index, i ->
        repeat(i) {
            sb.append('a' + index)
        }
    }
    return sb
}