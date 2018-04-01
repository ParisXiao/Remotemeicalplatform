package com.gxey.remotemedicalplatform.activity.secondactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gxey.remotemedicalplatform.R;
import com.gxey.remotemedicalplatform.activity.BaseActivity;
import com.gxey.remotemedicalplatform.adapter.DoctorAdapter;
import com.gxey.remotemedicalplatform.javaben.DoctorEntity;
import com.gxey.remotemedicalplatform.javaben.WDoctorEntity;
import com.gxey.remotemedicalplatform.network.HttpSubseiber;
import com.gxey.remotemedicalplatform.network.SendPushSigleR;
import com.gxey.remotemedicalplatform.utils.AndroidUtil;
import com.gxey.remotemedicalplatform.utils.MyStrUtil;
import com.gxey.remotemedicalplatform.utils.ScreenUtils;
import com.gxey.remotemedicalplatform.utils.SizeUtils;
import com.gxey.remotemedicalplatform.widget.EmptyLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.pchab.webrtcclient.SignalaUtils;

import static com.gxey.remotemedicalplatform.R.id.toolbar_left_btn;
import static com.gxey.remotemedicalplatform.utils.KeyboardUtil.hideInputMethod;
import static com.gxey.remotemedicalplatform.utils.KeyboardUtil.isShouldHideInput;

/**
 * Created by Administrator on 2018-03-02.
 */

public class ActivityDoctorList extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.toolbar_mid)
    TextView toolbarMid;
    @BindView(toolbar_left_btn)
    ImageButton toolbarLeftBtn;
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.sousuo_doctor)
    EditText sousuoDoctor;
    @BindView(R.id.text_all)
    TextView textAll;
    @BindView(R.id.text_online)
    TextView textOnline;
    @BindView(R.id.text_outline)
    TextView textOutline;
    @BindView(R.id.recyclerView_doctor)
    RecyclerView recyclerViewDoctor;
    @BindView(R.id.lay_refresh_doctor)
    SwipeRefreshLayout layRefreshDoctor;
    @BindView(R.id.emptyLayout_doctor)
    EmptyLayout emptyLayoutDoctor;
    @BindView(R.id.ys_btn_sousuo)
    ImageButton ysBtnSousuo;
    private DoctorAdapter adapter;
    private List<DoctorEntity> doctorBeen;
    private Handler handler = new Handler();

    private String Type = "0";// 1.离线，2.全部,0.在线
    private  PopupWindow window;
    private DoctorEntity doctorEntity;
    public static ActivityDoctorList activityDoctor;
    public String contectid="";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_doctorlist;
    }

    @Override
    protected void initView() {
        toolbarLeftBtn.setVisibility(View.VISIBLE);
        toolbarLeftBtn.setOnClickListener(this);
        toolbarRight.setTextColor(getResources().getColor(R.color.background_green));
        toolbarMid.setText(R.string.yisheng);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ScreenUtils.setStatusBarLightMode(this, R.color.black);
        doctorBeen = new ArrayList<>();
        doctorBeen.addAll(SendPushSigleR.list);
        recyclerViewDoctor.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewDoctor.setAdapter(adapter = new DoctorAdapter(this, doctorBeen));
        //绑定
        emptyLayoutDoctor.bindView(recyclerViewDoctor);
        emptyLayoutDoctor.setOnButtonClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新加载数据
                if (Type.equals("0")) {
                    doctorBeen.clear();
                    doctorBeen.addAll(SendPushSigleR.list);
                    adapter.notifyDataSetChanged();
                } else {
                    getDoctor("");
                }
            }
        });
        layRefreshDoctor.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        layRefreshDoctor.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (Type.equals("0")) {
                    doctorBeen.clear();
                    doctorBeen.addAll(SendPushSigleR.list);
                    adapter.notifyDataSetChanged();
                } else {
                    getDoctor("");
                }
                layRefreshDoctor.setRefreshing(false);

            }
        });
        activityDoctor=this;
    }

    private void getDoctor(String userName) {
        emptyLayoutDoctor.showLoading(this);
        mHttpHelper.getDoctor(userName, Type, new HttpSubseiber.ResponseHandler<List<WDoctorEntity>>() {
            @Override
            public void onSucceed(List<WDoctorEntity> data) {
                emptyLayoutDoctor.showSuccess();
                doctorBeen.clear();
                for (WDoctorEntity entity : data) {
                    DoctorEntity doctorEntity = new DoctorEntity();
                    doctorEntity.setUserName(entity.getUName());
                    doctorEntity.setDivision(entity.getManageDeptName());
                    doctorEntity.setHeadImg(entity.getHeadImg());
                    doctorEntity.setConnectionId(entity.getID());
                    doctorEntity.setLogout(false);
                    doctorEntity.setPosition(entity.getPositionName());
                    doctorBeen.add(doctorEntity);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFail(String msg) {
                emptyLayoutDoctor.showError("重新加载"); // 显示失败
                AndroidUtil.showToast(ActivityDoctorList.this, msg, 0);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                if (hideInputMethod(this, v)) {
                    return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    /**
     * 登录
     */
    public void loginUser(){
        JSONArray jsonArray=new JSONArray();

        JSONObject json = new JSONObject();
        try {
            json.put("IEGUID",mConfig.getDeviceId());
            json.put("Username",mConfig.getUserName());
            json.put("UserType","1");
            json.put("GUID",mConfig.getUserGUID());
            json.put("Division", mConfig.getDivision());
            json.put("Position","会员");
            json.put("StoreID",mConfig.getStoreID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.put(json);

        SignalaUtils.getInstance(this).sendMessage("sendLogin",jsonArray);
    }
    private MyBroadCastReceiver myBroadCastReceiver;
    private void regDoctorList(){
        myBroadCastReceiver  = new MyBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter(SendPushSigleR.DOCTORLISTCHEOSE);
        registerReceiver(myBroadCastReceiver,intentFilter);
    }
    class MyBroadCastReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            doctorBeen.clear();
            doctorBeen.addAll(SendPushSigleR.list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityDoctor=null;
        // SignalaUtils.getInstance(this).getHub().
        if(myBroadCastReceiver!=null){
            unregisterReceiver(myBroadCastReceiver);
        }
    }

    @Override
    protected void initData() {
        loginUser();
        regDoctorList();
//        initLoadMoreListener();
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        ysBtnSousuo.setOnClickListener(this);
        textAll.setTextColor(getResources().getColor(R.color.text_black));
        textOnline.setTextColor(getResources().getColor(R.color.background_green));
        textOutline.setTextColor(getResources().getColor(R.color.text_black));
        textAll.setOnClickListener(this);
        textOnline.setOnClickListener(this);
        textOutline.setOnClickListener(this);
        sousuoDoctor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                Log.d("Edit", "" + actionId + "," + event);
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_SEARCH)) {
                    String search = sousuoDoctor.getText().toString();
                    if (!TextUtils.isEmpty(search)) {
                        if (TextUtils.isEmpty(Type)) {
                            List<DoctorEntity> temp = new ArrayList<>();
                            Pattern pn = Pattern.compile(search + "\\w|\\w" + search + "\\w|\\w" + search);
                            Matcher mr = null;
                            for (DoctorEntity entity : doctorBeen) {
                                mr = pn.matcher(entity.getUserName());
                                if (mr.find()) {
                                    temp.add(entity);
                                }
                            }
                            doctorBeen.clear();
                            doctorBeen.addAll(temp);
                            adapter.notifyDataSetChanged();
                        } else {

                            getDoctor(search);

                        }
                        hideInputMethod(ActivityDoctorList.this, v);
                    }
                    //do something;
                    return true;
                }
                return false;
            }
        });
        adapter.setOnItemClickListener(new DoctorAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, DoctorAdapter.ViewName viewName, final int position) {
                if (viewName== DoctorAdapter.ViewName.Button){
//                    Intent intent = new Intent(ActivityDoctorList.this,OverConsultationActivity.class);
//                    intent.putExtra("entity",doctorBeen.get(position));
//                    startActivity(intent);
                    doctorEntity=doctorBeen.get(position);
                    initPopupWindow(view,doctorBeen.get(position));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            sendLinkDoctor(doctorBeen.get(position));
                        }
                    });
                }
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }
    private void initPopupWindow(View v,DoctorEntity doctorEntity) {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeight =dm.heightPixels;
        // 将pixels转为dip
        int xoffInDip = SizeUtils.px2dip(getApplicationContext(),screenHeight);
// 用于PopupWindow的View
        View contentView = LayoutInflater.from(this).inflate(R.layout.layout_popup_shenqing, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        window = new PopupWindow(contentView,  ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
//        window.setOutsideTouchable(true);
        window.setAnimationStyle(R.style.popupwindow_anim);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        ImageView exit = (ImageView) contentView.findViewById(R.id.popup_sq_exit);
        TextView yisheng= (TextView) contentView.findViewById(R.id.pop_yisheng);
        TextView paidui= (TextView) contentView.findViewById(R.id.pop_dengdai);
        yisheng.setText("您选择的是："+doctorEntity.getUserName());
        int time=0;
        if (!MyStrUtil.isEmpty(doctorEntity.getSN())){
            time = Integer.parseInt(doctorEntity.getSN()) * 5;
        }
        String textSource = "您前面还有<font color='#67e300'><big>"+doctorEntity.getSN()+"</big></font>人在排队，大约需要等待<font color='#67e300'><big>"+time+"</big></font>分钟";
        paidui.setText(Html.fromHtml(textSource));
        Button quxiao= (Button) contentView.findViewById(R.id.pop_quxiao);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCancleDotor();
                backgroundAlpha(1f);
                AndroidUtil.showToast(ActivityDoctorList.this, "您取消了排队", 0);
                window.dismiss();
            }
        });
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCancleDotor();
                backgroundAlpha(1f);
                AndroidUtil.showToast(ActivityDoctorList.this, "您取消了排队", 0);
                window.dismiss();
            }
        });
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移
//        window.showAsDropDown(reLiaotian);
        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        window.showAtLocation(v, Gravity.CENTER, 0,0);
        backgroundAlpha(0.5f);
    }
    /**
     * 患者选择医生
     */
    public void sendLinkDoctor(DoctorEntity doctorEntity) {

        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();
        try {
            json.put("targetid", doctorEntity.getConnectionId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsonArray.put(json);
        SignalaUtils.getInstance(this).sendMessage("sendLinkDoctor", jsonArray);
    }

    /**
     * 通知医生取消排队
     */
    private void sendCancleDotor() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(doctorEntity.getConnectionId());
        SignalaUtils.getInstance(this).sendMessage("sendCancelDoctor", jsonArray);
    }
    /**
     * 通知患者视频
     */
    public void acceptMemberCallBack(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                window.dismiss();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ActivityDoctorList.this,ActivityMyOverConsultation.class);
                intent.putExtra("entity",doctorEntity);
                intent.putExtra("connectionId",contectid);
                contectid="";
                startActivity(intent);
                finish();
            }
        },1000);

    }


    /**
     * 通知患者退出排队
     */
    public void unAcceptMemberCallBack(){
        AndroidUtil.showToast(ActivityDoctorList.this, "医生通知退出排队", 0);
        window.dismiss();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public void initSDP(String connectionId) {
        contectid=connectionId;

    }

    private void initLoadMoreListener() {
        adapter.changeMoreStatus(adapter.NO_LOAD_MORE);
        recyclerViewDoctor.setOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
                //判断RecyclerView的状态 是空闲时，同时，是最后一个可见的ITEM时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapter.getItemCount()) {

                    //设置正在加载更多
                    adapter.changeMoreStatus(adapter.LOADING_MORE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            List<DoctorBean> doctorBeen = new ArrayList<DoctorBean>();
//                            for (int i = 0; i < 10; i++) {
//                                doctorBeen.add(new DoctorBean("", "肖某某", "主治医生,帅哥医生,资深老中医", "科室：外科", "妹啊表达对啊实打实读书读", "10"));
//                            }
//                            adapter.AddFooterItem(doctorBeen);
//                            //设置回到上拉加载更多
//                            adapter.changeMoreStatus(adapter.PULLUP_LOAD_MORE);
//                            Toast.makeText(ActivityDoctorList.this, "更新了 " + doctorBeen.size() + " 条数据", Toast.LENGTH_SHORT).show();
                        }
                    }, 3000);


                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //最后一个可见的ITEM
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case toolbar_left_btn:
                finish();
                break;
            case R.id.ys_btn_sousuo:
                String search = sousuoDoctor.getText().toString();
                if (!TextUtils.isEmpty(search)) {
                    if (TextUtils.isEmpty(Type)) {
                        List<DoctorEntity> temp = new ArrayList<>();
                        Pattern pn = Pattern.compile(search + "\\w|\\w" + search + "\\w|\\w" + search);
                        Matcher mr = null;
                        for (DoctorEntity entity : doctorBeen) {
                            mr = pn.matcher(entity.getUserName());
                            if (mr.find()) {
                                temp.add(entity);
                            }
                        }
                        doctorBeen.clear();
                        doctorBeen.addAll(temp);
                        adapter.notifyDataSetChanged();
                    } else {

                        getDoctor(search);

                    }

                } else {
                    AndroidUtil.showToast(this, "搜索内容不能为空", 0);

                }
                break;
            case R.id.text_all:
                Type = "2";
                getDoctor("");
                textAll.setTextColor(getResources().getColor(R.color.background_green));
                textOnline.setTextColor(getResources().getColor(R.color.text_black));
                textOutline.setTextColor(getResources().getColor(R.color.text_black));
                break;
            case R.id.text_online:
                Type = "0";
                textAll.setTextColor(getResources().getColor(R.color.text_black));
                textOnline.setTextColor(getResources().getColor(R.color.background_green));
                textOutline.setTextColor(getResources().getColor(R.color.text_black));
                doctorBeen.clear();
                doctorBeen.addAll(SendPushSigleR.list);
                adapter.notifyDataSetChanged();

                break;
            case R.id.text_outline:
                textAll.setTextColor(getResources().getColor(R.color.text_black));
                textOnline.setTextColor(getResources().getColor(R.color.text_black));
                textOutline.setTextColor(getResources().getColor(R.color.background_green));
                Type = "1";
                getDoctor("");
                break;

        }
    }
}
