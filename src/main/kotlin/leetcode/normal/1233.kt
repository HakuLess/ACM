package leetcode.normal

import utils.print

fun main() {
    val s = Solution1233()
    s.removeSubfolders(arrayOf("/a", "/a/b", "/c/d", "/c/d/e", "/c/f")).joinToString().print()
    s.removeSubfolders(arrayOf("/a", "/a/b/c", "/a/b/d")).joinToString().print()
}

class Solution1233 {
    fun removeSubfolders(folder: Array<String>): List<String> {
        folder.sort()
        val set = HashSet<String>()
        val ans = ArrayList<String>()
        folder.forEach {
            val inSet = set.any { parent -> it.startsWith(parent) }
            if (!inSet) {
                set.add("${it}/")
                ans.add(it)
            }
        }
        return ans.toList()
    }
}