package com.life.mysql.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Goods)实体类
 *
 * @author tengyun
 * @since 2021-02-22 10:55:20
 */
public class Goods implements Serializable {
    private static final long serialVersionUID = 234743794021841683L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 商品名称
    */
    private String name;
    /**
    * 库存
    */
    private Integer stock;
    /**
    * 版本
    */
    private Integer version;
    /**
    * 秒杀开始时间
    */
    private Date startTime;
    /**
    * 秒杀结束时间
    */
    private Date endTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

}