package com.example.nguyenthanhxuan.gamecuoiki.asset;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;

import com.example.nguyenthanhxuan.gamecuoiki.R;

import static android.content.Context.AUDIO_SERVICE;

/**
 * Created by Nguyen Thanh Xuan on 29/04/2018.
 */

public class Asset {

    MediaPlayer mediaPlayer;
    Context context;
    private SoundPool soundPool;
    private AudioManager audioManager;
    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;
    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdDestroy;
    private int soundIdGun;
    private float volume;

    public Asset(Context context){
        this.context= context;
    }

    public Bitmap loadMenuBackground(){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_win);
        return bitmap;
    }
    public Bitmap loadSpritePokemon(){
        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_pokemon);
        return bitmap;
    }

    public void initSounds(){
        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);
        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);
        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;
        // Với phiên bản Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // Với phiên bản Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }
        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });
        // Tải file nhạc tiếng vật thể bị phá hủy (destroy.war) vào SoundPool.
        this.soundIdDestroy = this.soundPool.load(context,R.raw.chim_cu_gay,1);
        this.soundIdDestroy = this.soundPool.load(context,R.raw.open_card_sound,2);
    }

    public void playOpenCardSound(){
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Phát âm thanh tiếng súng. Trả về ID của luồng mới phát ra.
            int streamId = this.soundPool.play(2,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }
    public void playBackgroundSound(){
        mediaPlayer= MediaPlayer.create(context, R.raw.background_sound);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void stopBackgroundSound(){
        mediaPlayer.stop();
    }
}
