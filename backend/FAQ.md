FAQ
----


docker远程端口没有开启
--
1、编辑daemon.json文件

2、增加hosts ["tcp://0.0.0.0:2375", "unix:///var/run/docker.sock"]

3、重启docker服务 

主机模式没有暴露接口
--
通过docker inspect 命令查询容器信息

查看exposedports来查询容器暴露的接口
