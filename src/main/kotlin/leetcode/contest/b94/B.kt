package leetcode.contest.b94

class SolutionB {
    fun topStudents(
        positive_feedback: Array<String>,
        negative_feedback: Array<String>,
        report: Array<String>,
        student_id: IntArray,
        k: Int
    ): List<Int> {
        val pos = positive_feedback.toHashSet()
        val neg = negative_feedback.toHashSet()
        return report.mapIndexed { index, it ->
            val score = it.split(" ").let {
                var a = 0
                it.forEach {
                    if (it in pos) a += 3
                    else if (it in neg) a--
                }
                a
            }
            Pair(index, score)
        }.sortedWith(compareBy({ -it.second }, { student_id[it.first] })).take(k).map {
            student_id[it.first]
        }
    }
}