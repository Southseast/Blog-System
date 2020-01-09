package st.southsea.blog;

import lombok.SneakyThrows;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import st.southsea.blog.bean.Article;
import st.southsea.blog.bean.Comment;
import st.southsea.blog.bean.Friend;
import st.southsea.blog.bean.User;
import st.southsea.blog.utils.MD5Util;
import st.southsea.blog.utils.RandomUtil;

/**
 * @Author: South
 * @Date: 2019-05-14 10:45
 */
public class MainSetup implements Setup {
    @SneakyThrows
    @Override
    public void init(NutConfig nutConfig) {
        Ioc ioc = nutConfig.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "st.southsea.blog.bean", false);

        if (0 == dao.count(Friend.class)) {
            dao.insert(new Friend(1, "H4mster", "http://h4mster.top/"));
            dao.insert(new Friend(2, "Xishir", "https://www.codemonster.cn/"));
            dao.insert(new Friend(3, "L1nk3r", "http://www.lmxspace.com/"));
            dao.insert(new Friend(4, "Sheldon", "http://she1don.cn/"));
            dao.insert(new Friend(5, "saltyfishyu", "http://saltyfishyu.xmutsec.com/"));
            dao.insert(new Friend(6, "Cytosine", "https://cyto.top/"));
            dao.insert(new Friend(7, "PeterZ", "https://blog.csdn.net/PeterZ1997/"));
            dao.insert(new Friend(8, "ju5tw4nty0u", "http://ju5tw4nty0u.top/"));
            dao.insert(new Friend(9, "Cosmos", "http://blog.thecosmos.cn/"));
            dao.insert(new Friend(10, "Nepire", "http://nepire.xmutsec.com/"));
            dao.insert(new Friend(11, "White", "http://white.xmutsec.com/"));
            dao.insert(new Friend(12, "A3ura", "https://a3ura.github.io/"));
        }

        if (0 == dao.count(Article.class)) {
            dao.insert(new Article("那你就沉浸在以往你所谓的荣耀中，然后一直到老去死去吧。", "那你就沉浸在以往你所谓的荣耀中，然后一直到老去死去吧。"));
            dao.insert(new Article("可以卑微如尘土，不可扭曲如蛆虫。", "可以卑微如尘土，不可扭曲如蛆虫。"));
            dao.insert(new Article("生日快乐。", "生日快乐，接下来我还想和你一同跋山涉水翻山越岭披荆斩棘踏遍三江六岸走过世间坎坷漫步人生万里。"));
            dao.insert(new Article("往后余生", "我想带你去看晴空万里。"));
            dao.insert(new Article("出山", "一瞬三年五载。"));
            dao.insert(new Article("离人愁", "春去白了华发落寞了思量。"));
            dao.insert(new Article("星月神话", "千年之后的你会在哪里，身边有怎样风景。"));
            dao.insert(new Article("美丽的神话", "万事沧桑唯有爱是永远的神话。"));
            dao.insert(new Article("好久不见", "我多么想和你见一面，看看你最近改变。"));
            dao.insert(new Article("虹之间", "童话说雨后，会有一道彩虹。"));
            dao.insert(new Article("可惜没如果", "全都怪我，不该沉默时沉默，该勇敢时软弱。"));
            dao.insert(new Article("平凡之路", "我曾经拥有着的一切，转眼都飘散如烟。"));
            dao.insert(new Article("不要说话", "灯光再亮，也抱住你。"));
            dao.insert(new Article("此生不换", "回头看不曾走远眷恋一人流连忘返。"));
            dao.insert(new Article("岁月神偷", "时间是让人猝不及防的东西。"));
            dao.insert(new Article("小情歌", "你知道就算大雨让这座城市颠倒，我会给你怀抱。"));
            dao.insert(new Article("我好想你", "我还踮着脚思念。"));
            dao.insert(new Article("你被写在我的歌里", "忧伤有时候竟被你调味得像颗糖。"));
            dao.insert(new Article("再遇见", "所有回忆，青丝成雪。"));
            dao.insert(new Article("无与伦比的美丽", "我知道你才是这世界上无与伦比的美丽。"));
            dao.insert(new Article("当我们一起走过", "有多少苦痛，有你和我一起度过。"));
            dao.insert(new Article("夜空中最亮的星", "给我再去相信的勇气，越过谎言去拥抱你。"));
            dao.insert(new Article("空空如也", "一切的星光都已陨灭。"));
            dao.insert(new Article("一百万个可能", "在一瞬间有一百万个可能。"));
            dao.insert(new Article("借我", "借我十年，借我亡命天涯的勇敢。"));
            dao.insert(new Article("画舫烟中浅", "薄雾小雨润如烟，灯中画舫烟中浅。"));
            dao.insert(new Article("不过沧海", "像蝴蝶，最无奈，飞不过，沧海。"));
            dao.insert(new Article("年少有为", "假如我年少有为，知进退。"));
            dao.insert(new Article("盗将行", "谢绝策勋十二转，想为你窃玉簪。"));
            dao.insert(new Article("牵丝戏", "你一牵我舞如飞你一引我懂进退。"));
            dao.insert(new Article("可不可以", "我的快乐也只有你能给予。"));
            dao.insert(new Article("红昭愿", "年少风雅鲜衣怒马，也不过一刹那。"));
            dao.insert(new Article("白羊", "你裙下的人间太美妙，好想把你，一口气全部吃掉。"));
            dao.insert(new Article("起风了", "从前初识这世间，万般流连，看着天边似在眼前，也甘愿赴汤蹈火去走它一遍。"));
            dao.insert(new Article("东西", "我愿意陪着你去东和西。"));
            dao.insert(new Article("理想三旬", "就老去吧，孤独别醒来。"));
            dao.insert(new Article("再也没有", "每天都没有着存在，不知道要做些什么。"));
            dao.insert(new Article("不找了", "别找了找不到的。"));
            dao.insert(new Article("光年之外", "我没想到为了你我能疯狂到，山崩海啸没有你根本不想逃。"));
            dao.insert(new Article("侧脸", "关于你的一切我都想要了解。"));
            dao.insert(new Article("你曾是少年", "或许再过上几年，你也有张虚伪的脸。"));
            dao.insert(new Article("爱情转移", "把一个人的温暖，转移到另一个的胸膛，让上次犯的错反省出梦想。"));
            dao.insert(new Article("What Are Words", "Anywhere you are, I am near."));
            dao.insert(new Article("我好像在哪见过你", "像确定我要遇见你，就像曾经交换过眼睛。"));
            dao.insert(new Article("小幸运", "青春是段跌跌撞撞的旅行。"));
        }


        if (0 == dao.count(User.class)) {
            User user = new User();
            user.setEmail("i@southsea.st");
            user.setSex(1);
            user.setNickname("南溟");
            user.setPassword(MD5Util.Generate("south"));
            user.setTelphone("12345678910");
            user.setRegion("XM");
            user.setOccupation("CTFer");
            user.setSignature("我想带你去看晴空万里");
            user.setBlogAddress("https://southsea.st");
            user.setBlogAddress("https://github.com.southseast");
            user.setPermissionId(1);
            dao.insert(user);

            for (int i = 0; i < 100; i++) {
                user.setEmail(RandomUtil.GENERATE_EMAIL());
                user.setSex(RandomUtil.GENERATE_SEX());
                user.setNickname(RandomUtil.GENERATE_NAME(user.getSex()));
                user.setPassword(MD5Util.Generate(RandomUtil.GENERATE_PASSWORD(6)));
                user.setTelphone(RandomUtil.GENERATE_PHONE());
                user.setRegion("XM");
                user.setOccupation("CTFer");
                user.setSignature("我想带你去看晴空万里");
                user.setBlogAddress("https://southsea.st");
                user.setBlogAddress("https://github.com.southseast");
                user.setPermissionId(0);
                dao.insert(user);
                Comment comment = new Comment();
                comment.setEmail(user.getEmail());
                comment.setArticleId(Long.parseLong(RandomUtil.GENERATE_NUM(1)));
                comment.setContent(RandomUtil.GENERATE_WORD());
                dao.insert(comment);
            }
        }


    }

    @Override
    public void destroy(NutConfig nutConfig) {

    }
}
