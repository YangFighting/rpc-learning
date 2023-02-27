package io.yang.rpc.common.scanner;


import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhangyang
 * @description
 * @date 2023/02/28 0:31
 **/
public class ClassScannerTest {
    /**
     * 扫描 ClassScanner类所在包 下所有的类
     *
     * @throws IOException IO异常
     */
    @Test
    public void getClassNameList() throws IOException {
        List<String> classNameList = ClassScanner.getClassNameList(ClassScanner.class.getPackage().getName());
        assertTrue(classNameList.contains(ClassScanner.class.getName()));
        classNameList.forEach(System.out::println);
    }

}