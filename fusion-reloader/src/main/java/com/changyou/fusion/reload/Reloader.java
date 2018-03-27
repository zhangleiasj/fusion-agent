package com.changyou.fusion.reload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Arrays;

/**
 * 热更新Agent
 * <p>
 * Created by zhanglei_js on 2018/3/27.
 */
public class Reloader {

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, UnmodifiableClassException, InterruptedException, IOException {

        // 读取补丁文件
        File patcherFile = new File("patcher/Echo.class");
        InputStream is = new FileInputStream(patcherFile);
        long length = patcherFile.length();

        // 读取文件
        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + patcherFile.getName());
        }
        is.close();

        // 打补丁（com.changyou.fusion.app.Echo）
        inst.redefineClasses(new ClassDefinition(Arrays.stream(inst.getAllLoadedClasses()).filter(clz -> clz.getName().equals("com.changyou.fusion.app.Echo")).findAny().orElseGet(null), bytes));

        System.out.println("Echo Class Fixed!");
    }
}
