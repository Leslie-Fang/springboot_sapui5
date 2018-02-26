## Introduction
基于sapui5和spring搭建的一个基本应用
* 前端: sapui5
* 后端: spring_boot
* 数据流: sapui5->olingo(odata)->spring_boot->JPA->mysql

## Todolist
* Done: 搭建一个基本可用网页
* Done: 项目开发过程自动重启
https://www.jianshu.com/p/08d61789af70
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-devtools.html
在intelliji中实现需要额外配置：
https://github.com/jhipster/generator-jhipster/issues/2242
https://stackoverflow.com/questions/12744303/intellij-idea-java-classes-not-auto-compiling-on-save
https://my.oschina.net/u/940590/blog/1555785

配置完成之后只需要启动1次application.class
更改完代码之后手动重新编译一遍: maven package
不需要重新运行application.class，项目会自动重启

* Ongoing: 嵌入sapui5前端
openui5:https://openui5.hana.ondemand.com/#/topic/fe12df2e338e43598977d09f3d191b7b.html
修改加载静态资源路径：https://www.jianshu.com/p/d127c4f78bb8


坑：
1. 不能使用thymeleaf(从pom中直接移除),thymeleaf在解析html文件时会把双引号""改成''，这导致这句话不可用，必须要双引号
2. 不使用thymeleaf，要直接返回html，在web中用@Controller 而不是 @RestController
