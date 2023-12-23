package com.example.mdi.service.impl;

import com.example.mdi.configuration.ClientNames;
import com.example.mdi.configuration.DBContextHolder;
import com.example.mdi.configuration.annotation.ReadOnly;
import com.example.mdi.dao.MDIRepo;
import com.example.mdi.entity.MDIEntity;
import com.example.mdi.service.MDIRequestService;
import com.example.mdi.shared.dto.PostRequestDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MDIRequestServiceImpl implements MDIRequestService {


    final private MDIRepo mdiRepo;

    public MDIRequestServiceImpl(MDIRepo mdiRepo) {
        this.mdiRepo = mdiRepo;
    }

    @Override
    public PostRequestDto post(PostRequestDto postRequestDto) {
        MDIEntity mdiEntity = new MDIEntity();
        BeanUtils.copyProperties(postRequestDto, mdiEntity);
        MDIEntity savedEntity = mdiRepo.save(mdiEntity);
        PostRequestDto returnValue = new PostRequestDto();
        BeanUtils.copyProperties(savedEntity, returnValue);
        return returnValue;
    }

    @Override
    public PostRequestDto get(Long id)
    {
        MDIEntity mdiEntity = mdiRepo.findById(id).get();
        PostRequestDto returnValue = new PostRequestDto();
        BeanUtils.copyProperties(mdiEntity, returnValue);
        return returnValue;
    }
}
