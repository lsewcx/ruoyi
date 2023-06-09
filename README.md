# 运行本项目的方法  
1.在redis目录下打开cmd输入redis-server.exe redis.windows.conf运行  
2.idea打开vue开头的文件夹,然后运行构建   
3.在vue3![image](https://github.com/lsewcx/ruoyi/assets/107914845/d633161f-c954-451c-ac9f-188b083b3273)  
执行yarn dev  
# 所需要安装的软件
## mysql
## navicat(破解版)
执行sql语句命令的
## redis
具体见https://blog.csdn.net/weixin_44893902/article/details/123087435博客
## vue
## java idea社区版(破解版)
## vscode
插件  
Auto Rename Tag  
Vue Language Features (Volar)  
## git bash
输命令用的
# 第一次使用配置
创建mysql库  
![image](https://github.com/lsewcx/ruoyi/assets/107914845/b205eb51-c1b4-4625-a13d-8201332b81bf)  
数据库名称应与图中的名称相同，本项目默认的是lse1  
create table lse1  
然后使用navicat  
![image](https://github.com/lsewcx/ruoyi/assets/107914845/2a04178a-6703-4aa8-8c57-85ccce019d35)  
点击数据库的表右键运行sql文件运行文件夹中的RuoYi-Vue-master/sql/lse1.sql文件
运行完成后打开idea  
![image](https://github.com/lsewcx/ruoyi/assets/107914845/efb6a0ad-89e4-4e4b-a20b-7c718032a508)  
密码应与数据库用户名和密码一致
上图修改的文件路径为RuoYi-Vue-master\ruoyi-admin\target\classes\application-druid.yml中修改  
接下来点击右边的  
![image](https://github.com/lsewcx/ruoyi/assets/107914845/ff0a14b3-45e6-435f-a12a-f8392ae6ba77)  
![image](https://github.com/lsewcx/ruoyi/assets/107914845/3be9f6d1-9390-4cf4-9087-fb360dd9b728)  
![image](https://github.com/lsewcx/ruoyi/assets/107914845/813b6844-e73c-4de0-9be1-f364c9847f52)
上图中通用户写数据库的用户名和密码数据库填写你创建的数据库名称点击测试连接，若连接成功则点击确定即可  

# 注意
若是编写的新模块有数据库内容，别忘了将数据库转储成sql文件上传

# 高阶应用
查看https://blog.csdn.net/weixin_61845680/article/details/127269927
