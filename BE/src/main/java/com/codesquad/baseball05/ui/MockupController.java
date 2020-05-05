package com.codesquad.baseball05.ui;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MockupController {

    @GetMapping("/")
    public Object test() {
        List<Test> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Test test = new Test("시험" + i);
            list.add(test);
        }
        return list;
    }
}
