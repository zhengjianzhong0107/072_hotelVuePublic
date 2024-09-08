---
### 👉作者QQ ：1556708905 微信：zheng0123Long (支持修改、部署调试、定制毕设)

### 👉接网站建设、小程序、H5、APP、各种系统等

### 👉选题+开题报告+任务书+程序定制+安装调试+ppt 都可以做
---

**毕业设计所有选题地址 [https://github.com/zhengjianzhong0107/allProject](https://github.com/zhengjianzhong0107/allProject)****[博客地址](https://blog.csdn.net/2303_76227485/article/details/131029619)**

**视频演示：
[B站视频演示](https://www.bilibili.com/video/BV1Rj411U7J6/)**

 

## 基于springboot+vue的酒店预订管理系统(源代码+数据库)072

## 一、系统介绍

本项目前后端分离

本系统分为管理员、前台工作人员、用户三种角色

用户角色包含以下功能：

- 登录、注册、浏览酒店信息、预订客房、查询订单信息、支付

酒店前台角色包含以下功能：

- 入住登记、登记信息、客户信息、客户订单信息管理、客户入住信息管理、结账、通过房间号码进行退房结账、业务统计

管理员角色包含以下功能：

- 登录、客房类型设置、客房设置、部门管理、员工信息管理
- 入住登记、登记信息、客户信息、客户订单信息管理、客户入住信息管理、结账、通过房间号码进行退房结账、业务统计

## 二、所用技术

后端技术栈：

- springboot
- mybatis
- mysql

前端技术栈：

- vue
- elementui
- axios

## 三、环境介绍

基础环境 :IDEA/eclipse, JDK 1.8, Mysql5.7及以上,Node.js(14),Maven3.6

所有项目以及源代码本人均调试运行无问题 可支持远程调试运行

## 四、页面截图

![contents](./picture/picture1.png)
![contents](./picture/picture2.png)
![contents](./picture/picture3.png)
![contents](./picture/picture4.png)
![contents](./picture/picture5.png)
![contents](./picture/picture6.png)
![contents](./picture/picture7.png)
![contents](./picture/picture8.png)
![contents](./picture/picture9.png)
![contents](./picture/picture10.png)
![contents](./picture/picture11.png)
![contents](./picture/picture12.png)
![contents](./picture/picture13.png)
![contents](./picture/picture14.png)
![contents](./picture/picture15.png)
![contents](./picture/picture16.png)
![contents](./picture/picture17.png)
![contents](./picture/picture18.png)
![contents](./picture/picture19.png)
![contents](./picture/picture20.png)
![contents](./picture/picture21.png)
![contents](./picture/picture22.png)

## 五、浏览地址

前台访问地址：http://localhost:8888

-用户账号/密码：zhangsan/123456

后台访问地址：http://localhost:9528

-工作人员账号/密码：admin/123456
-管理员账号/密码：work1/123456

## 六、部署教程

1. 使用Navicat或者其它工具，在mysql中创建对应名称的数据库，并执行hotel-manager-master项目的sql文件；

2. 使用IDEA/Eclipse导入hotel-manager-master项目，若为maven项目请选择maven，等待依赖下载完成；

3. 进入hotel-manager-master/src/main/resources修改application.yml 里面的数据库配置

4. 启动项目后端项目

5. vscode或idea打开hotel-manager-vue-master项目和hotel_app项目，

6. 在编译器中打开terminal(终端)，执行npm install 依赖下载完成后执行 npm run dev,执行成功后会显示访问地址

 
