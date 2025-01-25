import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1713
public class Main {
    private static int N; // 사진틀의 개수
    private static int M; // 총 추천 횟수
    private static int[] numbers; // 추천받은 학생 번호
    

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        List<Student> list = new ArrayList<>(); // 사진틀
        Set<Integer> numberSet = new HashSet<>(); // 사진틀에 있는 학생 번호

        for(int i=0; i<M; i++) {

            // 현재 사진이 게시된 학생이 다른 학생의 추천을 받은 경우에는 추천받은 횟수만 증가
            if(numberSet.contains(numbers[i])) {
                incrementCount(list, numbers[i]);
            }
            // 첫 게시
            else {
                // 비어있는 사진틀이 없는 경우 기존 학생 삭제
                if (list.size() >= N) {
                    removeStudent(list, numberSet);
                }

                // 추천받은 학생 게시
                numberSet.add(numbers[i]);
                list.add(new Student(numbers[i], 1, i));
            }

        }//for

        // 최종 결과물
        List<Integer> result = new ArrayList<>();
        for(Student student : list) {
            result.add(student.number);
        }
        Collections.sort(result);

        StringBuilder ans = new StringBuilder();
        for(int n : result) {
            ans.append(n).append(' ');
        }

        System.out.print(ans);
    }//sol


    private static void incrementCount(List<Student> list, int num) {
        for(Student student : list) {
            if (student.number == num) {
                student.count++;
                break;
            }
        }
    }//incrementCount


    private static void removeStudent(List<Student> list, Set<Integer> numberSet) {
        // 현재까지 추천 받은 횟수가 가장 적은 학생, 그러한 학생들 중 게시된 지 가장 오래된 사진을 삭제
        Collections.sort(list); 
        numberSet.remove(list.get(0).number);
        list.remove(0);
    }//removeStudent

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 사진틀의 개수
        M = Integer.parseInt(br.readLine()); // 총 추천 횟수

        numbers = new int[M]; // 추천받은 학생 번호

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


    private static class Student implements Comparable<Student> {
        int number; // 학생 번호
        int count; // 추천 횟수
        int time; // 추천 받은 시간

        public Student(int number, int count, int time) {
            this.number = number;
            this.count = count;
            this.time = time;
        }

        @Override
        public int compareTo(Student o) {
            if(this.count == o.count) return this.time - o.time;
            return this.count - o.count;
        }
    }//Student
    

}//class