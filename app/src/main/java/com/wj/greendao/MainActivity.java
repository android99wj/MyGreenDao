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
    }

    /**
     * 增加
     */
    private void addUser() {
        try {
            User user = new User(null, "wj", "18", 100L);
            User user1 = new User(null, "hannah", "28", 101L);
            UserSon userSon = new UserSon(100L, "wj son");
            UserSon userSon1 = new UserSon(101L, "hannah son");
            MyApplication.getContext().getSession().getUserDao().insert(user);
            MyApplication.getContext().getSession().getUserDao().insert(user1);
            MyApplication.getContext().getSession().getUserSonDao().insert(userSon);
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
            Log.v("GreenDao for user&Son", "查询：" + user.getName() + " --- " + user.getAge() + "-----" + user.getUserSon().getName());
        }
    }
}
