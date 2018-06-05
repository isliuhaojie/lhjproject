package com.lhj.project.controller;

import javax.servlet.http.HttpServletRequest;

import com.lhj.project.model.User;
import com.lhj.project.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);
        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }


    @Scheduled(cron = "0/5 * * * * ?")
    public void doCheck() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh-ss-mm");
        Calendar c = Calendar.getInstance();
        String insert_date = format.format(c.getTime());
        System.out.println(insert_date);
    }

    public static void main(String[] args) {
        Long id = new Long(0);

        if (null == id || id <= 0) {
            System.out.println(id + "1111111111");
        }
    }

}