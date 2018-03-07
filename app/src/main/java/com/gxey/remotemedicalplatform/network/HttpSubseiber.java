package com.gxey.remotemedicalplatform.network;

import com.gxey.remotemedicalplatform.model.ApiModel;

import rx.Subscriber;

/**
 * Created by lanluo on 16/9/14.
 */
public class HttpSubseiber<T>{
    public  interface  ResponseHandler<T>{

        public abstract void onSucceed(T data);
        public abstract void onFail(String msg);

    }

    public Subscriber<ApiModel<T>> getSubseiber(final ResponseHandler<T> hander){
      return   new  Subscriber<ApiModel<T>>(){
            @Override
            public void onNext(ApiModel<T> tBaseResult) {
                if(tBaseResult.isCode()){
                    hander.onSucceed(tBaseResult.getResultJson());
                }else{
                    hander.onFail(tBaseResult.getMessage());
                }
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if(e!=null){
                    hander.onFail(e.getMessage());
                }else{
                    hander.onFail("Fatal Exception thrown on Scheduler.Worker thread");
                }


            }
        };
    }



}
