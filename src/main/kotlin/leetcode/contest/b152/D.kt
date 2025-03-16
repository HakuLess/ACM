package leetcode.contest.b152

class SolutionD {

    private lateinit var nums: IntArray
    private lateinit var adj: Array<MutableList<Pair<Int, Int>>>
    private var maxLen = 0
    private var minNodes = Int.MAX_VALUE

    fun longestSpecialPath(edges: Array<IntArray>, nums: IntArray): IntArray {
        this.nums = nums
        val n = nums.size
        adj = Array(n) { mutableListOf<Pair<Int, Int>>() }
        for (edge in edges) {
            val u = edge[0]
            val v = edge[1]
            val length = edge[2]
            adj[u].add(v to length)
            adj[v].add(u to length)
        }

        dfs(0, -1, 0, 1, HashMap<Int, Int>().withDefault { 0 }, 0)

        return intArrayOf(maxLen, minNodes)
    }

    private fun dfs(
        node: Int,
        parent: Int,
        currentLen: Int,
        nodeCount: Int,
        freq: MutableMap<Int, Int>,
        duplicates: Int
    ) {
        val value = nums[node]
        val currentCount = freq.getOrDefault(value, 0)
        freq[value] = currentCount + 1
        var newDuplicates = duplicates

        when (currentCount + 1) {
            2 -> newDuplicates += 1
            in 3..Int.MAX_VALUE -> {
                // This value's count is now >=3, which makes duplicates exceed 1
                freq[value] = currentCount // Revert before returning
                return
            }
        }

        if (newDuplicates > 1) {
            freq[value] = currentCount
            return
        }

        // Update the maximum length and minimum nodes
        if (currentLen > maxLen || (currentLen == maxLen && nodeCount < minNodes)) {
            maxLen = currentLen
            minNodes = nodeCount
        }

        // Explore children
        for ((child, length) in adj[node]) {
            if (child == parent) continue
            dfs(child, node, currentLen + length, nodeCount + 1, freq, newDuplicates)
        }

        // Backtrack
        freq[value] = currentCount
    }
}