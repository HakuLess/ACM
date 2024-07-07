package leetcode.contest.c405


/**
 * 字典树，连续 subString 优化
 * */
class SolutionD {
    fun minimumCost(target: String, words: Array<String>, costs: IntArray): Int {
        val n = target.length
        val inf = Int.MAX_VALUE
        val dp = IntArray(n + 1) { inf }
        dp[0] = 0

        val trie = Trie()

        val ids = IntRange(0, words.size - 1).toList().toTypedArray()
        // 对id进行排序
        ids.sortBy { -costs[it] }

        for (i in ids) {
            trie.insert(words[i], costs[i])
        }

        for (i in 0 until n) {
            if (dp[i] == inf) continue

            var node = trie.getRoot()
            for (j in i until n) {
                node = node.children[target[j]] ?: break
                if (node.word != null) {
                    dp[j + 1] = minOf(dp[j + 1], dp[i] + node.cost)
                }
            }
        }

        return if (dp[n] == inf) -1 else dp[n]
    }
}

class TrieNode {
    val children: MutableMap<Char, TrieNode> = mutableMapOf()
    var cost: Int = Int.MAX_VALUE
    var word: String? = null
}

class Trie {
    private val root = TrieNode()

    fun insert(word: String, cost: Int) {
        var node = root
        for (char in word) {
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        node.cost = cost
        node.word = word
    }

    fun getRoot() = root
}