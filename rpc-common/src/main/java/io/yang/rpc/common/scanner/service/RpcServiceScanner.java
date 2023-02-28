package io.yang.rpc.common.scanner.service;

import io.yang.rpc.annotation.RpcService;
import io.yang.rpc.common.scanner.ClassScanner;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * @description @RpcService注解扫描器
 * @date 2023/02/28 21:50
 **/
public class RpcServiceScanner extends ClassScanner {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcServiceScanner.class);

    /**
     * @param scanPackage 扫描的包
     * @return 扫描指定包下的类，并筛选使用@RpcService注解标注的类
     * @throws IOException IO
     */
    public static Map<String, Object> doScannerWithRpcServiceAnnotationFilterAndRegistryService(
            String scanPackage) throws IOException {
        List<String> classNameList = getClassNameList(scanPackage);
        if (CollectionUtils.isEmpty(classNameList)) {
            return new HashMap<>();
        }
        classNameList.forEach(className -> {
            try {
                Class<?> clazz = Class.forName(className);
                RpcService rpcService = clazz.getAnnotation(RpcService.class);
                if (rpcService != null) {
                    //优先使用interfaceClass, 如果interfaceClass的name为空，再使用interfaceClassName
                    LOGGER.info("当前标注了@RpcService注解的类实例名称===>>> " + clazz.getName());
                    LOGGER.info("@RpcService注解上标注的属性信息如下：");
                    LOGGER.info("interfaceClass===>>> " + rpcService.interfaceClass().getName());
                    LOGGER.info("interfaceClassName===>>> " + rpcService.interfaceClassName());
                    LOGGER.info("version===>>> " + rpcService.version());
                    LOGGER.info("group===>>> " + rpcService.group());

                }
            } catch (ClassNotFoundException e) {
                LOGGER.error("scan classes throws exception: ", e);
            }
        });
        return new HashMap<>();
    }

    /**
     * todo 获取serviceName 的顺序有什么意思
     * @param rpcService 注解
     * @return 获取serviceName
     */
    private static String getServiceName(RpcService rpcService) {
        Class<?> clazz = rpcService.interfaceClass();
        if (clazz == void.class) {
            return rpcService.interfaceClassName();
        }
        String serviceName = clazz.getName();
        if (serviceName.trim().isEmpty()) {
            return rpcService.interfaceClassName();
        }
        return serviceName;

    }
}
