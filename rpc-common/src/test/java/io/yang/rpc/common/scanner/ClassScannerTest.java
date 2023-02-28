package io.yang.rpc.common.scanner;


import io.yang.rpc.common.scanner.reference.RpcReferenceScanner;
import io.yang.rpc.common.scanner.service.RpcServiceScanner;
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
     * 扫描 ClassScannerTest类所在包 下所有的类
     *
     * @throws IOException IO异常
     */
    @Test
    public void getClassNameList() throws IOException {
        List<String> classNameList = ClassScanner.getClassNameList(ClassScannerTest.class.getPackage().getName());
        assertTrue(classNameList.contains(ClassScannerTest.class.getName()));
        classNameList.forEach(System.out::println);
    }

    /**
     * 扫描当前包下所有标注了@RpcService注解的类
     * @throws IOException  反射异常
     */
    @Test
    public void doScannerWithRpcServiceAnnotationFilterAndRegistryService() throws IOException {

        RpcServiceScanner.doScannerWithRpcServiceAnnotationFilterAndRegistryService(
                ClassScannerTest.class.getPackage().getName());
    }

    /**
     * 扫描当前包下所有标注了@RpcReference注解的类
     * @throws Exception  Exception
     */
    @Test
    public void testScannerClassNameListByRpcReference() throws Exception {
        RpcReferenceScanner.doScannerWithRpcReferenceAnnotationFilter(
                ClassScannerTest.class.getPackage().getName());
    }

}