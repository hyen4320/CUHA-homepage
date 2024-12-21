package CUHA.homepage.controller;

import CUHA.homepage.service.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("API")
@RequiredArgsConstructor
public class APIController {

   private final APIService apiService;

    @GetMapping("ctftime")
    public List<Map<String,String>> ctfTime() throws IOException {
        return apiService.ctfTimeGetApi();
    }


}
