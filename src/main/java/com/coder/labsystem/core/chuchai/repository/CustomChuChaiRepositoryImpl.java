package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.model.entity.ChuChai;
import com.coder.labsystem.util.MongoOperatorUtil;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author : JQK
 * @date : 2021-04-20 20:41
 * @description :
 */
@Repository
public class CustomChuChaiRepositoryImpl implements CustomChuChaiRepository {

    private final MongoTemplate mongoTemplate;

    public CustomChuChaiRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public boolean updateChuChai(ChuChai chuChai) {
        Query query = Query.query(Criteria.where("id").is(chuChai.getId()));
        Update update = MongoOperatorUtil.beanToUpdate(chuChai);
        UpdateResult result = mongoTemplate.updateFirst(query, update, ChuChai.class);
        return result.getModifiedCount() > 0 ? true : false;
    }
}
