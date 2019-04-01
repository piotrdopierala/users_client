package pl.dopierala.usersclient;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class Client {

    //@EventListener(ApplicationReadyEvent.class)
    public void startup() {
    }

    public static List<User> getDataFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> exchange = restTemplate.exchange(
                "http://localhost:8080/users",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                User[].class
        );

        User[] body = exchange.getBody();
        return Arrays.asList(body);
        //Stream.of(body).forEach(System.out::println);
    }

    public static void deleteUser(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://localhost:8080/deleteUser?id=" + id,
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                String.class);

        System.out.println(exchange.getBody());
    }

    public static void addUserToAPI(User userToAdd) {
        RestTemplate restTemplate = new RestTemplate();

        JSONObject json = new JSONObject();
        json.put("name", userToAdd.getName());
        json.put("lastName", userToAdd.getLastName());
        json.put("age", userToAdd.getAge());
        json.put("sex", userToAdd.getSex());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE,
                "application/json"
        );

        String jsonString = json.toString();

        HttpEntity httpEntity = new HttpEntity(jsonString, httpHeaders);

        restTemplate.exchange(
                "http://localhost:8080/saveUserBody",
                HttpMethod.PUT,
                httpEntity,
                Void.class);
    }
}
