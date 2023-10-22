package leetcode.contest.c368

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumChanges("abcac", 2).print()
//    s.minimumChanges("abcdef", 2).print()
//    s.minimumChanges("aabbaa", 3).print()
//    s.minimumChanges("aac", 1).print()
//    s.minimumChanges("abcc", 1).print()
}

class SolutionD {
    fun minimumChanges(s: String, k: Int): Int {

        val checkSeen = HashMap<String, Int>()
        fun check(left: Int, right: Int): Int {
            val key = "$left, $right"
            if (key in checkSeen) return checkSeen[key]!!
            // 长度为n
            val n = right - left + 1
            var ans = n
            for (i in 1 until n) {
                if (n % i == 0) {
                    // 可分为i组
                    val arr = Array<ArrayList<Char>>(i) { arrayListOf() }
                    for (j in 0 until n) {
                        arr[j % i].add(s[left + j])
                    }
                    var tmp = 0
                    arr.forEach {
//                        it.joinToString().print()
                        for (i in 0 until it.size / 2) {
                            if (it[i] != it[it.size - i - 1]) {
                                tmp++
                            }
                        }
                    }
                    ans = minOf(ans, tmp)
                }
            }
            return ans.also {
                checkSeen[key] = it
//                println("${s.substring(left, right + 1)} 需要修改 $it")
            }
        }

        val seen = HashMap<String, Int>()
        fun dfs(left: Int, right: Int, split: Int): Int {
            val key = "$left..$right,$split"
            if (key in seen) return seen[key]!!
            if (split == 1) {
                return check(left, right).also {
//                    println("in $left..$right with $it")
                }
            }
            var ans = 200
            for (i in left + 1 until right - 1) {
                var tmp = 0
                tmp += dfs(left, i, 1)
                tmp += dfs(i + 1, right, split - 1)
                ans = minOf(ans, tmp)
            }
            return ans.also {
                seen[key] = it
//                println("$left..$right with $it")
            }
        }
        return dfs(0, s.lastIndex, k)
    }
}