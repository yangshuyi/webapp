package com.echodrama.action;

import java.util.List;

import com.echodrama.form.JSONMsg;
import com.echodrama.model.Topic;
import com.echodrama.mongo.collection.TopicDoc;
import com.echodrama.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by shuyi on 14-3-1.
 */
@Controller
@RequestMapping("/topic")
public class TopicAction extends BaseAction {
    @Autowired
    private TopicService topicService;

    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONMsg queryAll() {
        JSONMsg result = new JSONMsg();
        try {
            List<Topic> topics = topicService.queryTopicFromDB();
            result.setResultEntry("topics", topics);
        } catch (Exception ex) {
            result.setErrorInfo("ERROR 1001", ex.getLocalizedMessage());
        }
        return result;
    }


}