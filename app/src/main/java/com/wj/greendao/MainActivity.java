package com.wj.greendao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
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

//        addUser();
//        searchUser();
//        searchUserGoods();

        //测试多对多关系
        test2Many();
    }

    private void test2Many() {
        List<TeacherBean> teacherBeans = new ArrayList<>();
        String job = "";
        for (long i = 1; i <= 2; i++) {
            if (i == 1) {
                job = "语文";
            } else if (i == 2) {
                job = "数学";
            } else {
                job = "英语";
            }
            TeacherBean teacherBean = new TeacherBean(i, "老师 " + i, job);
            teacherBeans.add(teacherBean);
        }

        MyApplication.getContext().getSession().getTeacherBeanDao().insertInTx(teacherBeans);


        List<StudentBean> studentBeans = new ArrayList<>();
        int age;
        for (long i = 1; i <= 3; i++) {
            if (i == 1) {
                age = 18;
            } else if (i == 2) {
                age = 20;
            } else {
                age = 3;
            }
            StudentBean studentBean = new StudentBean(i, "学生 " + i, age);
            studentBeans.add(studentBean);
        }

        MyApplication.getContext().getSession().getStudentBeanDao().insertInTx(studentBeans);

        List<TeacherJoinStudentBean> joinStudentBeans = new ArrayList<>();
        //对应关系   老师1带 学生1 学生2
        TeacherJoinStudentBean joinStudentBean = new TeacherJoinStudentBean(null, 1L, 1L);
        joinStudentBeans.add(joinStudentBean);

        TeacherJoinStudentBean joinStudentBean1 = new TeacherJoinStudentBean(null, 1L, 2L);
        joinStudentBeans.add(joinStudentBean1);

        //老师2 带学生1 学生3

        TeacherJoinStudentBean joinStudentBean2 = new TeacherJoinStudentBean(null, 2L, 1L);
        joinStudentBeans.add(joinStudentBean2);

        TeacherJoinStudentBean joinStudentBean3 = new TeacherJoinStudentBean(null, 2L, 3L);
        joinStudentBeans.add(joinStudentBean3);

        MyApplication.getContext().getSession().getTeacherJoinStudentBeanDao().insertInTx(joinStudentBeans);

        //打印数据
        List<TeacherBean> allTeacherList = MyApplication.getContext().getSession().getTeacherBeanDao().loadAll();
        for (int i = 0; i < allTeacherList.size(); i++) {
            Log.v("GreenDao for Teacher", "查询：" + allTeacherList.get(i).getName() + " **** " + allTeacherList.get(i).getJob() + " &&& 学生个数：" + allTeacherList.get(i).getStudentBeanList().size());
            for (int j = 0; j < allTeacherList.get(i).getStudentBeanList().size(); j++) {
                Log.v("GreenDao for T&S", "查询：" + allTeacherList.get(i).getName() + " **** " + allTeacherList.get(i).getJob() + " &&& 学生个数：" + allTeacherList.get(i).getStudentBeanList().size() + " ！！！ 学生名字：" + allTeacherList.get(i).getStudentBeanList().get(j).getName() + " ##### 学生年龄：" + allTeacherList.get(i).getStudentBeanList().get(j).getAge());
            }
        }

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
