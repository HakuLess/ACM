package leetcode.contest.c414

class SolutionA {
    fun convertDateToBinary(date: String): String {

        val (year, month, day) = date.split("-")

        val yearBinary = year.toInt().toString(2)
        val monthBinary = month.toInt().toString(2)
        val dayBinary = day.toInt().toString(2)

        return "$yearBinary-$monthBinary-$dayBinary"
    }
}