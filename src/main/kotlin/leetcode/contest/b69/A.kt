package leetcode.contest.b69

class SolutionA {
    fun capitalizeTitle(title: String): String {
        return title.split(' ').map {
            val tmp = StringBuilder()
            tmp.append(it.toLowerCase())
            if (it.length > 2) {
                tmp[0] = tmp[0].toUpperCase()
            }
            tmp.toString()
        }.joinToString(" ")
    }
}