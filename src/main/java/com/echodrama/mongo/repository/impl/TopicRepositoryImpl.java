package com.echodrama.mongo.repository.impl;

import javax.annotation.Resource;

import java.util.List;

import com.echodrama.mongo.collection.TopicDoc;
import com.echodrama.mongo.repository.TopicRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by shuyi on 15/11/25.
 */
@Service("topicRepository")
public class TopicRepositoryImpl implements TopicRepository {
    @Resource
    private MongoTemplate topicMongo;

    public List<TopicDoc> queryTopic(){
        return topicMongo.findAll(TopicDoc.class);
    }
}
