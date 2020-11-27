package io.kuark.server.mq.rocket

import org.apache.rocketmq.broker.BrokerStartup
import org.apache.rocketmq.namesrv.NamesrvStartup
import java.util.*

/**
 * 启动RocketMQ的name server和broker
 *
 * @author K
 * @since 1.0.0
 */
class RocketMQStartup {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val path = RocketMQStartup::class.java.classLoader.getResource(".").path + "../../../resources/main/"
            setEnvVars(mapOf("ROCKETMQ_HOME" to path))
            NamesrvStartup.main(args)
            BrokerStartup.main(args)
        }

        /**
         * 设置系统环境变量
         *
         * @param vars Map(变量名，变量值)
         * @author https://blog.csdn.net/n1007530194/article/details/97130931
         * @author K
         * @since 1.0.0
         */
        fun setEnvVars(vars: Map<String, String>) {
            try {
                val processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment")
                val theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment")
                theEnvironmentField.isAccessible = true
                val env = theEnvironmentField.get(null) as MutableMap<String, String>
                env.putAll(vars!!)
                val theCaseInsensitiveEnvironmentField =
                    processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment")
                theCaseInsensitiveEnvironmentField.isAccessible = true
                val cienv = theCaseInsensitiveEnvironmentField.get(null) as MutableMap<String, String>
                cienv.putAll(vars)
            } catch (e: NoSuchFieldException) {
                val classes = Collections::class.java.declaredClasses
                val env = System.getenv()
                for (cl in classes) {
                    if ("java.util.Collections\$UnmodifiableMap" == cl.name) {
                        val field = cl.getDeclaredField("m")
                        field.isAccessible = true
                        val obj = field.get(env)
                        val map = obj as MutableMap<String, String>
                        map.clear()
                        map.putAll(vars!!)
                    }
                }
            }
        }
    }

}