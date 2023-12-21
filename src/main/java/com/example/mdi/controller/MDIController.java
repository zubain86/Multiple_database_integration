package com.example.mdi.controller;

import com.example.mdi.manager.MDIRequestManager;
import com.example.mdi.shared.dto.PostRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MDIController {

    private MDIRequestManager mdiRequestManager;

    public MDIController(MDIRequestManager mdiRequestManager) {
        this.mdiRequestManager = mdiRequestManager;
    }

    @PostMapping("/post")
    public ResponseEntity<PostRequestDto> post(@RequestBody PostRequestDto postRequestDto) {
        PostRequestDto response = mdiRequestManager.post(postRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostRequestDto> get(@PathVariable Long id)
    {
        PostRequestDto postRequestDto = mdiRequestManager.get(id);
        return ResponseEntity.ok(postRequestDto);
    }
}
