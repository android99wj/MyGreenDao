package com.wj.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: WangJing
 * Date: 2018/8/1
 * Des: 用户的物品
 */
@Entity
public class UserGoods {
    @Id
    private Long id;
    private String name;
    private String deadline;

    //此处自定义userId，用于和User中对应
    private Long userId;


    public String getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
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

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Generated(hash = 1330480090)
    public UserGoods(Long id, String name, String deadline, Long userId) {
        this.id = id;
        this.name = name;
        this.deadline = deadline;
        this.userId = userId;
    }

    @Generated(hash = 367369332)
    public UserGoods() {
    }
}
