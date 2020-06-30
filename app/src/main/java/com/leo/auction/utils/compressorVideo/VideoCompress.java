package com.leo.auction.utils.compressorVideo;

import android.os.AsyncTask;

import com.aten.compiler.utils.EmptyUtils;
import com.leo.auction.utils.compressorVideo.videocompression.VideoController;


/**
 * Created by Vincent Woo
 * Date: 2017/8/16
 * Time: 15:15
 */

public class VideoCompress {
    private static final String TAG = VideoCompress.class.getSimpleName();

    public static VideoCompressTask compressVideoHigh(String srcPath, String destPath, String tag, CompressListener listener) {
        VideoCompressTask task = new VideoCompressTask(listener, VideoController.COMPRESS_QUALITY_HIGH, tag);
        task.execute(srcPath, destPath);
        return task;
    }

    public static VideoCompressTask compressVideoMedium(String srcPath, String destPath, String tag, CompressListener listener) {
        VideoCompressTask task = new VideoCompressTask(listener, VideoController.COMPRESS_QUALITY_MEDIUM, tag);
        task.execute(srcPath, destPath);
        return task;
    }

    public static VideoCompressTask compressVideoLow(String srcPath, String destinationDir,String tag, CompressListener listener) {
        VideoCompressTask task =  new VideoCompressTask(listener, VideoController.COMPRESS_QUALITY_MEDIUM,tag);
        task.execute(srcPath, destinationDir);
        return task;
    }

    private static class VideoCompressTask extends AsyncTask<String, Float, String> {
        private CompressListener mListener;
        private int mQuality;
        private String mTag;

        public VideoCompressTask(CompressListener listener, int quality, String tag) {
            mListener = listener;
            mQuality = quality;
            mTag = tag;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mListener != null) {
                mListener.onStart();
            }
        }

        @Override
        protected String doInBackground(String... paths) {
            return VideoController.getInstance().convertVideo(paths[0], paths[1], mQuality, new VideoController.CompressProgressListener() {
                @Override
                public void onProgress(float percent) {
                    publishProgress(percent);
                }
            });
        }

        @Override
        protected void onProgressUpdate(Float... percent) {
            super.onProgressUpdate(percent);
            if (mListener != null) {
                mListener.onProgress(percent[0]);
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (mListener != null) {
                if (!EmptyUtils.isEmpty(result)) {
                    mListener.onSuccess(result,mTag);
                } else {
                    mListener.onFail();
                }
            }
        }
    }

    public interface CompressListener {
        void onStart();
        void onSuccess(String path, String tag);
        void onFail();
        void onProgress(float percent);
    }
}
