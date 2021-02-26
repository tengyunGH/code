package com.life.seckill.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SeckillOrder)实体类
 *
 * @author tengyun
 * @since 2021-02-22 19:53:59
 */
public class SeckillOrder implements Serializable {
    private static final long serialVersionUID = -68032439676944832L;
    
    private Long id;
    
    private Long userId;
    
    private Long goodsId;
    
    private Integer state;
    
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}