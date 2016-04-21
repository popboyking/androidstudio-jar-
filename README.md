# androidstudio-jar-



一、热修复

这个项目原本是用来展示如何打包用的,最近在研究热修复动态加载,所以有了以下目录:

1.buildSrc   -->目录的作用是将应用的dex在应用编译之前插入,以达到 防止被打上 CLASS_ISPREVERIFIED 标记

2.codefix    -->引用类的工程目录

3.hotfixlib  -->实现Dex类加载的核心库文件

4.pathmodule --测试用的修复补丁包


主要是参考了这位大神的实现自己再实现了一遍,增加了网络请求部分
http://blog.csdn.net/lmj623565791/article/details/49883661


二、打包
执行gradle task 里的buildJar方法进行打包操作
注意修改打包目录的包名路径！
