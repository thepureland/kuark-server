rootProject.name = "kuark-server"

include("kuark-server-config")
include("kuark-server-eureka")
include("kuark-server-hystrix")
include("kuark-server-admin")
include("kuark-server-seata")
include("kuark-server-h2")
include("kuark-server-nacos")

include("kuark-server-mq")
include("kuark-server-mq:kuark-server-mq-rocket")
findProject(":kuark-server-mq:kuark-server-mq-rocket")?.name = "kuark-server-mq-rocket"
include("kuark-server-mq:kuark-server-mq-rocket-console")
findProject(":kuark-server-mq:kuark-server-mq-rocket-console")?.name = "kuark-server-mq-rocket-console"

include("kuark-server-skywalking")
include("kuark-server-skywalking:kuark-server-skywalking-collecter")
findProject(":kuark-server-skywalking:kuark-server-skywalking-collecter")?.name = "kuark-server-skywalking-collecter"
include("kuark-server-skywalking:kuark-server-skywalking-ui")
findProject(":kuark-server-skywalking:kuark-server-skywalking-ui")?.name = "kuark-server-skywalking-ui"

