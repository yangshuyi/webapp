package com.echodrama.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.echodrama.dao.TopicDao;
import com.echodrama.model.Topic;
import com.echodrama.mongo.collection.TopicDoc;
import com.echodrama.mongo.repository.TopicRepository;
import com.echodrama.pojo.TopicPojo;
import com.echodrama.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("topicService")
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;

    @Autowired
    private TopicRepository topicRepository;



    @Override
    public List<Topic> queryTopicFromMongo() throws Exception{
        List<TopicPojo> topicPojoList = topicDao.listAll(TopicPojo.class);
        List<Topic> topicList = new ArrayList<Topic>();

        for(TopicPojo pojo: topicPojoList){
            Topic topic =new Topic();
            BeanUtils.copyProperties(pojo, topic);
            topicList.add(topic);
        }

        return topicList;
    }


    @Override
    public List<Topic> queryTopicFromDB() throws Exception{
        List<TopicDoc> topicDocList = topicRepository.queryTopic();
        List<Topic> topicList = new ArrayList<Topic>();

        for(TopicDoc doc: topicDocList){
            Topic topic =new Topic();
            BeanUtils.copyProperties(doc, topic);
            topicList.add(topic);
        }

        return topicList;
    }



}
