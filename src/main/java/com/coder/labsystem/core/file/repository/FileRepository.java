package com.coder.labsystem.core.file.repository;

import com.coder.labsystem.model.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : JQK
 * @date : 2021-04-22 15:25
 * @description :
 */
@Repository
public interface FileRepository extends MongoRepository<File, String> {

    /**
     *
     * @param id
     * @return
     */

}
