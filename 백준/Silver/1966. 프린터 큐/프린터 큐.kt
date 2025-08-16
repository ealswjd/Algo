/* https://www.acmicpc.net/problem/1966
 * 문제: 프린터 큐
 */
data class Document(val index: Int, val priority: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine().toInt() // 테스트 케이스 개수
    val result = StringBuilder() // 결과 저장

    repeat(tc) {
        val printer = ArrayDeque<Document>() // 프린터 큐
        
        // 문서의 개수 n, 몇 번째로 인쇄되었는지 궁금한 문서의 현재 위치 m이 주어진다.
        val (n, m) = readLine().split(" ").map { it.toInt() }
        // n개 문서의 중요도가 차례대로 주어진다. 중요도는 1 이상 9 이하의 정수.
        val priorities = readLine().split(" ").mapIndexed { idx, p ->
            printer.add(Document(idx, p.toInt()))
            p.toInt()
        }.toIntArray()

        // 각 테스트 케이스에 대해 문서가 몇 번째로 인쇄되는지 출력
        val order = getOrder(n, m, priorities, printer)
        result.append("${order}\n")
    }

    print(result)
    close()
}

fun getOrder(n: Int, m: Int, priorities: IntArray, printer: ArrayDeque<Document>): Int {
    priorities.sortDescending()
    var order = 1
    var idx = 0

    while (printer.isNotEmpty()) {
        val cur = printer.removeFirst()

        if(priorities[idx] > cur.priority) {
            // 현재 문서의 중요도가 가장 높지 않으면 가장 뒤에 재배치
            printer.add(cur)
        }
        else {
            // 현재 문서의 인덱스가 m과 같으면 현재 순서 반환
            if(cur.index == m) return order

            idx++   // 현재 문서의 중요도가 가장 높으면 인쇄, 다음으로 높은 중요도로 이동
            order++ // 순서 증가
        }
    }

    return order
}

