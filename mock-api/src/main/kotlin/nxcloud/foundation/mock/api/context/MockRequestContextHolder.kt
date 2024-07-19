package nxcloud.foundation.mock.api.context

import nxcloud.foundation.mock.api.type.MockHandler
import java.util.concurrent.ConcurrentHashMap

object MockRequestContextHolder {

    private val empty = MockRequestContext()

    private val contextThreadLocal: ThreadLocal<MockRequestContext> = ThreadLocal()

    private fun createContext(): MockRequestContext {
        return MockRequestContext()
    }

    private val endpointHandlerMapping: MutableMap<String, MockHandler> = ConcurrentHashMap()

    fun current(): MockRequestContext {
        var context = contextThreadLocal.get()
        if (context == null) {
            synchronized(empty) {
                context = contextThreadLocal.get()

                if (context == null) {
                    context = createContext()
                    contextThreadLocal.set(context)
                }
            }
        }
        return context
    }

    fun exists(): Boolean {
        return contextThreadLocal.get() != null
    }

    fun reset() {
        contextThreadLocal.remove()
    }

    fun registerHandler(endpoint: String, handler: MockHandler) {
        endpointHandlerMapping.put(endpoint, handler)
    }

}