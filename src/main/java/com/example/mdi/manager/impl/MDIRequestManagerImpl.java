package com.example.mdi.manager.impl;

import com.example.mdi.configuration.ClientNames;
import com.example.mdi.configuration.annotation.ReadOnly;
import com.example.mdi.configuration.annotation.WriteOnly;
import com.example.mdi.manager.MDIRequestManager;
import com.example.mdi.service.MDIRequestService;
import com.example.mdi.shared.dto.PostRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
@Transactional
@Component
public class MDIRequestManagerImpl implements MDIRequestManager {

    private MDIRequestService mdiRequestService;

    public MDIRequestManagerImpl(MDIRequestService mdiRequestService) {
        this.mdiRequestService = mdiRequestService;
    }

    @Override
    @WriteOnly
    public PostRequestDto post(PostRequestDto postRequestDto) {
        return mdiRequestService.post(postRequestDto);
    }

    @Override
    @ReadOnly
    public PostRequestDto get(Long id)
    {
        return mdiRequestService.get(id);
    }
}
