package com.coder.labsystem.core.user.repository;

import com.coder.labsystem.constant.CollectionName;
import com.coder.labsystem.model.entity.User;
import com.coder.labsystem.model.entity.UserExtendInfo;
import com.coder.labsystem.util.MongoOperatorUtil;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.*;

/**
 * @author : JQK
 * @date : 2021-04-20 16:32
 * @description :
 */
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private final MongoTemplate mongoTemplate;

    public CustomUserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean insertUserExtendInfo(String username, UserExtendInfo extendInfo) {
        Query query = query(where("username").is(username));
        Update update = MongoOperatorUtil.beanToUpdate(extendInfo);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class, CollectionName.USER_COLLECTION_NAME);
        return updateResult.getModifiedCount() > 0 ? true : false;
    }

    @Override
    public UserExtendInfo findByUsername(String username) {
        return null;
    }
}
