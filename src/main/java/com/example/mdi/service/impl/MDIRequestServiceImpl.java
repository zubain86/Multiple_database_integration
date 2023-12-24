package com.example.mdi.service.impl;

import com.example.mdi.configuration.ClientNames;
import com.example.mdi.configuration.DBContextHolder;
import com.example.mdi.configuration.annotation.ReadOnly;
import com.example.mdi.dao.MDIRepo;
import com.example.mdi.entity.MDIEntity;
import com.example.mdi.service.MDIRequestService;
import com.example.mdi.shared.dto.PostRequestDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MDIRequestServiceImpl implements MDIRequestService {


    final private MDIRepo mdiRepo;

    final private EntityManager entityManager;

    public MDIRequestServiceImpl(MDIRepo mdiRepo, EntityManager entityManager) {
        this.mdiRepo = mdiRepo;
        this.entityManager = entityManager;
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
//        MDIEntity mdiEntity = mdiRepo.findById(id).get();
        String sqlQuery = "SELECT * FROM mdientity WHERE id = :id";
        Query query = entityManager.createNativeQuery(sqlQuery, MDIEntity.class);
        query.setParameter("id", id);
        MDIEntity mdiEntity = (MDIEntity) query.getSingleResult();
        PostRequestDto returnValue = new PostRequestDto();
        BeanUtils.copyProperties(mdiEntity, returnValue);
        return returnValue;
    }
}
