package leetcode.normal

class FindSumPairs(val nums1: IntArray, val nums2: IntArray) {

    private val freqMap = HashMap<Int, Int>()

    init {
        for (num in nums2) {
            freqMap[num] = freqMap.getOrDefault(num, 0) + 1
        }
    }

    fun add(index: Int, `val`: Int) {
        val oldValue = nums2[index]
        freqMap[oldValue] = freqMap.getValue(oldValue) - 1
        if (freqMap[oldValue] == 0) freqMap.remove(oldValue)

        nums2[index] += `val`
        val newValue = nums2[index]
        freqMap[newValue] = freqMap.getOrDefault(newValue, 0) + 1
    }

    fun count(tot: Int): Int {
        var result = 0
        for (num in nums1) {
            val need = tot - num
            result += freqMap.getOrDefault(need, 0)
        }
        return result
    }

}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * var obj = FindSumPairs(nums1, nums2)
 * obj.add(index,`val`)
 * var param_2 = obj.count(tot)
 */