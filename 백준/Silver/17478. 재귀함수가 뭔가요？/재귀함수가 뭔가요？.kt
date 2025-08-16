// https://www.acmicpc.net/problem/17478
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt() // 재귀 횟수
    val result = StringBuilder() // 결과 저장
    val underbar = StringBuilder() // 언더바 저장

    result.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n")
    dfs(n, 0, result)

    print(result)
    close()
}

fun dfs(n: Int, depth: Int, result: StringBuilder) {
    val underbar = "____".repeat(depth)

    if(depth == n) {
        result.append("${underbar}\"재귀함수가 뭔가요?\"\n")
        result.append("${underbar}\"재귀함수는 자기 자신을 호출하는 함수라네\"\n")
        result.append("${underbar}라고 답변하였지.\n")
        return
    }

    result.append("${underbar}\"재귀함수가 뭔가요?\"\n")
    result.append("${underbar}\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n")
    result.append("${underbar}마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n")
    result.append("${underbar}그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n")

    dfs(n, depth+1, result)

    result.append("${underbar}라고 답변하였지.\n")
}
