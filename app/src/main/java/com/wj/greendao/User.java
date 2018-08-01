package com.wj.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.wj.greendao.dao.DaoSession;
import com.wj.greendao.dao.UserDao;
import com.wj.greendao.dao.UserSonDao;

import java.util.List;
import com.wj.greendao.dao.UserGoodsDao;

/**
 * Created by Hannah on 2018/2/6.
 */

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    //添加age字段
    private String age;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1478353602)
    public void setUserSon(UserSon userSon) {
        synchronized (this) {
            this.userSon = userSon;
            userSonId = userSon == null ? null : userSon.getSonId();
            userSon__resolvedKey = userSonId;
        }
    }
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 721201876)
    public UserSon getUserSon() {
        Long __key = this.userSonId;
        if (userSon__resolvedKey == null || !userSon__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserSonDao targetDao = daoSession.getUserSonDao();
            UserSon userSonNew = targetDao.load(__key);
            synchronized (this) {
                userSon = userSonNew;
                userSon__resolvedKey = __key;
            }
        }
        return userSon;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2059241980)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDao() : null;
    }
    public Long getUserSonId() {
        return this.userSonId;
    }
    public void setUserSonId(Long userSonId) {
        this.userSonId = userSonId;
    }
    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 337006195)
    public synchronized void resetUserGoodsList() {
        userGoodsList = null;
    }
    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 147086231)
    public List<UserGoods> getUserGoodsList() {
        if (userGoodsList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserGoodsDao targetDao = daoSession.getUserGoodsDao();
            List<UserGoods> userGoodsListNew = targetDao._queryUser_UserGoodsList(id);
            synchronized (this) {
                if(userGoodsList == null) {
                    userGoodsList = userGoodsListNew;
                }
            }
        }
        return userGoodsList;
    }

    private Long userSonId;

    //一对一
    @ToOne(joinProperty = "userSonId")
    private UserSon userSon;

    //一对多 此处的userId是在UserGoods中定义的一个变量
    @ToMany(referencedJoinProperty = "userId")
    private List<UserGoods> userGoodsList;

    @Generated(hash = 311550425)
    private transient Long userSon__resolvedKey;
    /** Used for active entity operations. */
    @Generated(hash = 1507654846)
    private transient UserDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    @Generated(hash = 1307127814)
    public User(Long id, String name, String age, Long userSonId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userSonId = userSonId;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}