package com.echodrama.dao.impl;

import com.echodrama.dao.TopicDao;
import com.echodrama.pojo.TopicPojo;
import org.springframework.stereotype.Repository;

@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<TopicPojo> implements TopicDao {

}
