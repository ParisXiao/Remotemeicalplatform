package fr.pchab.webrtcclient;

import android.content.Context;
import android.content.OperationApplicationException;
import android.util.Log;

import com.zsoft.signala.hubs.HubConnection;
import com.zsoft.signala.hubs.HubOnDataCallback;
import com.zsoft.signala.hubs.IHubProxy;
import com.zsoft.signala.transport.StateBase;
import com.zsoft.signala.transport.longpolling.LongPollingTransport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lanluo on 2017/1/7.
 */

public class SignalaUtils {

    private static SignalaUtils signalaUtils;
    private HubConnection con;

    public IHubProxy getHub() {
        return hub;
    }

    public void setHub(IHubProxy hub) {
        this.hub = hub;
    }

    private IHubProxy hub = null;
    private Context context;

    public  synchronized static SignalaUtils getInstance(Context context) {
        if(signalaUtils==null)
           signalaUtils = new SignalaUtils(context);
        return signalaUtils;
    }

    public void close() {
        con.Stop();
        con=null;
        signalaUtils=null;

    }

    private SignalaUtils(Context context) {
        this.context = context.getApplicationContext();
        initSignal(context);
    }
    /**
     * 初始化Signal
     */
    private void initSignal(Context context) {

        String url = "https://h.gxwdyf.com:5083/signalr/hubs";
        if(AppConstant.isDebug){
            url = AppConstant.TESTSIGNALA;
        }else{
            url = AppConstant.SIGNALA;
        }
        con = new HubConnection(url, context, new LongPollingTransport()){
            @Override
            public void OnError(Exception exception) {
                super.OnError(exception);

                Log.d("exception",exception.toString());
            }


            @Override
            public void OnMessage(String message) {
                super.OnMessage(message);
                Log.d("message",message);
            }

            @Override
            public void OnStateChanged(StateBase oldState, StateBase newState) {
                super.OnStateChanged(oldState, newState);



            }
        };

        startSignalA();
        //sendMessage();
    }



    /**
     * 链接推送服务器注册方法
     */
    private void startSignalA()
    {
        if(con!=null)
            //
            try {
                hub=con.CreateHubProxy("WebRtcHub");
            } catch (OperationApplicationException e) {
                e.printStackTrace();
            }

        con.Start();

        /**
         * 全局消息返回
         */

        hub.On("getErrorCallBack", new HubOnDataCallback()
        {
            @Override
            public void OnReceived(JSONArray args) {

                for(int i=0; i<args.length(); i++)
                {
                    Log.d("getErrorCallBack",args.opt(i).toString());
                }
            }
        });



        /**
         * 患者获取处方通知
         */
        hub.On("sendCaseDualCallBack", new HubOnDataCallback() {
            @Override
            public void OnReceived(JSONArray args) {
                for(int i=0; i<args.length(); i++)
                {
                    Log.d("sendCaseDualCallBack",args.opt(i).toString());
                }
            }
        });






    }

    /**
     * 发送消息
     * @param method
     * @param jsonArray
     */
    public void sendMessage(String method,JSONArray jsonArray){
        Log.d("发送的数据=========method",jsonArray.toString());
        hub.Invoke(method,jsonArray,null);


    }




}
