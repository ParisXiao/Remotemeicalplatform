package fr.pchab.webrtcclient;

import android.content.Context;
import android.hardware.Camera;
import android.opengl.EGLContext;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.webrtc.AudioSource;
import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoCapturerAndroid;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.util.HashMap;
import java.util.LinkedList;

public class WebRtcClient {
    private final static String TAG = WebRtcClient.class.getCanonicalName();
    private final static int MAX_PEER = 2;
    private boolean[] endPoints = new boolean[MAX_PEER];
    private PeerConnectionFactory factory;
    private HashMap<String, Peer> peers = new HashMap<>();
    private LinkedList<PeerConnection.IceServer> iceServers = new LinkedList<>();
    private PeerConnectionParameters pcParams;
    private MediaConstraints pcConstraints = new MediaConstraints();
    private MediaStream localMS;
    private VideoSource videoSource;
    private RtcListener mListener;
    private VideoRenderer.Callbacks localRender;
    private VideoRenderer.Callbacks remoteRender;
    private String RoomID;
    private Context context;

    public WebRtcClient(Context context, VideoRenderer.Callbacks localRender, VideoRenderer.Callbacks remoteRender, RtcListener listener, PeerConnectionParameters params) {
        this.mListener = listener;
        this.context = context;
        this.localRender = localRender;
        this.remoteRender = remoteRender;
        this.pcParams = params;
        PeerConnectionFactory.initializeAndroidGlobals(listener, true, true,
                params.videoCodecHwAcceleration);

        factory = new PeerConnectionFactory();
        new MessageHandler();

        if (AppConstant.isDebug) {
            iceServers.add(new PeerConnection.IceServer("turn:211.149.207.162:3478", "gad", "gad1234"));
        } else {
            iceServers.add(new PeerConnection.IceServer("turn:218.201.47.3:3478", "gad", "gad1234"));
        }
        pcConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveAudio", "true"));
        pcConstraints.mandatory.add(new MediaConstraints.KeyValuePair("OfferToReceiveVideo", "true"));
        pcConstraints.optional.add(new MediaConstraints.KeyValuePair("DtlsSrtpKeyAgreement", "true"));
        setCamera();
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public static String connectionId;

    public void receivSignal(JSONArray args) {
        String type = "";
        String sdp = "";
        String connectionId = "";
        int label = 0;
        String id = "";
        String candidate = "";


        JSONObject payload = new JSONObject();
        for (int i = 0; i < args.length(); i++) {
            try {
                String str = args.getString(i);
                JSONObject jsonObject = new JSONObject(str);
                if (jsonObject.has("ConnectionId")) {
                    connectionId = jsonObject.getString("ConnectionId");
                    RoomID = jsonObject.getString("RoomID");
                } else if (jsonObject.has("sdp")) {
                    JSONObject jsonSdp = jsonObject.getJSONObject("sdp");
                    type = jsonSdp.getString("type");
                    sdp = jsonSdp.getString("sdp");
                } else if (jsonObject.has("candidate")) {
                    JSONObject jsondate = jsonObject.getJSONObject("candidate");
                    type = "candidate";
                    id = jsondate.getString("sdpMid");
                    label = jsondate.getInt("sdpMLineIndex");
                    candidate = jsondate.getString("candidate");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.d("receiveSignal", args.opt(i).toString());
        }

        if (!TextUtils.isEmpty(type) && (!TextUtils.isEmpty(sdp) && !TextUtils.isEmpty(connectionId) || (!TextUtils.isEmpty(candidate) && !TextUtils.isEmpty(id)))) {

            if (!type.equals("init")) {
                try {
                    if (type.equals("candidate")) {
                        //Log.d("收到============================candidate");
                        payload.put("id", id);
                        payload.put("label", label);
                        payload.put("candidate", candidate);
                    } else {
                        payload.put("type", type);
                        payload.put("sdp", sdp);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            // if peer is unknown, try to add him
            if (!peers.containsKey(connectionId)) {
                // if MAX_PEER is reach, ignore the call
                int endPoint = findEndPoint();
                if (endPoint != MAX_PEER) {
                    Peer peer = addPeer(connectionId, endPoint);
                    peer.pc.addStream(localMS);
                    try {
                        commandMap.get(type).execute(connectionId, payload);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    commandMap.get(type).execute(connectionId, payload);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    public void initOffe(String connectionId) {

        if (!peers.containsKey(connectionId)) {
            // if MAX_PEER is reach, ignore the call
            int endPoint = findEndPoint();
            if (endPoint != MAX_PEER) {
                Peer peer = addPeer(connectionId, endPoint);
                peer.pc.addStream(localMS);
                try {
                    JSONObject payload = new JSONObject();
                    commandMap.get("init").execute(connectionId, payload);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                JSONObject payload = new JSONObject();
                commandMap.get("init").execute(connectionId, payload);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * Implement this interface to be notified of events.
     */
    public interface RtcListener {
        void onCallReady(String callId);

        void onCallReady2(String callId);

        void onStatusChanged(String newStatus);

        void onLocalStream(MediaStream localStream);

        void onAddRemoteStream(MediaStream remoteStream, int endPoint);

        void onRemoveRemoteStream(int endPoint);
    }

    private interface Command {
        void execute(String peerId, JSONObject payload) throws JSONException;
    }

    private class CreateOfferCommand implements Command {
        public void execute(String peerId, JSONObject payload) throws JSONException {
            Log.d(TAG, "CreateOfferCommand");
            Peer peer = peers.get(peerId);
            peer.pc.createOffer(peer, pcConstraints);
        }
    }

    private class CreateAnswerCommand implements Command {
        public void execute(String peerId, JSONObject payload) throws JSONException {
            Log.d(TAG, "CreateAnswerCommand");
            Peer peer = peers.get(peerId);
            SessionDescription sdp = new SessionDescription(
                    SessionDescription.Type.fromCanonicalForm(payload.getString("type")),
                    payload.getString("sdp")
            );
            peer.pc.setRemoteDescription(peer, sdp);
            peer.pc.createAnswer(peer, pcConstraints);
        }
    }

    private class SetRemoteSDPCommand implements Command {
        public void execute(String peerId, JSONObject payload) throws JSONException {
            Log.d(TAG, "SetRemoteSDPCommand");
            Peer peer = peers.get(peerId);
            SessionDescription sdp = new SessionDescription(
                    SessionDescription.Type.fromCanonicalForm(payload.getString("type")),
                    payload.getString("sdp")
            );
            peer.pc.setRemoteDescription(peer, sdp);
        }
    }

    private class AddIceCandidateCommand implements Command {
        public void execute(String peerId, JSONObject payload) throws JSONException {
            Log.d(TAG, "AddIceCandidateCommand");
            PeerConnection pc = peers.get(peerId).pc;
            if (pc.getRemoteDescription() != null) {
                IceCandidate candidate = new IceCandidate(
                        payload.getString("id"),
                        payload.getInt("label"),
                        payload.getString("candidate")
                );
                pc.addIceCandidate(candidate);
            }
        }
    }

    private HashMap<String, Command> commandMap;

    private class MessageHandler {

        private MessageHandler() {
            commandMap = new HashMap<>();
            commandMap.put("init", new CreateOfferCommand());
            commandMap.put("offer", new CreateAnswerCommand());
            commandMap.put("answer", new SetRemoteSDPCommand());
            commandMap.put("candidate", new AddIceCandidateCommand());
        }

    }

    private class Peer implements SdpObserver, PeerConnection.Observer {
        private PeerConnection pc;
        private String id;
        private int endPoint;

        @Override
        public void onCreateSuccess(final SessionDescription sdp) {
            // TODO: modify sdp to use pcParams prefered codecs
            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonSdp = new JSONObject();
                jsonSdp.put("type", sdp.type.canonicalForm());
                jsonSdp.put("sdp", sdp.description);
                jsonObject.put("sdp", jsonSdp);
                jsonArray.put(jsonObject.toString());
                jsonArray.put(id);
                SignalaUtils.getInstance(context).sendMessage("SendSignal", jsonArray);
                Log.d("SendSignal==========", "onCreateSuccess" + jsonArray.toString());
                pc.setLocalDescription(Peer.this, sdp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onSetSuccess() {
        }

        @Override
        public void onCreateFailure(String s) {
        }

        @Override
        public void onSetFailure(String s) {
        }

        @Override
        public void onSignalingChange(PeerConnection.SignalingState signalingState) {
        }

        @Override
        public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {
            if (iceConnectionState == PeerConnection.IceConnectionState.DISCONNECTED) {
                removePeer(id);
                mListener.onStatusChanged("DISCONNECTED");
            }
        }

        @Override
        public void onIceConnectionReceivingChange(boolean b) {

        }


        @Override
        public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {
        }

        @Override
        public void onIceCandidate(final IceCandidate candidate) {
            try {

                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                JSONObject jsonSdp = new JSONObject();

                jsonSdp.put("candidate", candidate.sdp);
                jsonSdp.put("sdpMLineIndex", candidate.sdpMLineIndex);
                jsonSdp.put("sdpMid", candidate.sdpMid);

                jsonObject.put("candidate", jsonSdp);
                jsonArray.put(jsonObject.toString());
                jsonArray.put(id);
                SignalaUtils.getInstance(context).sendMessage("SendSignal", jsonArray);
                Log.d("SendSignal==========", "发送candidate信令" + jsonArray.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onAddStream(MediaStream mediaStream) {
            Log.d(TAG, "onAddStream " + mediaStream.label());
            // remote streams are displayed from 1 to MAX_PEER (0 is localStream)
            mediaStream.videoTracks.get(0).addRenderer(new VideoRenderer(remoteRender));
            mListener.onAddRemoteStream(mediaStream, endPoint + 1);
        }

        @Override
        public void onRemoveStream(MediaStream mediaStream) {
            Log.d(TAG, "onRemoveStream " + mediaStream.label());
            removePeer(id);
        }



        @Override
        public void onDataChannel(DataChannel dataChannel) {
        }

        @Override
        public void onRenegotiationNeeded() {

        }

        public Peer(String id, int endPoint) {
            Log.d(TAG, "new Peer: " + id + " " + endPoint);
            this.pc = factory.createPeerConnection(iceServers, pcConstraints, this);
            this.id = id;
            this.endPoint = endPoint;

            pc.addStream(localMS);

            mListener.onLocalStream(localMS);
            mListener.onStatusChanged("CONNECTING");
        }
    }

    private Peer addPeer(String id, int endPoint) {
        Peer peer = new Peer(id, endPoint);
        peers.put(id, peer);

        endPoints[endPoint] = true;
        return peer;
    }

    private void removePeer(String id) {
        Peer peer = peers.get(id);
        mListener.onRemoveRemoteStream(peer.endPoint);
        peer.pc.close();
        peers.remove(peer.id);
        endPoints[peer.endPoint] = false;
    }

    /**
     * Call this method in Activity.onPause()
     */
    public void onPause() {
        if (videoSource != null) videoSource.stop();
    }

    /**
     * Call this method in Activity.onResume()
     */
    public void onResume() {
        if (videoSource != null) videoSource.restart();
    }

    /**
     * Call this method in Activity.onDestroy()
     */
    public void onDestroy() {
        for (Peer peer : peers.values()) {
            peer.pc.dispose();
        }
        peers.clear();
        if (videoSource != null) {
            videoSource.dispose();
            videoSource = null;
        }

        if (factory != null) {
            factory.dispose();

        }
        //factory.dispose();

    }

    private int findEndPoint() {
        for (int i = 0; i < MAX_PEER; i++) if (!endPoints[i]) return i;
        return MAX_PEER;
    }

    // 打开本地视频流
    public void setCamera() {
        //本地的流
        localMS = factory.createLocalMediaStream("ARDAMS");
        if (pcParams.videoCallEnabled) {
            MediaConstraints videoConstraints = getMediaConstraints();
            videoSource = factory.createVideoSource(getVideoCapturer(), videoConstraints);
        }
        AudioSource audioSource = factory.createAudioSource(new MediaConstraints());
        localMS.addTrack(factory.createAudioTrack("ARDAMSa0", audioSource));
        VideoTrack videoTrack = factory.createVideoTrack("ARDAMSv0", videoSource);
        videoTrack.addRenderer(new VideoRenderer(localRender));
        localMS.addTrack(videoTrack);
    }

    private MediaConstraints getMediaConstraints() {
        MediaConstraints videoConstraints = new MediaConstraints();
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("maxHeight", Integer.toString(pcParams.videoHeight)));
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("maxWidth", Integer.toString(pcParams.videoWidth)));
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("maxFrameRate", Integer.toString(pcParams.videoFps)));
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("minFrameRate", Integer.toString(pcParams.videoFps)));
        return videoConstraints;
    }

    private VideoCapturer getVideoCapturer() {
        String frontCameraDeviceName = getNameOfFrontFacingDevice();
        return VideoCapturerAndroid.create(frontCameraDeviceName);
    }

    public static String getNameOfFrontFacingDevice() {
        for (int i = 0; i < Camera.getNumberOfCameras(); ++i) {
            Camera.CameraInfo info = new Camera.CameraInfo();

            try {
                Camera.getCameraInfo(i, info);
                if (info.facing == 1) {
                    return getDeviceName(i);
                }
            } catch (Exception var3) {
                Log.e("VideoCapturerAndroid", "getCameraInfo failed on index " + i, var3);
            }
        }

        return null;
    }

    public static String getDeviceName(int index) {
        Camera.CameraInfo info = new Camera.CameraInfo();

        try {
            Camera.getCameraInfo(index, info);
        } catch (Exception var3) {
            Log.e("VideoCapturerAndroid", "getCameraInfo failed on index " + index, var3);
            return null;
        }

        String facing = info.facing == 1 ? "front" : "back";
        return "Camera " + index + ", Facing " + facing + ", Orientation " + info.orientation;
    }
}
