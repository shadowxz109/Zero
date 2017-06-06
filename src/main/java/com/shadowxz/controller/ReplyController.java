package com.shadowxz.controller;

import com.shadowxz.domain.Reply;
import com.shadowxz.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xz on 2017/4/29.
 */

@Controller
@RequestMapping(value = "/api/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @RequestMapping(value = "/{replyId}",method = RequestMethod.GET)
    public @ResponseBody Reply getReply(@PathVariable Integer replyId){
        return replyService.findReplyById(replyId);
    }

}