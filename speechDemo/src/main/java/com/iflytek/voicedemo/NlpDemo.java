package com.iflytek.voicedemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.iflytek.aiui.AIUIAgent;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.aiui.AIUIEvent;
import com.iflytek.aiui.AIUIListener;
import com.iflytek.aiui.AIUIMessage;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import adapter.ChatMsgViewAdapter;
import enty.Answer;

import enty.ChatMsgEntity;

public class NlpDemo extends Activity implements OnClickListener {
	private static String TAG = NlpDemo.class.getSimpleName();

    private Context mContext;
	private Toast mToast;	
	private EditText mNlpText;
	
	private AIUIAgent mAIUIAgent = null;
	private int mAIUIState = AIUIConstant.STATE_IDLE;
	private ListView lv_showmessage;


    private ChatMsgViewAdapter mAdapter1;
    private List<ChatMsgEntity> chatLists;
	@SuppressLint("ShowToast")
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.nlpdemo);
        mContext= NlpDemo.this;
        chatLists = new ArrayList<>();
		initLayout();
		
		mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}
	
	/**
	 * 初始化Layout。
	 */
	private void initLayout(){
		/*findViewById(R.id.text_nlp_start).setOnClickListener(this);*/
		findViewById(R.id.nlp_start).setOnClickListener(this);

		lv_showmessage = (ListView) findViewById(R.id.lv_showmessage);

		findViewById(R.id.nlp_stop).setOnClickListener(this);




	}
	
	int ret = 0;// 函数调用返回值
	@Override
	public void onClick(View view) 
	{				
		if( !checkAIUIAgent() ){
			return;
		}
		
		switch (view.getId()) {
		/*// 开始文本理解
		case R.id.text_nlp_start:
			startTextNlp();
			break;*/
		// 开始语音理解
		case R.id.nlp_start:
			startVoiceNlp();
			break;
		// 停止语音理解
		case R.id.nlp_stop:
			// AIUI 是连续会话，一次 start 后，可以连续的录音并返回结果；要停止需要调用 stop
			stopVoiceNlp();
			break;
		/*case R.id.update_lexicon:
			updateLexicon();
			break;*/
		default:
			break;
		}
	}
	
	private String getAIUIParams() {
		String params = "";

		AssetManager assetManager = getResources().getAssets();
		try {
			InputStream ins = assetManager.open( "cfg/aiui_phone.cfg" );
			byte[] buffer = new byte[ins.available()];

			ins.read(buffer);
			ins.close();

			params = new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return params;
	}
	
	private boolean checkAIUIAgent(){
		if( null == mAIUIAgent ){
			Log.i( TAG, "create aiui agent" );
			mAIUIAgent = AIUIAgent.createAgent( this, getAIUIParams(), mAIUIListener );
			AIUIMessage startMsg = new AIUIMessage(AIUIConstant.CMD_START, 0, 0, null, null);
			mAIUIAgent.sendMessage( startMsg );
		}
		
		if( null == mAIUIAgent ){
			final String strErrorTip = "创建 AIUI Agent 失败！";
			showTip( strErrorTip );
			this.mNlpText.setText( strErrorTip );
		}
		
		return null != mAIUIAgent;
	}
	
	private void startVoiceNlp(){
		Log.i( TAG, "start voice nlp" );
		/*mNlpText.setText("");*/
		
		// 先发送唤醒消息，改变AIUI内部状态，只有唤醒状态才能接收语音输入
		// 默认为oneshot 模式，即一次唤醒后就进入休眠，如果语音唤醒后，需要进行文本语义，请将改段逻辑copy至startTextNlp()开头处
		if( AIUIConstant.STATE_WORKING != 	this.mAIUIState ){
			AIUIMessage wakeupMsg = new AIUIMessage(AIUIConstant.CMD_WAKEUP, 0, 0, "", null);
			mAIUIAgent.sendMessage(wakeupMsg);
		}
		
		// 打开AIUI内部录音机，开始录音
		String params = "sample_rate=16000,data_type=audio";
		AIUIMessage writeMsg = new AIUIMessage( AIUIConstant.CMD_START_RECORD, 0, 0, params, null );
		mAIUIAgent.sendMessage(writeMsg);
	}
	
	private void stopVoiceNlp(){
		Log.i( TAG, "stop voice nlp" );
		// 停止录音
		String params = "sample_rate=16000,data_type=audio";
		AIUIMessage stopWriteMsg = new AIUIMessage(AIUIConstant.CMD_STOP_RECORD, 0, 0, params, null);
		
		mAIUIAgent.sendMessage(stopWriteMsg);
	}
	
	private void startTextNlp(){
		Log.i( TAG, "start text nlp" );
		String text = mNlpText.getText().toString();
		mNlpText.setText("");
		String params = "data_type=text";
		
		//if( TextUtils.isEmpty(text) ){
			text = "合肥明天的天气怎么样？";
		//}
		
		byte[] textData = text.getBytes();
		
		AIUIMessage msg = new AIUIMessage(AIUIConstant.CMD_WRITE, 0, 0, params, textData);
		mAIUIAgent.sendMessage(msg);
	}
	
	/*private void updateLexicon(){
		String params = null;
		String contents = FucUtil.readFile(this, "userwords","utf-8");
		try{
			JSONObject joAiuiLexicon = new JSONObject();
			joAiuiLexicon.put( "name", "userword" );
			joAiuiLexicon.put( "content", contents );
			params = joAiuiLexicon.toString();
		}catch( Throwable e ){
			e.printStackTrace();
			showTip( e.getLocalizedMessage() );
		}//end of try-catch
		
		mNlpText.setText(contents);

		AIUIMessage msg = new AIUIMessage(AIUIConstant.CMD_UPLOAD_LEXICON, 0, 0, params, null);
		mAIUIAgent.sendMessage(msg);
	}
	*/
	private AIUIListener mAIUIListener = new AIUIListener() {

		@Override
		public void onEvent(AIUIEvent event) {
			switch (event.eventType) {
				case AIUIConstant.EVENT_WAKEUP: 
					Log.i( TAG,  "on event: "+ event.eventType );
					showTip( "进入识别状态" );
				break;
	
				case AIUIConstant.EVENT_RESULT: {
					Log.i( TAG,  "on event: "+ event.eventType );
					try {
						JSONObject bizParamJson = new JSONObject(event.info);
						JSONObject data = bizParamJson.getJSONArray("data").getJSONObject(0);
						JSONObject params = data.getJSONObject("params");
						JSONObject content = data.getJSONArray("content").getJSONObject(0);
						
						if (content.has("cnt_id")) {
							String cnt_id = content.getString("cnt_id");
							JSONObject cntJson = new JSONObject(new String(event.data.getByteArray(cnt_id), "utf-8"));
	
							/*mNlpText.append( "\n" );
							mNlpText.append(cntJson.toString());*/
							
							String sub = params.optString("sub");
							if ("nlp".equals(sub)) {
								// 解析得到语义结果
								String resultStr = cntJson.optString("intent");
								
								// // TODO: 2017/10/20  需要做的就是对获取的进行解析,最后播放出来   和listview的展示 
                                //解析添加到bean上面
                                Gson gson = new Gson();
                                Answer answerBean = gson.fromJson(resultStr, Answer.class);
                                String clinetSpeakTest = answerBean.answer.question.question;

                                ChatMsgEntity chatMsgEntity = new ChatMsgEntity();
                                chatMsgEntity.setMsgType(false);
                                chatMsgEntity.setName("问题");
                                chatMsgEntity.setDate(getSystemCurrentTimeOne());
                                chatMsgEntity.setText(clinetSpeakTest);
                                chatLists.add(chatMsgEntity);


                                //  huida
                                ChatMsgEntity chatMsgEntity1 = new ChatMsgEntity();
                                String responseSpeakTest =answerBean.text;
                                chatMsgEntity1.setMsgType(true);
                                chatMsgEntity1.setName("回答");
                                chatMsgEntity1.setDate(getSystemCurrentTimeOne());
                                chatMsgEntity1.setText(responseSpeakTest);
                                chatLists.add(chatMsgEntity1);

                                //  刷新适配器

                                initAdapter();


								Log.i( TAG, resultStr );


                            }
						}
					} catch (Throwable e) {
						e.printStackTrace();
						/*mNlpText.append( "\n" );
						mNlpText.append( e.getLocalizedMessage() )*/;
					}
					
					/*mNlpText.append( "\n" );*/



				} break;
	
				case AIUIConstant.EVENT_ERROR: {
					Log.i( TAG,  "on event: "+ event.eventType );
					mNlpText.append( "\n" );
					mNlpText.append( "错误: "+event.arg1+"\n"+event.info );


				} break;
	
				case AIUIConstant.EVENT_VAD: {
					if (AIUIConstant.VAD_BOS == event.arg1) {
						showTip("找到vad_bos");
					} else if (AIUIConstant.VAD_EOS == event.arg1) {
						showTip("找到vad_eos");
					} else {
						showTip("" + event.arg2);
					}
				} break;
				
				case AIUIConstant.EVENT_START_RECORD: {
					Log.i( TAG,  "on event: "+ event.eventType );
					showTip("开始录音");
				} break;
				
				case AIUIConstant.EVENT_STOP_RECORD: {
					Log.i( TAG,  "on event: "+ event.eventType );
					showTip("停止录音");
				} break;
	
				case AIUIConstant.EVENT_STATE: {	// 状态事件
					mAIUIState = event.arg1;
					
					if (AIUIConstant.STATE_IDLE == mAIUIState) {
						// 闲置状态，AIUI未开启
						showTip("STATE_IDLE");
					} else if (AIUIConstant.STATE_READY == mAIUIState) {
						// AIUI已就绪，等待唤醒
						showTip("STATE_READY");
					} else if (AIUIConstant.STATE_WORKING == mAIUIState) {
						// AIUI工作中，可进行交互
						showTip("STATE_WORKING");
					}
				} break;
				
				case AIUIConstant.EVENT_CMD_RETURN:{
					if( AIUIConstant.CMD_UPLOAD_LEXICON == event.arg1 ){
						showTip( "上传"+ (0==event.arg2?"成功":"失败") );
					}
				}break;
				
				default:
					break;
			}
		}

	};

    private void initAdapter() {
        mAdapter1 = new ChatMsgViewAdapter(mContext, chatLists);
        lv_showmessage.setAdapter(mAdapter1);
    }


    private String getSystemCurrentTimeOne() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日   HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        // 格式化时间\
        String str = formatter.format(curDate);
        //ToastUtils.showToast(getContext(), str);
        return str;
    }

    @Override
    protected void onDestroy() {
    	super.onDestroy();
        
    	if( null != this.mAIUIAgent ){
    		AIUIMessage stopMsg = new AIUIMessage(AIUIConstant.CMD_STOP, 0, 0, null, null);
    		mAIUIAgent.sendMessage( stopMsg );
    		
    		this.mAIUIAgent.destroy();
    		this.mAIUIAgent = null;
    	}
    }
	
	private void showTip(final String str)
	{
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				mToast.setText(str);
				mToast.show();
			}
		});
	}


}
