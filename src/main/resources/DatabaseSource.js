var ioc = {
    conf: {
        type: "org.nutz.ioc.impl.PropertiesProxy",
        fields: {
            paths: ["./"]
        }
    },
    dataSource: {
        type: "com.alibaba.druid.pool.DruidDataSource",
        events: {
            depose: 'close'
        },
        fields: {
            // 链接地址
            url: "jdbc:mysql://127.0.0.1:3306/Blog?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf8",
            // 用户名
            username: "root",
            // 密码
            password: "123456",
            // 若不配置此项,如果数据库未启动,druid会一直等可用连接,卡住启动过程
            maxWait: 15000,
            // 提高fastInsert的性能
            defaultAutoCommit: false
        }
    },
    dao: {
        type: "org.nutz.dao.impl.NutDao",
        args: [{refer: "dataSource"}]
    }
};