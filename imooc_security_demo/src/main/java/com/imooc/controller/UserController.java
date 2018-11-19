package com.imooc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.mapper.CatMapper;
import com.imooc.pojo.Cat;
import com.imooc.pojo.TbUser;
import com.imooc.service.UserService;
import com.imooc.utils.IBusResult;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;



@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;


    @Autowired
    private CatMapper catMapper;
    /**
     * 获取当前用户
     * @return
     */
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){//(Authentication authentication){
        return userDetails;
    }
   /* public Object getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }*/

    @GetMapping("/user")
    @ApiOperation(value = "用户查询服务")
    public List<User> queryUser(@RequestParam(required = false) String username,
                                @Valid UserQueryCondition queryCondition,
                                @PageableDefault(size = 10,page = 20,sort ="username,asc") Pageable pageable,
                                BindingResult errors) {
        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error-> System.out.println("有输出吗"+error.getDefaultMessage()));
        }

        System.out.println(ReflectionToStringBuilder.toString(queryCondition,ToStringStyle.MULTI_LINE_STYLE));
        System.out.println("username:"+username);
        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @PostMapping("/user/regist")
    public void regist(TbUser tbUser, HttpServletRequest request){
        //TODO 注册用户
        //不管注册用户还是绑定用户，都会拿到用户的唯一标识
        String  username= tbUser.getUsername();

        providerSignInUtils.doPostSignUp(String.valueOf(username),new ServletWebRequest(request));

    }




    @RequestMapping("/catList")
    @JsonView(Cat.CatDetailView.class)
    public List<Cat> queryCat(String name){
        List<Cat> catList = userService.getCatList(name);
        Cat cat = catMapper.selectByPrimaryKey(80);
        System.out.println(cat==null);
        return  catList;
    }

    @RequestMapping("/saveCat")
    public Integer save(){
        Cat cat=new Cat();
        cat.setCatAge(132);
        cat.setCatName("fafaj");
        return  catMapper.insertSelective(cat);
    }

    @RequestMapping("/deleteCat")
    public Integer delete(){
        Cat cat=new Cat();
        cat.setId(2);
        return  catMapper.deleteByPrimaryKey(cat);
    }



    @RequestMapping("/updateCat")
    public Integer updatecat(){
        Cat cat=new Cat();
        cat.setId(1);
        cat.setCatAge(588);
        cat.setCatName("cmytaa");
        return   catMapper.updateByPrimaryKeySelective(cat);
    }

    @GetMapping("{id:\\d+}")
    public List<TbUser> getInfo(@ApiParam("用户Id")  @PathVariable String id){
        System.out.println("进入getInfo服务");
        List<TbUser> tbUsers = userService.queryAll();



        return tbUsers;


        //throw new UserNotExistException(id);
        // User user=new User();
    // user.setName("adada");
       // return  user;
    }


    @GetMapping("/cc")
    @ApiOperation(value = "测试！！！")
    public IBusResult<TbUser> getcc(){
        return IBusResult.ok(new TbUser());

    }


}
