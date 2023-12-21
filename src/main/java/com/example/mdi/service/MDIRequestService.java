package com.example.mdi.service;

import com.example.mdi.shared.dto.PostRequestDto;

public interface MDIRequestService {

    PostRequestDto post(PostRequestDto postRequestDto);

    PostRequestDto get(Long id);
}
