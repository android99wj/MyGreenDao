package com.wj.greendao;

import android.app.Application;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import com.wj.greendao.dao.DaoMaster;
import com.wj.greendao.dao.DaoSession;
import com.wj.greendao.dao.DaoUtil;
import com.wj.greendao.dao.DbHelper;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Hannah on 2018/2/6.
 */

public class MyApplication extends Application {
  private static final String DATA_NAME = "user";
  private        DaoSession session;
  private static Context    context;

  @Override
  public void onCreate() {
    super.onCreate();
    context = this;
    setUpDatabase();
  }

  public static synchronized MyApplication getContext() {
    return (MyApplication) context;
  }

  /**
   * 初始化数据库
   */
  public void setUpDatabase() {
    DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, DATA_NAME, null);
    //DaoMaster.OpenHelper devOpenHelper = new DbHelper(new DaoUtil(this));//更新数据库时候使用
    SQLiteDatabase database = devOpenHelper.getWritableDatabase();
    DaoMaster daoMaster = new DaoMaster(database);
    session = daoMaster.newSession();
  }

  public DaoSession getSession() {
    return session;
  }
}
