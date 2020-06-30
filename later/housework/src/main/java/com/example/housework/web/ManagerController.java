package com.example.housework.web;

import com.example.housework.Service.OrderService;
import com.example.housework.pojo.Order;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ManagerController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/hello")
    public String hello(Model m){
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        return "hello";
    }

    @GetMapping("/order/list/listCategory")
    public String list(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size){
        PageHelper.startPage(start,size,"id desc");
        List<Order> list = orderService.listCategory();
        PageInfo<Order> page = new PageInfo<>(list);
        m.addAttribute("page", page);
        return "listCategory";
    };
}
