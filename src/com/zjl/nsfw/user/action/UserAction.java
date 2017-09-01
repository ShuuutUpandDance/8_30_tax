package com.zjl.nsfw.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.zjl.nsfw.user.entity.User;
import com.zjl.nsfw.user.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserAction extends ActionSupport {
    @Resource
    private UserService userService;

    private List<User> userList;
    private User user;
    //批量删除使用
    private String[] selectedRow;
    //头像
    private File headImg;
    private String headImgContentType;
    private String headImgFileName;

    //列表页面
    public String listUI(){
        userList = userService.findObjects();
        return "listUI";
    }
    //跳转到新增页面
    public String addUI(){
        return "addUI";
    }
    //保存新增
    public String add(){
        try {
            if (user != null) {
                //处理头像
                if (headImg != null) {
                    //1. 保存到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
                    String filename = UUID.randomUUID().toString().replaceAll("-","") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, filename));

                    //2. 设置用户头像路径
                    user.setHeadImg("user/"+filename);
                }

                userService.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //跳转到编辑页面
    public String editUI(){
        if (user != null && user.getId() != null) {
            user = userService.findObjectById(user.getId());
        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        try {
            if (user != null) {
                //处理头像
                if (headImg != null) {
                    //1. 保存到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
                    String filename = UUID.randomUUID().toString().replaceAll("-","") + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //复制文件
                    FileUtils.copyFile(headImg, new File(filePath, filename));

                    String oldPath = user.getHeadImg();
                    File oldImg = new File(filePath, oldPath.substring(oldPath.lastIndexOf("/")));
                    oldImg.delete();
                    //2. 设置用户头像路径
                    user.setHeadImg("user/"+filename);
                }
                userService.update(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }
    //删除
    public String delete(){
        if (user != null && user.getId() != null) {
            deleteImgById(user.getId());
            userService.delete(user.getId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if (selectedRow != null) {
            for (String id : selectedRow) {
                deleteImgById(id);
                userService.delete(id);
            }
        }
        return "list";
    }
    //根据id删除头像
    public void deleteImgById(Serializable id) {
        User user = userService.findObjectById(id);
        if (user.getHeadImg() != null) {
            System.out.println("进入头像删除-----");
            //删除头像文件
            String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
            String oldPath = user.getHeadImg();
            File oldImg = new File(filePath, oldPath.substring(oldPath.lastIndexOf("/")));
            System.out.println(oldImg.exists());
            System.out.println(oldImg.delete());
            System.out.println("结束头像删除-----");
        }
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }
}
