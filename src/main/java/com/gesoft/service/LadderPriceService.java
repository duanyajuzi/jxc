package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.LadderPriceDAO;
import com.gesoft.model.LadderPriceModel;

@Service
@Transactional
public class LadderPriceService extends EntityService<LadderPriceModel, Long> {

    @Resource
    private LadderPriceDAO ladderPriceDAO;

    @Override
    protected EntityDAO<LadderPriceModel, Long> getEntityDao() {
        return this.ladderPriceDAO;
    }
}
