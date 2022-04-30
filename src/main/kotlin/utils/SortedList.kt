package utils

class SortedList<T : Comparable<T>> {

    val valueList = ArrayList<T>()

    fun insert(value: T) {
        val index = valueList.binarySearch(value)
        if (index < 0) {
            // 无当前值，按index返回值插入
            valueList.add(-index - 1, value)
        } else {
            valueList.add(index, value)
        }
    }

    fun largerThanAndEqual(value: T): Int {
        if (valueList.isEmpty()) return 0
        if (value > valueList.last()) return 0
        if (value < valueList.first()) return valueList.size
        // 获取最小的 大于value的index
        val minIndex = biMin(0L, valueList.lastIndex.toLong()) {
            valueList[it.toInt()] >= value
        }.toInt()
        return valueList.size - minIndex
    }

    fun print() {
        println(valueList.joinToString())
    }
}