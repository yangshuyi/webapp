package com.echodrama.mongo.repository;

import java.util.List;

import com.echodrama.mongo.collection.TopicDoc;

/**
 * Created by shuyi on 15/11/25.
 */
public interface TopicRepository {
    public List<TopicDoc> queryTopic();
}
