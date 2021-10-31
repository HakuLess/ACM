package leetcode.contest.c265

import utils.print

fun main() {
    val s = SolutionD()
//    s.possiblyEquals("internationalization", "i18n").print()
//    s.possiblyEquals("l123e", "44").print()

    s.possiblyEquals("64g97q959g531q54g576g491q611g362g", "9g157q83q57q9g465q92q554g23g41q47").print()
}

class SolutionD {
    fun possiblyEquals(s1: String, s2: String): Boolean {

        fun helper(s: String): ArrayList<String> {
            val ans = ArrayList<String>()
            val sb = StringBuilder()
            var i = 0
            val c = StringBuilder()
            while (i in s.indices) {
                if (s[i] in 'a'..'z') {
                    if (c.isNotEmpty()) {
                        ans.add(c.toString())
                        c.clear()
                    }
                    sb.append(s[i])
                } else if (s[i] in '1'..'9') {
                    if (sb.isNotEmpty()) {
                        ans.add(sb.toString())
                        sb.clear()
                    }
                    c.append(s[i])
                }
                i++
            }
            if (sb.isNotEmpty()) {
                ans.add(sb.toString())
                sb.clear()
            }
            if (c.isNotEmpty()) {
                ans.add(c.toString())
                c.clear()
            }
            return ans
        }

        fun getNum(i: String): HashSet<Int> {
            val ans = hashSetOf<Int>()
            ans.add(i.toInt())
            if (i.length == 2) {
                ans.add((i[0] - '0') + (i[1] - '0'))
            }
            if (i.length == 3) {
                ans.add((i[0] - '0') + (i[1] - '0') + (i[2] - '0'))
                ans.add((i[0] - '0') + (i[1] - '0') * 10 + (i[2] - '0'))
                ans.add((i[0] - '0') * 10 + (i[1] - '0') + (i[2] - '0'))
            }
            return ans
        }

        val res = ArrayList<StringBuilder>()

        fun dfs(arr: ArrayList<String>, i: Int = 0, cur: ArrayList<StringBuilder> = ArrayList()) {
            if (i !in arr.indices) {
                res.addAll(cur)
                return
            }
            if (arr[i].toIntOrNull() == null) {
                for (j in cur.indices) {
                    cur[j].append(arr[i])
                }
                if (cur.isEmpty()) cur.add(StringBuilder(arr[i]))
                dfs(arr, i + 1, cur)
            } else if (arr[i].toIntOrNull() != null) {
                val set = getNum(arr[i])
                set.forEach {
                    val next = ArrayList<StringBuilder>()
                    cur.forEach {
                        next.add(StringBuilder(it))
                    }
                    if (next.isEmpty()) {
                        next.add(StringBuilder())
                    }
                    for (j in next.indices) {
                        repeat(it) {
                            next[j].append('*')
                        }
                    }
                    dfs(arr, i + 1, next)
                }
            }
        }

        val source1 = helper(s1)
        val source2 = helper(s2)

        dfs(source1)
        val res1 = res.map { it.toString() }
        res.clear()
        dfs(source2)
        val res2 = res.map { it.toString() }

        val map1 = HashMap<Int, ArrayList<String>>()
        res1.forEach {
            map1[it.length] = map1.getOrDefault(it.length, arrayListOf())
            map1[it.length]!!.add(it)
        }
        val map2 = HashMap<Int, ArrayList<String>>()
        res2.forEach {
            map2[it.length] = map2.getOrDefault(it.length, arrayListOf())
            map2[it.length]!!.add(it)
        }

        fun check(s1: String, s2: String): Boolean {
            for (i in s1.indices) {
                if (s1[i] == s2[i] || s1[i] == '*' || s2[i] == '*') continue
                return false
            }
            return true
        }

//         res1.joinToString().print()
//         res2.joinToString().print()

        for (key in map1.keys) {
            map1[key]?.forEach { a ->
                map2[key]?.forEach { b ->
                    if (check(a, b)) return true
                }
            }
        }
        return false
    }
}