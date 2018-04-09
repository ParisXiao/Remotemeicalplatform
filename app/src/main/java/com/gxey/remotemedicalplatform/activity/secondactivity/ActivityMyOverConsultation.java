package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.activity.EvaluationActivity;
import com.gxey.remotemedicalplatform.activity.WebBannerbenActgvity;
import com.gxey.remotemedicalplatform.adapter.MessageAdapter;
import com.gxey.remotemedicalplatform.application.LocalApplication;
import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.javaben.DoctorEntity;
import com.gxey.remotemedicalplatform.javaben.MessageEntity;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.newconfig.UrlConfig;
import com.gxey.remotemedicalplatform.newconfig.UserConfig;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;
import com.gxey.remotemedicalplatform.utils.PreferenceUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.webrtc.MediaStream;
import org.webrtc.RendererCommon;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRendererGui;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import cn.nodemedia.NodeCameraView;
import cn.nodemedia.NodePublisher;
import cn.nodemedia.NodePublisherDelegate;
import fr.pchab.webrtcclient.PeerConnectionParameters;
import fr.pchab.webrtcclient.SignalaUtils;
import fr.pchab.webrtcclient.WebRtcClient;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by xusongsong on 2016/12/26.
 * 视频咨询界面
 */

public class ActivityMyOverConsultation extends BaseActivity implements NodePublisherDelegate, View.OnClickListener, WebRtcClient.RtcListener {

    @BindView(R.id.GL_text_left)
    TextView GLTextLeft;
    @BindView(R.id.GL_text_mid)
    TextView GLTextMid;
    @BindView(R.id.GL_text_right)
    TextView GLTextRight;
    @BindView(R.id.GL_edit_say)
    EditText GLEditSay;
    @BindView(R.id.GL_btn_send)
    ImageView GLBtnSend;
    @BindView(R.id.GL_btn_liaotian)
    ImageView GLBtnLiaotian;
    @BindView(R.id.re_liaotian)
    RelativeLayout reLiaotian;
    @BindView(R.id.GL_btn_sendimg)
    ImageView GLBtnSendimg;
    @BindView(R.id.GL_btn_getimg)
    ImageView GLBtnGetimg;
    @BindView(R.id.GL_btn_over)
    ImageView GLBtnOver;
    @BindView(R.id.camera_preview)
    NodeCameraView npv;
    private DoctorEntity entity;
    private static final String VIDEO_CODEC_VP9 = "VP9";
    private static final String AUDIO_CODEC_OPUS = "opus";
    static final int LOCAL_HEIGHT_CONNECTED = 25;
    private static final int LOCAL_WIDTH_CONNECTED = 25;
    private static final int LOCAL_X_CONNECTED = 72;
    private static final int LOCAL_Y_CONNECTED = 72;
    private static final int REMOTE_HEIGHT = 100;
    private static final int REMOTE_WIDTH = 100;
    private static final int REMOTE_X = 0;
    private static final int REMOTE_Y = 0;
    private RendererCommon.ScalingType scalingType = RendererCommon.ScalingType.SCALE_ASPECT_FILL;
    private GLSurfaceView vsv;
    private VideoRenderer.Callbacks localRender;
    private VideoRenderer.Callbacks remoteRender;
    public static WebRtcClient client;
    public static ActivityMyOverConsultation activity;
    private List<MessageEntity> listMessage = new ArrayList<>();
    private MessageAdapter messageAdapter;
    private String connectionId = "";
    private boolean isShow = false;
    private PopupWindow window;
    private NodePublisher np;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_over_myconsultation;
    }

    @Override
    protected void initView() {
        activity = this;
        entity = (DoctorEntity) getIntent().getSerializableExtra("entity");
        connectionId = getIntent().getStringExtra("connectionId");
        Log.d("connectionId", connectionId);
        initVideo();
        initAudio();
        if (LocalApplication.getInstance().isRTMP) {
            initRTMP(this);
        }


        //initGetMessage();
        // initAcceptMemberCallBack();
        //initsendLeaveCallBack();
        // initUnAcceptMemberCallBack();
        // initSendCaseDualCallBack();
        GLTextLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyOverConsultation.this, ActivityHeathDangAn.class);
                startActivity(intent);
            }
        });
        GLTextRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMyOverConsultation.this, ActivityHeathDianZi.class);
                startActivity(intent);
            }
        });
        GLTextMid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GLTextMid.getVisibility() == View.VISIBLE) {
                    Intent intent = new Intent(ActivityMyOverConsultation.this, WebBannerbenActgvity.class);
                    intent.putExtra("url", ApiConstant.H5BASE + ApiConstant.Recordbills + mConfig.getRoomID());
                    startActivity(intent);

                }
            }
        });
        /**
         * 发送消息
         */
        GLBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = GLEditSay.getText().toString();
                if (!TextUtils.isEmpty(str)) {
                    GLEditSay.setText("");
                    sendMeaage(1, str);
                    initPopupWindow(v);
                }

            }
        });
        /**
         * 挂断退出
         */
        GLBtnOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LocalApplication.getInstance().isRTMP) {
                    np.stop();
                }
                sendLeaveCallBack();
            }
        });
        /**
         * 发送图片
         */
        GLBtnSendimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .start(ActivityMyOverConsultation.this, PhotoPicker.REQUEST_CODE);
            }
        });
        /**
         * 截图
         */
        GLBtnGetimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screenshot();

            }
        });

//        mLVMesage.post(new Runnable() {
//            @Override
//            public void run() {
//                mLVMesage.setSelection(messageAdapter.getCount() - 1);
//            }
//        });
        GLBtnLiaotian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShow) {
                    isShow = false;
                    window.dismiss();
                } else {
                    isShow = true;
                    initPopupWindow(v);
                }

            }
        });

    }

    private void initRTMP(Context c) {
        np = new NodePublisher(c);
        np.setNodePublisherDelegate(this);
        np.setCameraPreview(npv, NodePublisher.CAMERA_FRONT, true);
        np.setAudioParam(32 * 1000, NodePublisher.AUDIO_PROFILE_HEAAC);
        np.setVideoParam(NodePublisher.VIDEO_PPRESET_16X9_360, 24, 500 * 1000, NodePublisher.VIDEO_PROFILE_MAIN, false);
        np.setDenoiseEnable(true);
        np.setBeautyLevel(3);
        np.setOutputUrl(UrlConfig.RTMPURL+ PreferenceUtils.getInstance(ActivityMyOverConsultation.this).getString(UserConfig.UserId));
        Log.d("RTMPurl",UrlConfig.RTMPURL+ PreferenceUtils.getInstance(ActivityMyOverConsultation.this).getString(UserConfig.UserId));
        /**
         * @brief rtmpdump 风格的connect参数
         * Append arbitrary AMF data to the Connect message. The type must be B for Boolean, N for number, S for string, O for object, or Z for null.
         * For Booleans the data must be either 0 or 1 for FALSE or TRUE, respectively. Likewise for Objects the data must be 0 or 1 to end or begin an object, respectively.
         * Data items in subobjects may be named, by prefixing the type with 'N' and specifying the name before the value, e.g. NB:myFlag:1.
         * This option may be used multiple times to construct arbitrary AMF sequences. E.g.
         */
        np.setConnArgs("S:info O:1 NS:uid:10012 NB:vip:1 NN:num:209.12 O:0");
        np.startPreview();
        int ret = np.start();
        Log.e("NP", "start ret :" + ret);
    }

    private void initPopupWindow(View v) {
// 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popup_msg, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        window = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        window.setAnimationStyle(R.style.popupwindow_anim);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        ImageView exit;
        final ListView msglist;
        exit = (ImageView) contentView.findViewById(R.id.popup_exit);
        msglist = (ListView) contentView.findViewById(R.id.popup_listview);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        messageAdapter = new MessageAdapter(listMessage, this);
        msglist.setAdapter(messageAdapter);
        msglist.post(new Runnable() {
            @Override
            public void run() {
                msglist.setSelection(messageAdapter.getCount() - 1);
            }
        });
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(reLiaotian);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        window.showAtLocation(reLiaotian, Gravity.TOP, 0, 0);

    }

    private void initVideo() {
        vsv = (GLSurfaceView) findViewById(R.id.glview_call);
        vsv.setPreserveEGLContextOnPause(true);
        vsv.setKeepScreenOn(true);
        vsv.setOnClickListener(this);

        // local and remote render
        VideoRendererGui.setView(vsv, new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
        remoteRender = VideoRendererGui.create(
                REMOTE_X, REMOTE_Y,
                REMOTE_WIDTH, REMOTE_HEIGHT, scalingType, false);
            localRender = VideoRendererGui.create(
                    LOCAL_X_CONNECTED, LOCAL_Y_CONNECTED,
                    LOCAL_WIDTH_CONNECTED, LOCAL_HEIGHT_CONNECTED, scalingType, true);

    }

    private void initAudio() {
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        vsv.onPause();
        if (client != null) {
            client.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        vsv.onResume();
        if (client != null) {
            client.onResume();
        }
    }

    @Override
    public void onDestroy() {
        if (client != null) {
            client.onDestroy();
        }
        activity = null;
        if (LocalApplication.getInstance().isRTMP){
            np.stopPreview();
            np.stop();
            np.release();
        }
        super.onDestroy();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.glview_call:
                if (LocalApplication.getInstance().isRTMP) {
                   return;
                }
                videoConverter();
                break;
        }
    }


    private void screenshot() {
        SimpleDateFormat
                sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
        String fname = "/sdcard/" + sdf.format(new Date()) + ".png";

        View view = getWindow().getDecorView();

        view.setDrawingCacheEnabled(true);

        view.buildDrawingCache();

        Bitmap bitmap = view.getDrawingCache();

        if (bitmap != null) {
            try {
                FileOutputStream out = new FileOutputStream(fname);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                        out);
                mHttpHelper.upLoadImg(fname, new HttpSubseiber.ResponseHandler<String>() {
                    @Override
                    public void onSucceed(String data) {
                        sendMeaage(2, data);
                    }

                    @Override
                    public void onFail(String msg) {

                    }
                });


            } catch (Exception
                    e) {

                e.printStackTrace();

            }

        }


    }

    private void sendLeaveCallBack() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(mConfig.getRoomID());
        SignalaUtils.getInstance(this).sendMessage("sendLeave", jsonArray);
        Intent intent = new Intent(ActivityMyOverConsultation.this, EvaluationActivity.class);
        intent.putExtra("roomID", client.getRoomID());
        intent.putExtra("doctorc", entity.getConnectionId());
        startActivity(intent);
        finish();
    }

    /**
     * 通知患者医生离开离开
     */

    public void isendLeaveCallBack() {
        Intent intent = new Intent(ActivityMyOverConsultation.this, EvaluationActivity.class);
        intent.putExtra("roomID", client.getRoomID());
        intent.putExtra("doctorc", entity.getConnectionId());
        AndroidUtil.showToast(ActivityMyOverConsultation.this, "医生已经离开", 0);
        startActivity(intent);
        finish();
    }


    private void init() {
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        PeerConnectionParameters params = new PeerConnectionParameters(
                true, false, 240, 320, 15, 1, VIDEO_CODEC_VP9, true, 1, AUDIO_CODEC_OPUS, true);
        client = new WebRtcClient(this, localRender, remoteRender, this, params,LocalApplication.getInstance().isRTMP);
        client.initOffe(connectionId);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                mHttpHelper.upLoadImg(photos.get(0), new HttpSubseiber.ResponseHandler<String>() {
                    @Override
                    public void onSucceed(String data) {
                        sendMeaage(2, data);

                    }

                    @Override
                    public void onFail(String msg) {

                        AndroidUtil.showToast(ActivityMyOverConsultation.this, msg, 0);
                    }
                });

            }
        }


    }

    private boolean isVideo = false;


    public void sendCaseDualCallBack() {
        GLTextMid.setVisibility(View.VISIBLE);
    }


    public void getMessage(JSONArray args) {
        if (!isShow) {
            GLBtnLiaotian.performClick();
        }
        MessageEntity messageEntity = new MessageEntity();
        for (int i = 0; i < args.length(); i++) {
            try {
                JSONObject jsonObject = args.getJSONObject(i);
                if (jsonObject.has("HeadImg")) {
                    messageEntity.setHeadImg(jsonObject.getString("HeadImg"));
                } else {
                    messageEntity.setConnectionId(jsonObject.getString("ConnectionId"));
                    messageEntity.setCenum(jsonObject.getInt("cenum"));
                    messageEntity.setContent(jsonObject.getString("content"));
                    messageEntity.setCreatetime(jsonObject.getString("createtime"));
                    messageEntity.setSuffix(jsonObject.getString("suffix"));
                    messageEntity.setDirection(0);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (messageEntity.getConnectionId().equals(entity.getConnectionId())) {
            listMessage.add(messageEntity);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    messageAdapter.notifyDataSetChanged();
                }
            });
        }
    }


    /**
     * 类型 1.纯文本，2.图片，3.连接，4.超文本
     *
     * @param type
     * @param str
     */
    private void sendMeaage(int type, String str) {
        if (!isShow) {
            GLBtnLiaotian.performClick();
        }
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        try {
            json.put("cenum", type);
            json.put("content", str);
            json.put("length", str.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.put(json);
        jsonArray.put(mConfig.getRoomID());
        SignalaUtils.getInstance(this).sendMessage("sendMsg", jsonArray);


        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setHeadImg(mConfig.getHeadImg());
        messageEntity.setContent(str);
        messageEntity.setCenum(type);
        messageEntity.setDirection(1);
        listMessage.add(messageEntity);
//        messageAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCallReady(String callId) {

    }

    @Override
    public void onCallReady2(String callId) {

    }

    @Override
    public void onStatusChanged(String newStatus) {

    }


    @Override
    public void onLocalStream(MediaStream localStream) {

    }

    @Override
    public void onAddRemoteStream(MediaStream remoteStream, int endPoint) {
        isLink = true;
    }

    @Override
    public void onRemoveRemoteStream(int endPoint) {
        isLink = false;
    }


    private boolean isLink = false;
    private boolean isConverter = false;

    private void videoConverter() {
        if (isLink) {
            if (isConverter) {
                isConverter = false;
                VideoRendererGui.update(remoteRender,
                        REMOTE_X, REMOTE_Y,
                        REMOTE_WIDTH, REMOTE_HEIGHT, scalingType, false);

                    VideoRendererGui.update(localRender,
                            LOCAL_X_CONNECTED, LOCAL_Y_CONNECTED,
                            LOCAL_WIDTH_CONNECTED, LOCAL_HEIGHT_CONNECTED,
                            scalingType, false);
            } else {
                isConverter = true;
                VideoRendererGui.update(remoteRender,
                        LOCAL_X_CONNECTED, LOCAL_Y_CONNECTED,
                        LOCAL_WIDTH_CONNECTED, LOCAL_HEIGHT_CONNECTED,
                        scalingType, false);

                    VideoRendererGui.update(localRender,
                            REMOTE_X, REMOTE_Y,
                            REMOTE_WIDTH, REMOTE_HEIGHT, scalingType, false);

            }


        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        sendLeaveCallBack();
    }

    @Override
    public void onEventCallback(NodePublisher nodePublisher, int i, String s) {

    }
}
