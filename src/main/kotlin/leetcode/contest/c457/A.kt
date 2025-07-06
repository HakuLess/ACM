package leetcode.contest.c457

class SolutionA {
    fun validateCoupons(code: Array<String>, businessLine: Array<String>, isActive: BooleanArray): List<String> {
        val validBus = listOf("electronics", "grocery", "pharmacy", "restaurant")
        val order = validBus.withIndex().associate { it.value to it.index }

        val regex = Regex("^[a-zA-Z0-9_]+$")

        val list = ArrayList<Pair<String, String>>()
        for (i in code.indices) {
            if (code[i].isNotEmpty() && regex.matches(code[i]) && businessLine[i] in order && isActive[i]) {
                list.add(Pair(businessLine[i], code[i]))
            }
        }

        list.sortWith(compareBy({ order[it.first] }, { it.second }))

        return list.map { it.second }
    }
}