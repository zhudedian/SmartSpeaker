package com.lingan.edongspeechlibrary.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.lingan.edongspeechlibrary.bean.MusicBean;

import com.lingan.edongspeechlibrary.bean.NetFM3Bean;
import com.lingan.edongspeechlibrary.bean.NetFMBean;
import com.lingan.edongspeechlibrary.bean.NewsBean;
import com.lingan.edongspeechlibrary.bean.ResultBean;
import com.lingan.edongspeechlibrary.utils.Constant;

import java.io.IOException;
import java.util.List;


/**
 * Created by dyx on 2017/12/15.
 * 提示音，音乐，笑话，故事，新闻 等播放
 */

public class MediaUtil {

    private static MediaPlayer mediaPlayer;
    private static boolean isSinglePlay = false;
    private static List resList;
    public static int fastMills = 0;
    /**
     * @param playType 播放类型
     */
    public static void play(Context context, ResultBean.PlayType playType, List resList, int position) {
        if (resList!=null) {
            MediaUtil.resList = resList;
        }
        switch (playType) {
            case TIP:
                // 播放提示音
                playLocalRaw(context, resList);
                break;
            case MUSIC:
                playRemoteUrl(playType, resList, position);
                break;
            case NETFM:
            case NETFM3:
                playRemoteUrl(playType, resList, position);
                break;
            case NEWS:
                playRemoteUrl(playType, resList, position);
            case PAUSE:
                pause();
                break;
            case RESUME:
                resume();
            case NO:
                break;
            case FAST:
                fastForward(fastMills);
            default:
                break;
        }

    }

    /**
     * 设置单曲循环
     */
    public static void setSinglePlay(){
        isSinglePlay = true;
    }

    /**
     * 移除单曲循环
     */
    public static void removeSinglePlay(){
        isSinglePlay = false;
    }

    /**
     * 快进
     */
    private static void fastForward(int time){
        if (mediaPlayer!=null) {
            int totalTimes = mediaPlayer.getDuration();
            int seekTime = mediaPlayer.getCurrentPosition() + time;
            if (seekTime>totalTimes){
                seekTime = totalTimes;
            }
            if (seekTime<0){
                seekTime = 0;
            }
            final int mills = seekTime;
            try {
                mediaPlayer.stop();
                mediaPlayer.prepare();
                mediaPlayer.seekTo(mills);
                mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
                    public void onSeekComplete(MediaPlayer m) {
                        m.start();
                    }
                });


            } catch (IOException e) {

            }

        }
    }
    /**
     * 播放提示音
     *
     * @param context context
     */
    private static void playLocalRaw(Context context, List resList) {

        // 如果正在播放其他提示音 先停掉
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                mediaPlayer.release();
            } catch (Exception e) {
            }
        }
        // 播放提示音
        mediaPlayer = MediaPlayer.create(context, (int) resList.get(0));
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });

    }

    /**
     * 获取歌手名
     */
    public static String getPlayingMusicArtist(){
        String artist= "";
        if (resList!=null){
            artist = ((MusicBean.DbdataBean)resList.get(Constant.musicPlayPosition)).getArtist();
        }
        return artist;
    }
    /**
     * 获取歌曲名
     */
    public static String getPlayingMusicName(){
        String name= "";
        if (resList!=null){
            name = ((MusicBean.DbdataBean)resList.get(Constant.musicPlayPosition)).getTitle();
        }
        return name;
    }
    /**
     * 播放 url
     */
    private static void playRemoteUrl(final ResultBean.PlayType playType, final List resList, final int position) {

        if(resList==null||resList.size()==0)
            // 音乐为空
            return;

        String url = "";
        if (playType == ResultBean.PlayType.MUSIC) {
            url = ((MusicBean.DbdataBean) resList.get(position)).getUrl();
        } else if (playType == ResultBean.PlayType.NETFM) {
            url = ((NetFMBean.DbdataBean) resList.get(position)).getPlayUrl32();
        } else if (playType == ResultBean.PlayType.NETFM3) {
            url = ((NetFM3Bean.ResultBean.SdsBean.DataBean.DbdataBean) resList.get(position)).getRate64_aac_url();
        }else if (playType == ResultBean.PlayType.NEWS) {
            url = ((NewsBean.DbdataBean) resList.get(position)).getMp3PlayUrl32();
        }

        // 如果正在播放其他提示音 先停掉
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        MediaUtil.removeSinglePlay();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                // 播放下一首
                String next_url = "";
                if (playType == ResultBean.PlayType.MUSIC) {
                    if (isSinglePlay){
                        next_url = ((MusicBean.DbdataBean) resList.get(Constant.musicPlayPosition)).getUrl();
                    }else {
                        if (Constant.musicPlayPosition == resList.size() - 1)
                            Constant.musicPlayPosition = -1;
                        next_url = ((MusicBean.DbdataBean) resList.get(++Constant.musicPlayPosition)).getUrl();
                    }
                }
                if (!next_url.isEmpty()) {
                    try {
                        mediaPlayer.reset();
                        //mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(next_url);
                        mediaPlayer.setLooping(false);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        mediaPlayer.release();
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    /**
     * 讲笑话
     */
    public static void sayJoke(String url){
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        });
    }

    /**
     * 暂停当前正在播放的音乐
     */
    private static void pause() {
        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    Constant.IS_WAKEUP_PAUSE_MUSIC = true;
                } else {
                    Constant.IS_WAKEUP_PAUSE_MUSIC = false;
                }
            } catch (Exception e) {
                Constant.IS_WAKEUP_PAUSE_MUSIC = false;
                mediaPlayer = null;
            }

        }
    }

    /**
     * 恢复当前正在播放的音乐
     */
    private static void resume() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    /**
     * 正在播放音乐 - - - 暂停
     * 暂停播放音乐 - - - 恢复
     */
    public static void pauseResume() {

        if (mediaPlayer != null) {
            try {
                if (mediaPlayer.isPlaying())
                    mediaPlayer.pause();
                else
                    mediaPlayer.start();
            } catch (Exception e) {
                mediaPlayer = null;
            }
        }

    }


    /**
     * 停掉正在播放的提示音
     */
    public static void stop() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying())
                mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    public static boolean isPlaying(){
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying())
                    return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }


}