package com.chemin.smallserver.constant.service.controller;

import com.iflytek.cloud.speech.*;

/**
 * Create By zhengjing on 2018/4/13 14:56
 */
public class IflytekController {

//    public static void main(String[] args) {
//
//        SpeechUtility utility = SpeechUtility.createUtility(SpeechConstant.APPID + "=5ad05257");
//        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
//        // /设置发音人
//        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");
//        //设置语速
//        mTts.setParameter(SpeechConstant.SPEED, "50");
//
//        mTts.setParameter(SpeechConstant.VOLUME, "80");
//        //设置音量，范围 0~100 //设置合成音频保存位置(可自定义保存位置)，保存在“
//        // ./tts_test.pcm”
//        // 如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "/Users/zhengjing/doc/zhonghuilv/temp/tts_test.pcm");
//        // 3.开始合成
//        mTts.startSpeaking("语音合成测试程序", mSynListener);
//        System.err.println("????");
//    }

    //合成监听器
    private static SynthesizerListener mSynListener = new SynthesizerListener() {
        @Override
        public void onBufferProgress(int i, int i1, int i2, String s) {

        }

        @Override
        public void onSpeakBegin() {

        }

        @Override
        public void onSpeakProgress(int i, int i1, int i2) {

        }

        @Override
        public void onSpeakPaused() {

        }

        @Override
        public void onSpeakResumed() {

        }

        @Override
        public void onCompleted(SpeechError speechError) {

            System.err.println("speechError:" + speechError);
        }

        @Override
        public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {

        }
    };
}
