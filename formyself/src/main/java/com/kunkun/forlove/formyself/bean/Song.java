package com.kunkun.forlove.formyself.bean;

import java.io.Serializable;

/**
 * com.baituoyitong.kun.baituoyitong.mainpackage.bean
 * <p>
 * Created by ${kun} on 2017/4/27.
 */

public class Song implements Serializable{
    /**
     * Created by user on 2017/4/27. * 放置音乐的bean
     */
    /**
     * 歌手
     */
    public String singer;
    /**
     * 歌曲名
     */
    public String song;
    /**
     * 歌曲的地址
     */
    public String path;
    /**
     * 歌曲长度
     */
    public int duration;
    /**
     * 歌曲的大小
     */
    public long size;


}
