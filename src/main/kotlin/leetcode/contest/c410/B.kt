package leetcode.contest.c410

class SolutionB {
    fun countGoodNodes(edges: Array<IntArray>): Int {
        val n = edges.maxOf { maxOf(it[0], it[1]) } + 1
        val tree = Array(n) { mutableListOf<Int>() }
        for (edge in edges) {
            tree[edge[0]].add(edge[1])
            tree[edge[1]].add(edge[0])
        }

        val subtreeSize = IntArray(n)

        var goodNodesCount = 0

        fun dfs(node: Int, parent: Int): Int {
            var size = 1
            var isGood = true
            var firstChildSize = -1

            for (child in tree[node]) {
                if (child != parent) {
                    val childSize = dfs(child, node)
                    size += childSize

                    if (firstChildSize == -1) {
                        firstChildSize = childSize
                    } else if (firstChildSize != childSize) {
                        isGood = false
                    }
                }
            }

            if (isGood) {
                goodNodesCount++
            }

            subtreeSize[node] = size
            return size
        }

        dfs(0, -1)

        return goodNodesCount
    }
}