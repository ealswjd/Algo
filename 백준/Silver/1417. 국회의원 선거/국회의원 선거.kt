import java.util.PriorityQueue

/**
 * https://www.acmicpc.net/problem/1417
 * 다솜이가 매수해야하는 사람의 최솟값을 출력하는 프로그램을 작성
  */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt() // 후보 수
    var d = readLine().toInt() // 다솜이의 표(다솜이는 기호 1번)
    val pq = PriorityQueue<Int>(compareByDescending { it }) // 최대힙
    var minCnt = 0 // 다솜이가 매수해야하는 사람의 최솟값

    repeat(n-1) {
        val cnt = readLine().toInt()
        pq.offer(cnt)
    }

    while (pq.isNotEmpty() && pq.peek() >= d) {
        val top = pq.poll() - 1
        
        pq.offer(top)
        d++
        minCnt++
    }

    close()
    print(minCnt)
}
