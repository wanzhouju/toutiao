package com.nowcoder.controller;

import com.nowcoder.model.News;
import com.nowcoder.model.User;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.ToutiaoService;
import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by wuzhaojun on 2017/4/7.
 */
@Controller
public class HomeController {
    @Autowired
    private ToutiaoService toutiaoService;

    @Autowired
    private UserService userService;

    private List<ViewObject> getNews(int userId, int offset, int limit) {
        List<News> newsList = toutiaoService.getLatestNews(userId, offset, limit);

        List<ViewObject> vos = new ArrayList<>();
        for (News news : newsList) {
            ViewObject vo = new ViewObject();
            vo.set("news", news);
            vo.set("user", userService.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;
    }
    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model,@RequestParam(value = "userId", defaultValue = "0") int userId
                        ) {
        model.addAttribute("vos", getNews(0, 0, 10));

        return "home";
    }

    @RequestMapping(path = {"/user/{userId}/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex( Model model,@PathVariable("userId") int userId) {

        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    }


}
