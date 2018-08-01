package com.wj.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.main_tv);
        tv.setText("第一页");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到第二页
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        addUser();
        searchUser();
//        addGoods();
        searchUserGoods();
    }

    private void searchUserGoods() {
        List<UserGoods> userGoods = MyApplication.getContext().getSession().getUserGoodsDao().loadAll();
        List<User> users = MyApplication.getContext().getSession().getUserDao().loadAll();
        for (UserGoods goods : userGoods) {
            Log.v("GreenDao for goods", "查询：" + goods.getName() + " #### 用户ID:" + goods.getUserId() + "####" + goods.getDeadline());
        }
        for (User user : users) {
            Log.v("GreenDao for user", "查询：" + user.getName() + " @@@@@ " + user.getId() + "@@@@@ " + user.getUserGoodsList().size());
            for (UserGoods goods : user.getUserGoodsList()) {
                Log.v("GreenDao for goods 里面", "查询：用户名：" + user.getName() + "!!!商品名：" + goods.getName() + " ！！！！ 用户ID:" + goods.getUserId() + "！！！！" + goods.getDeadline());
            }
        }
    }

    private void addGoods() {
        UserGoods goods1 = new UserGoods(1L, "电脑", "2018-01-01", 20L);
        MyApplication.getContext().getSession().getUserGoodsDao().insert(goods1);
        UserGoods goods2 = new UserGoods(2L, "手机", "2018-11-01", 20L);
        MyApplication.getContext().getSession().getUserGoodsDao().insert(goods2);
    }

    /**
     * 增加
     */
    private void addUser() {
        try {
            User user = new User();
            user.setId(20L);
            user.setName("wj 1");
            user.setAge("18");
            user.setUserSonId(110L);

            User user1 = new User();
            user1.setId(21L);
            user1.setName("hannah 1");
            user1.setAge("28");
            user1.setUserSonId(111L);

            UserSon userSon = new UserSon(110L, "wj1 son");
            UserSon userSon1 = new UserSon(111L, "hannah1 son");
            addGoods();
            MyApplication.getContext().getSession().getUserDao().insert(user);
            MyApplication.getContext().getSession().getUserSonDao().insert(userSon);
            MyApplication.getContext().getSession().getUserDao().insert(user1);
            MyApplication.getContext().getSession().getUserSonDao().insert(userSon1);
            Log.v("GreenDao for user&son", "添加完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     */
    private void deleteUser() {
        MyApplication.getContext().getSession().getUserDao().deleteByKey(null);//按照key删除
        //        MyApplication.getContext().getSession().getUserDao().deleteAll();//删除所有
        //        MyApplication.getContext().getSession().getUserDao().delete();//删除一个bean
    }

    /**
     * 更新数据
     */
    private void updateUser() {
        //        MyApplication.getContext().getSession().getUserDao().update();
    }

    /**
     * 查询数据
     */
    private void searchUser() {
        //        User user = MyApplication.getContext().getSession().getUserDao().load(1);
        List<User> users = MyApplication.getContext().getSession().getUserDao().loadAll();
//        List<UserSon> userSons = MyApplication.getContext().getSession().getUserSonDao().loadAll();
        //        List<User> list = MyApplication.getContext().getSession().getUserDao().queryBuilder().where(UserDao.Properties.Name.eq("")).list();
        //        MyApplication.getContext().getSession().getUserDao().queryRaw("wj","");
        for (User user : users) {
            Log.v("GreenDao for user&Son", "查询：姓名：" + user.getName() + " --- 年龄：" + user.getAge() + "----用户ID:" + user.getId() + "----- 儿子姓名：" + user.getUserSon().getName());
        }
    }
}
