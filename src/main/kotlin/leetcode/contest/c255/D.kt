package leetcode.contest.c255

import utils.print

fun main() {
    val s = Solution5853()
//    s.recoverArray(3, intArrayOf(-3, -2, -1, 0, 0, 1, 2, 3)).print()
    s.recoverArray(4, intArrayOf(0, 0, 5, 5, 4, -1, 4, 9, 9, -1, 4, 3, 4, 8, 3, 8)).print()
}

class Solution5853 {
    fun recoverArray(n: Int, sums: IntArray): IntArray {
        sums.sort()
        fun checkDiff(diff: Int, arr: ArrayList<Int>, pos: Boolean): ArrayList<Int> {
            if (pos && diff !in arr) return arrayListOf()
            if (!pos && -diff !in arr) return arrayListOf()
            var l = 0
            var r = 0
            val s = arrayListOf<Int>()
            val t = arrayListOf<Int>()
            val used = BooleanArray(arr.size)
            while (l in arr.indices) {
                if (used[l]) {
                    l++
                    continue
                }
                s.add(arr[l])
                used[l] = true
                while (used[r] || arr[r] != arr[l] + diff) {
                    r++
                    if (r !in arr.indices) return arrayListOf<Int>()
                }
                t.add(arr[r])
                used[r] = true
                l++
            }
            return if (pos) s else t
        }

        val ans = ArrayList<Int>()
        val tmp = ArrayList<Int>()
        fun dfs(cur: ArrayList<Int>) {
            if (cur.isEmpty() || ans.isNotEmpty()) return
            if (tmp.size == n) {
                ans.addAll(tmp)
                return
            }
            val d = cur[cur.lastIndex] - cur[cur.lastIndex - 1]
            tmp.add(d)
            dfs(checkDiff(d, cur, true))
            tmp.remove(d)
            tmp.add(-d)
            dfs(checkDiff(d, cur, false))
            tmp.remove(-d)
        }

        dfs(ArrayList(sums.sorted()))
        return ans.toIntArray()
    }

    /**
     * 暴力解法，尝试所有可能...
     * */
//    fun recoverArray(n: Int, sums: IntArray): IntArray {
//        val target = HashMap<Int, Int>()
//        sums.forEach {
//            target[it] = target.getOrDefault(it, 0) + 1
//        }
//        var ans = IntArray(0)
//        fun dfs(cur: ArrayList<Int>, index: Int, curMap: HashMap<Int, Int>) {
//            if (ans.isNotEmpty()) return
//            if (curMap.keys.any { curMap.getOrDefault(it, 0) > target.getOrDefault(it, 0) })
//                return
//            if (cur.size == n) ans = cur.toIntArray()
//            for (i in index until sums.size) {
//                cur.add(sums[i])
//                val diffMap = HashMap<Int, Int>()
//                diffMap[sums[i]] = 1
//                curMap.forEach { key, value ->
//                    repeat(value) {
//                        diffMap[key + sums[i]] = diffMap.getOrDefault(key + sums[i], 0) + 1
//                    }
//                }
//                diffMap.forEach { key, value ->
//                    curMap[key] = curMap.getOrDefault(key, 0) + value
//                }
//                dfs(cur, i + 1, curMap)
//                diffMap.forEach { key, value ->
//                    curMap[key] = curMap.getOrDefault(key, 0) - value
//                }
//                cur.remove(sums[i])
//            }
//        }
//        dfs(arrayListOf(), 0, hashMapOf())
//        return ans
//    }
}