package leetcode.contest.c428

class SolutionB {
    fun maxAmount(initialCurrency: String, pairs1: List<List<String>>, rates1: DoubleArray, pairs2: List<List<String>>, rates2: DoubleArray): Double {

        fun buildGraph(pairs: List<List<String>>, rates: DoubleArray): Map<String, MutableMap<String, Double>> {
            val graph = mutableMapOf<String, MutableMap<String, Double>>()
            for (i in pairs.indices) {
                val start = pairs[i][0]
                val target = pairs[i][1]
                val rate = rates[i]
                graph.computeIfAbsent(start) { mutableMapOf() }[target] = rate
                graph.computeIfAbsent(target) { mutableMapOf() }[start] = 1.0 / rate
            }
            return graph
        }

        fun dfsMax(graph: Map<String, MutableMap<String, Double>>, start: String, amount: Double): MutableMap<String, Double> {
            val maxAmounts = mutableMapOf<String, Double>()
            fun dfs(node: String, currentAmount: Double) {
                if (currentAmount <= (maxAmounts[node] ?: 0.0)) return
                maxAmounts[node] = currentAmount
                for ((neighbor, rate) in graph[node] ?: emptyMap()) {
                    dfs(neighbor, currentAmount * rate)
                }
            }
            dfs(start, amount)
            return maxAmounts
        }

        val graphDay1 = buildGraph(pairs1, rates1)
        val graphDay2 = buildGraph(pairs2, rates2)

        val maxAfterDay1 = dfsMax(graphDay1, initialCurrency, 1.0)

        var maxAmount = 1.0
        for ((currency, amountDay1) in maxAfterDay1) {
            val maxAfterDay2 = dfsMax(graphDay2, currency, amountDay1)
            maxAmount = maxOf(maxAmount, maxAfterDay2[initialCurrency] ?: 0.0)
        }

        return maxAmount
    }
}