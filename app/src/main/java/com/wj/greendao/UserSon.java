package com.wj.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Author: WangJing
 * Date: 2018/8/1
 * Des:
 */

@Entity
public class UserSon {
    @Id
    private Long sonId;
    private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getSonId() {
        return this.sonId;
    }
    public void setSonId(Long sonId) {
        this.sonId = sonId;
    }
    @Generated(hash = 850860055)
    public UserSon(Long sonId, String name) {
        this.sonId = sonId;
        this.name = name;
    }
    @Generated(hash = 2075218724)
    public UserSon() {
    }
}
