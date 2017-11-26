package com.shadowxz.controller;

import com.shadowxz.domain.Constant;
import com.shadowxz.domain.Dialogue;
import com.shadowxz.domain.Reply;
import com.shadowxz.service.DialogueService;
import com.shadowxz.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xz on 2017/4/29.
 */

@Controller
@RequestMapping(value = "/api/reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    @Autowired
    DialogueService dialogueService;

    @RequestMapping(value = "/{replyId}",method = RequestMethod.GET)
    public @ResponseBody Reply getReply(@PathVariable Integer replyId){
        return replyService.findReplyById(replyId);
    }

    @RequestMapping(value = "/{replyId}",method = RequestMethod.POST)
    public @ResponseBody Map<String,Object> postDialogue(@PathVariable Integer replyId, @RequestParam String content,
                                                         @RequestParam Integer receivceUserId, HttpServletRequest request){
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String,Object> result = new HashMap<>();
        Dialogue dialogue = new Dialogue(replyId,userId,receivceUserId,content,0,0);
        dialogueService.addDialogue(dialogue);
        result.put("resultCode", Constant.RETURN_CODE_SUCC);
        result.put("msg","回复成功");
        return result;
    }
}