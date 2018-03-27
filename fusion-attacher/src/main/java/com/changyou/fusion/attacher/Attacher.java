package com.changyou.fusion.attacher;

import com.sun.tools.attach.*;

import java.io.IOException;

/**
 * 附加类
 * <p>
 * Created by zhanglei_js on 2018/3/27.
 */
public class Attacher {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // 找到模拟的应用
        VirtualMachineDescriptor vmd = VirtualMachine.list().stream().filter(vm -> vm.displayName().contains("app.App")).findAny().orElseGet(null);
        if (vmd != null) {
            VirtualMachine vm = VirtualMachine.attach(vmd);
            vm.loadAgent("patcher/fusion-reloader-1.0-SNAPSHOT.jar");
            vm.detach();
        } else {
            // 找不到需要更新的进程
            System.out.println("Can not find any process.");
        }
    }
}
