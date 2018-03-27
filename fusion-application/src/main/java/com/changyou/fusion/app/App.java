package com.changyou.fusion.app;

/**
 * 模拟业务进程
 * <p>
 * Created by zhanglei_js on 2018/3/27.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        // 定义一个可以被热更新的对象
        Echo echo = new Echo();

        // 死循环输出
        while (true) {
            echo.echo();

            // 每次输出后等待5秒
            Thread.sleep(5000);
        }
    }
}
