package nxcloud.foundation.mock.api.endpoint

/**
 * 解析要模拟的端点名称
 */
interface MockEndpointNamingResolver {

    /**
     * @param obj 由植入mock的调用处传入要解析的对象
     */
    fun resolve(obj: Any): String

}