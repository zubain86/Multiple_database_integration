package com.example.mdi.manager;

import com.example.mdi.shared.dto.PostRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

public interface MDIRequestManager {
    PostRequestDto post(PostRequestDto postRequestDto);

    PostRequestDto get(Long id);
}
