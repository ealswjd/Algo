 // https://www.acmicpc.net/problem/26070

 fun main() = with(System.`in`.bufferedReader()) {
     // 치킨, 피자, 버거를 먹고 싶은 곰곰이의 수
     val gom = readLine().split(" ").map { it.toInt() }.toIntArray()
     // 치킨, 피자, 버거의 식권 장수
     val ticket = readLine().split(" ").map { it.toInt() }.toIntArray()
     // 곰곰이의 최대 마릿수를 구하는 함수 호출
     val result = getTotal(gom, ticket)

     print(result)
     close()
 }


 fun getTotal(gom: IntArray, ticket: IntArray): Long {
     var total = 0L // 곰곰이의 최대 마릿수
     val n = 3 // 음식의 종류 수

     for (i in 0 until n * 2) {
         total += eatFood(gom, ticket, i % 3)
     }

     return total
 }


 fun eatFood(gom: IntArray, ticket: IntArray, i: Int): Long {
     var cnt = 0L // 학식을 먹을 수 있는 곰곰이의 수

     if(gom[i] >= ticket[i]) { // 식권보다 곰곰이의 수가 많거나 같음
         cnt += ticket[i] // 해당 식권의 수만큼 곰곰이 마릿수 증가
         gom[i] -= ticket[i] // 해당 곰곰이의 수 감소
         ticket[i] = 0 // 해당 식권은 모두 사용
     }
     else { // 식권보다 곰곰이의 수가 적음
         cnt += gom[i] // 곰곰이의 수만큼 마릿수 증가
         ticket[i] -= gom[i] // 해당 식권의 수 감소
         gom[i] = 0 // 해당 곰곰이는 모두 먹을 수 있음

         if (ticket[i] >= 3) ticket[(i+1) % 3] += ticket[i] / 3
     }

     return cnt
 }
