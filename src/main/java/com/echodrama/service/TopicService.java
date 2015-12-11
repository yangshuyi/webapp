package com.echodrama.service;

import java.util.List;

import com.echodrama.model.Topic;

public interface TopicService {

    public List<Topic> queryTopicFromMongo() throws Exception;

    public List<Topic> queryTopicFromDB() throws Exception;
}
