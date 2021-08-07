package hcy.gym;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class ApiTest {

    @Test
    void apiRequestTest() throws IOException, JSONException {

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode("32hmVfmyuzAN/Keh22jiPFEVvGM/v4Ghvm07M0ZFAx0KyNwGs2QjybzOZZd8lyrrx+g4PBVr9B0cQEBvk9mqyw==", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8"));

        System.out.println(urlBuilder.toString());

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code : " + connection.getResponseCode());

        BufferedReader bufferedReader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder result = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }

        bufferedReader.close();
        connection.disconnect();

        System.out.println(result.toString());

        JSONObject jsonObject = new JSONObject(result.toString());

        System.out.println(jsonObject.toString());


    }

    @Test
    void streamTest(){
        List<Student> students = Arrays.asList(new Student("사람1", 20), new Student("사람2", 30));
        OptionalDouble average = students.stream()
                .mapToInt(Student::getAge)
                .average();
        System.out.println(average.getAsDouble());

        students.stream()
                .sorted((a, b) -> Integer.compare(a.age, b.age))
                .forEach(s -> System.out.println(s));

    }

    static class Student implements Comparable<Student>{
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.compare(o.age, age);
        }

        @Override
        public String toString() {
            return "name : " + name + " age : " + age;
        }
    }

}
