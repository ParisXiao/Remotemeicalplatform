package com.gxey.remotemedicalplatform.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.media.AudioManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.adapter.MessageAdapter;
import com.gxey.remotemedicalplatform.common.ApiConstant;
import com.gxey.remotemedicalplatform.javaben.DoctorEntity;
import com.gxey.remotemedicalplatform.javaben.MessageEntity;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;
import com.gxey.remotemedicalplatform.utils.ImageUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.webrtc.MediaStream;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRendererGui;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import fr.pchab.webrtcclient.PeerConnectionParameters;
import fr.pchab.webrtcclient.SignalaUtils;
import fr.pchab.webrtcclient.WebRtcClient;
import me.iwf.photopicker.PhotoPicker;

/**
 * Created by xusongsong on 2016/12/26.
 * 视频咨询界面
 */

public class OverConsultationActivity extends BaseActivity implements View.OnClickListener, WebRtcClient.RtcListener {
    @BindView(R.id.editText2)
    EditText mETContent;
    @BindView(R.id.tv_sned)
    TextView mTVSend;
    @BindView(R.id.back)
    ImageView back;

    private DoctorEntity entity;
    private final static int VIDEO_CALL_SENT = 666;
    private static final String VIDEO_CODEC_VP9 = "VP9";
    private static final String AUDIO_CODEC_OPUS = "opus";
    // Local preview screen position before call is connected.
    private static final int LOCAL_X_CONNECTING = 0;
    private static final int LOCAL_Y_CONNECTING = 0;
    private static final int LOCAL_WIDTH_CONNECTING = 100;
    private static final int LOCAL_HEIGHT_CONNECTING = 100;
    // Local preview screen position after call is connected.
    private static final int LOCAL_X_CONNECTED = 72;
    private static final int LOCAL_Y_CONNECTED = 72;
    private static final int LOCAL_WIDTH_CONNECTED = 25;
    private static final int LOCAL_HEIGHT_CONNECTED = 25;
    // Remote video screen position
    private static final int REMOTE_X = 0;
    private static final int REMOTE_Y = 0;
    private static final int REMOTE_WIDTH = 100;
    private static final int REMOTE_HEIGHT = 100;
    private VideoRendererGui.ScalingType scalingType = VideoRendererGui.ScalingType.SCALE_ASPECT_FILL;
    private GLSurfaceView vsv;
    private VideoRenderer.Callbacks localRender;
    private VideoRenderer.Callbacks remoteRender;
    public static WebRtcClient client;
    public static OverConsultationActivity activity;
    private String mSocketAddress;
    private String callerId;
    private List<MessageEntity> listMessage = new ArrayList<>();
    @BindView(R.id.lv_message)
    ListView mLVMesage;
    private MessageAdapter messageAdapter;
    @BindView(R.id.oc_tv8)
    TextView mTVCancleDoctor;

    @BindView(R.id.rl_wait)
    RelativeLayout mmRLWait;

    @BindView(R.id.iv_send_img)
    ImageView ivSendImage;
    @BindView(R.id.iv_screenshot)
    ImageView ivScreenshot;
    @BindView(R.id.oc_tv4)
    TextView mTVWaitNumber;
    @BindView(R.id.oc_tv6)
    TextView mTVWaitPersion;
    @BindView(R.id.oc_img)
    ImageView mIVDoctorHead;
    @BindView(R.id.oc_tv9)
    TextView mTVDoctorNmae;
    @BindView(R.id.tv_prescription)
    TextView mTVPrescription;
    @BindView(R.id.tv_dotor_name)
    TextView mTVDoctorName;
    @BindView(R.id.oc_tv11)
    ImageView mIVPrescription;
    @BindView(R.id.imageView4)
    ImageView mIVDoc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_over_consultation;
    }

    @Override
    protected void initView() {
        //help.setOnClickListener(this);
        back.setOnClickListener(this);
        mIVDoc.setOnClickListener(this);
        entity = (DoctorEntity) getIntent().getSerializableExtra("entity");
        mTVWaitNumber.setText(entity.getSN());
        int time = Integer.parseInt(entity.getSN()) * 5;
        mTVWaitPersion.setText(time + "");
        mTVDoctorNmae.setText(entity.getUserName());
        mTVDoctorName.setText(entity.getUserName());
        ImageUtils.load(this, entity.getHeadImg(), mIVDoctorHead);
        messageAdapter = new MessageAdapter(listMessage, this);
        mLVMesage.setAdapter(messageAdapter);
        mIVPrescription.setOnClickListener(this);
        initVideo();
        //initGetMessage();
       // initAcceptMemberCallBack();
        //initsendLeaveCallBack();
       // initUnAcceptMemberCallBack();
       // initSendCaseDualCallBack();
        mTVSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = mETContent.getText().toString();
                if (!TextUtils.isEmpty(str)) {
                    mETContent.setText("");
                    sendMeaage(1, str);

                }

            }
        });
        mTVCancleDoctor.setOnClickListener(this);
        ivSendImage.setOnClickListener(this);
        ivScreenshot.setOnClickListener(this);
        mLVMesage.post(new Runnable() {
            @Override
            public void run() {
                mLVMesage.setSelection(messageAdapter.getCount() - 1);
            }
        });

        activity=this;
    }


    private void initVideo() {
        vsv = (GLSurfaceView) findViewById(R.id.glview_call);
        vsv.setPreserveEGLContextOnPause(true);
        vsv.setKeepScreenOn(true);
        vsv.setOnClickListener(this);
        VideoRendererGui.setView(vsv, new Runnable() {
            @Override
            public void run() {
                init();
            }
        });

        // local and remote render
        remoteRender = VideoRendererGui.create(
                REMOTE_X, REMOTE_Y,
                REMOTE_WIDTH, REMOTE_HEIGHT, scalingType, false);
        localRender = VideoRendererGui.create(
                LOCAL_X_CONNECTING, LOCAL_Y_CONNECTING,
                LOCAL_WIDTH_CONNECTING, LOCAL_HEIGHT_CONNECTING, scalingType, true);

        final Intent intent = getIntent();
        final String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            final List<String> segments = intent.getData().getPathSegments();
            callerId = segments.get(0);
        }
    }

    private void initAudio(){


        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        vsv.onPause();
//        if (client != null) {
//            client.onPause();
//        }
    }

    @Override
    public void onResume() {
        super.onResume();
        vsv.onResume();

    }

    @Override
    public void onDestroy() {
        if (client != null) {
            client.onDestroy();
        }
        activity=null;
        super.onDestroy();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                sendLeaveCallBack();
                // finish();
                break;
            case R.id.oc_tv8:
                sendCancleDotor();
                break;
            case R.id.iv_send_img:
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(false)
                        .setPreviewEnabled(false)
                        .start(this, PhotoPicker.REQUEST_CODE);

                break;
            case R.id.iv_screenshot:
                screenshot();
                break;
            case R.id.glview_call:
                videoConverter();
                break;

            case R.id.oc_tv11:
                if(mTVPrescription.getVisibility()==View.VISIBLE){
                    Intent intent = new Intent(OverConsultationActivity.this,WebBannerbenActgvity.class);
                    intent.putExtra("url", ApiConstant.H5BASE+ApiConstant.Recordbills+mConfig.getRoomID());
                    startActivity(intent);

                }

                break;

            case R.id.imageView4:
                Intent intent = new Intent(this, WebHealthRecordsActivity.class);
                startActivity(intent);

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
        if(isVideo){
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(mConfig.getRoomID());
            SignalaUtils.getInstance(this).sendMessage("sendLeave", jsonArray);
            Intent intent = new Intent(OverConsultationActivity.this, EvaluationActivity.class);
            intent.putExtra("roomID",client.getRoomID());
            intent.putExtra("doctorc",entity.getConnectionId());
            startActivity(intent);
        }

        finish();
    }

    /**
     * 通知患者医生离开离开
     */

    public void isendLeaveCallBack(){
        Intent intent = new Intent(OverConsultationActivity.this, EvaluationActivity.class);
        intent.putExtra("roomID",client.getRoomID());
        intent.putExtra("doctorc",entity.getConnectionId());
        AndroidUtil.showToast(OverConsultationActivity.this,"医生已经离开",0);
        startActivity(intent);
        finish();
    }


    /**
     * 通知医生取消排队
     */
    private void sendCancleDotor() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(entity.getConnectionId());
        SignalaUtils.getInstance(this).sendMessage("sendCancelDoctor", jsonArray);
        finish();
    }

    private void init() {
        Point displaySize = new Point();
        getWindowManager().getDefaultDisplay().getSize(displaySize);
        PeerConnectionParameters params = new PeerConnectionParameters(
                true, false, 240, 320, 30, 1, VIDEO_CODEC_VP9, true, 1, AUDIO_CODEC_OPUS, true);

        client = new WebRtcClient(this, this, mSocketAddress, params,VideoRendererGui.getEGLContext());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                sendLinkDoctor();
            }
        });
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

                            AndroidUtil.showToast(OverConsultationActivity.this, msg, 0);
                        }
                    });

                }
            }


    }

    private boolean isVideo=false;
    /**
     * 通知患者视频
     */
    public void acceptMemberCallBack(){
        startCam();
        isVideo=true;
        mmRLWait.setVisibility(View.GONE);
    }


    /**
     * 通知患者退出排队
     */
    public void unAcceptMemberCallBack(){
        AndroidUtil.showToast(OverConsultationActivity.this, "医生通知退出排队", 0);
        finish();
    }



    public void sendCaseDualCallBack(){
        mTVPrescription.setVisibility(View.VISIBLE);
    }

    public void initSDP(String connectionId){
        client.initOffe(connectionId);
    }



    public void getMessage(JSONArray args){

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
        messageAdapter.notifyDataSetChanged();

    }


    public void startCam() {
        // Camera settings
        client.start("android_test");
        initAudio();
    }

    /**
     * 患者选择医生
     */
    public void sendLinkDoctor() {

        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        try {
            json.put("targetid", entity.getConnectionId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.put(json);
        SignalaUtils.getInstance(this).sendMessage("sendLinkDoctor", jsonArray);
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
        localStream.videoTracks.get(0).addRenderer(new VideoRenderer(localRender));
        VideoRendererGui.update(localRender,
                LOCAL_X_CONNECTING, LOCAL_Y_CONNECTING,
                LOCAL_WIDTH_CONNECTING, LOCAL_HEIGHT_CONNECTING,
                scalingType, false);
    }

    @Override
    public void onAddRemoteStream(MediaStream remoteStream, int endPoint) {
        isLink=true;
        remoteStream.videoTracks.get(0).addRenderer(new VideoRenderer(remoteRender));
        VideoRendererGui.update(remoteRender,
                REMOTE_X, REMOTE_Y,
                REMOTE_WIDTH, REMOTE_HEIGHT, scalingType, false);
        VideoRendererGui.update(localRender,
                LOCAL_X_CONNECTED, LOCAL_Y_CONNECTED,
                LOCAL_WIDTH_CONNECTED, LOCAL_HEIGHT_CONNECTED,
                scalingType, false);
    }

    @Override
    public void onRemoveRemoteStream(int endPoint) {
        isLink=false;
        VideoRendererGui.update(localRender,
                LOCAL_X_CONNECTING, LOCAL_Y_CONNECTING,
                LOCAL_WIDTH_CONNECTING, LOCAL_HEIGHT_CONNECTING,
                scalingType, false);
    }


    private boolean isLink=false;
    private boolean isConverter=false;
    private void videoConverter(){
        if(isLink){
            if(isConverter){
                isConverter=false;
                VideoRendererGui.update(remoteRender,
                        REMOTE_X, REMOTE_Y,
                        REMOTE_WIDTH, REMOTE_HEIGHT, scalingType, false);
                VideoRendererGui.update(localRender,
                        LOCAL_X_CONNECTED, LOCAL_Y_CONNECTED,
                        LOCAL_WIDTH_CONNECTED, LOCAL_HEIGHT_CONNECTED,
                        scalingType, false);

            }else{
                isConverter=true;
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
}
