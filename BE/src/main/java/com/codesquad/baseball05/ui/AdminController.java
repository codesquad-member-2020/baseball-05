package com.codesquad.baseball05.ui;

import com.codesquad.baseball05.application.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/resetMatch")
    public ResponseEntity<Object> resetMatch(@RequestParam Long matchId) {
        return new ResponseEntity<>(adminService.resetMatchUser(matchId), HttpStatus.OK);
    }
}
