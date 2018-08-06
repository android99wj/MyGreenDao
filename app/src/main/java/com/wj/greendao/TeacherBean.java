package com.wj.greendao;

import com.wj.greendao.dao.DaoSession;
import com.wj.greendao.dao.StudentBeanDao;
import com.wj.greendao.dao.TeacherBeanDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Author: WangJing
 * Date: 2018/8/6
 * Des: 演示多对多关系使用
 * 教师类
 */

@Entity
public class TeacherBean {
    @Id
    private Long id;
    private String name;
    private String job;

    //entity指的是绑定类
    //sourceProperty填写绑定类中标示自身的id，此处为tId，指TeacherBean的id
    //targetProperty填写绑定类中标示关联类的id，此处为sId，指StudentBean的id
    @ToMany
    @JoinEntity(entity = TeacherJoinStudentBean.class,
            sourceProperty = "tId",
            targetProperty = "sId"
    )
    private List<StudentBean> studentBeanList;

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

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 747728825)
    public synchronized void resetStudentBeanList() {
        studentBeanList = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 767851859)
    public List<StudentBean> getStudentBeanList() {
        if (studentBeanList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentBeanDao targetDao = daoSession.getStudentBeanDao();
            List<StudentBean> studentBeanListNew = targetDao._queryTeacherBean_StudentBeanList(id);
            synchronized (this) {
                if(studentBeanList == null) {
                    studentBeanList = studentBeanListNew;
                }
            }
        }
        return studentBeanList;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1588249045)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTeacherBeanDao() : null;
    }

    /** Used for active entity operations. */
    @Generated(hash = 121856788)
    private transient TeacherBeanDao myDao;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

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

    @Generated(hash = 1686975550)
    public TeacherBean(Long id, String name, String job) {
        this.id = id;
        this.name = name;
        this.job = job;
    }

    @Generated(hash = 1376380279)
    public TeacherBean() {
    }

}
