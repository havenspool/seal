# seal  数据模块
mongodb：数据持久化
redis：内存数据

数据流程：游戏启动-->从redis的数据覆盖到monodb，并清空redis
          玩家登录-->从mongodb加载数据到jvm内存并同步到redis