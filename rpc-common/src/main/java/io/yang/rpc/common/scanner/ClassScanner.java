package io.yang.rpc.common.scanner;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author zhangyang
 * @description 类扫描器
 * @date 2023/02/27 23:38
 **/
public class ClassScanner {
    /**
     * 文件
     */
    private static final String PROTOCOL_FILE = "file";
    /**
     * jar包
     */
    private static final String PROTOCOL_JAR = "jar";
    /**
     * class文件的后缀
     */
    private static final String CLASS_FILE_SUFFIX = ".class";

    public static List<String> getClassNameList(String packageName) throws IOException {

        List<String> classNameList = new ArrayList<>();
        // 获取包的名字
        String packageDirName = packageName.replace(".", "/");
        Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        //是否循环迭代
        boolean recursive = true;
        while (dirs.hasMoreElements()) {
            // 获取下一个元素
            URL url = dirs.nextElement();
            String protocol = url.getProtocol();
            if (StringUtils.equals(PROTOCOL_FILE, protocol)) {
                // 元素为文件，获取元素的包路径
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                // 扫描路径下的所有类
                findAndAddClassesInPackageByFile(packageName, filePath, recursive, classNameList);
            } else if (StringUtils.equals(PROTOCOL_JAR, protocol)) {
                packageName = findAndAddClassesInPackageByJar(
                        packageName, classNameList, recursive, packageDirName, url);
            }
        }
        return classNameList;


    }

    /**
     * 扫描Jar文件中指定包下的所有类信息
     *
     * @param packageName    扫描的包名
     * @param classNameList  完成类名存放的List集合
     * @param recursive      是否递归调用
     * @param packageDirName 当前包名的前面部分的名称
     * @param url            包的url地址
     * @return 处理后的包名，以供下次调用使用
     */
    private static String findAndAddClassesInPackageByJar(String packageName,
                                                          List<String> classNameList,
                                                          boolean recursive,
                                                          String packageDirName,
                                                          URL url) throws IOException {
        JarFile jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            // 如果是以 / 开头，获取 / 后面的字符串
            name = (name.charAt(0) == '/') ? StringUtils.substring(name, 1) : name;
            if (StringUtils.startsWith(name, packageDirName)) {
                // 如果前半部分和定义的包名相同
                int idx = StringUtils.lastIndexOf(name, "/");
                if (idx != -1) {
                    //如果以"/"结尾 是一个包,获取包名 把"/"替换成"."
                    packageName = StringUtils.substring(name, 0, idx).replace('/', '.');
                }
                if ((idx != -1) || recursive) {
                    // 如果可以迭代下去 并且是一个包
                    if (StringUtils.endsWith(name, CLASS_FILE_SUFFIX) && !entry.isDirectory()) {
                        // 去掉后面的".class" 获取真正的类名
                        String className = StringUtils.substring(name,
                                packageName.length() + 1, name.length() - CLASS_FILE_SUFFIX.length());
                        classNameList.add(packageName + '.' + className);
                    }
                }
            }
        }
        return packageName;
    }

    /**
     * 扫描当前工程中指定包下的所有类信息
     *
     * @param packageName   扫描的包名
     * @param packagePath   包在磁盘上的完整路径
     * @param recursive     是否递归调用
     * @param classNameList 类名称的集合
     */
    private static void findAndAddClassesInPackageByFile(String packageName,
                                                         String packagePath,
                                                         final boolean recursive,
                                                         List<String> classNameList) {
        File dir = new File(packagePath);
        //如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        //如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(file ->
                (recursive && file.isDirectory() || (file.getName().endsWith(CLASS_FILE_SUFFIX))));
        assert dirfiles != null;
        Arrays.stream(dirfiles).forEach(file -> {
                    if (file.isDirectory()) {
                        //如果是目录 则继续扫描
                        findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
                                file.getAbsolutePath(), recursive, classNameList);
                    } else {
                        //如果是java类文件 去掉后面的.class 只留下类名
                        String className = file.getName()
                                .substring(0, file.getName().length() - CLASS_FILE_SUFFIX.length());
                        //添加到集合中去
                        classNameList.add(packageName + '.' + className);
                    }
                }
        );

    }
}
