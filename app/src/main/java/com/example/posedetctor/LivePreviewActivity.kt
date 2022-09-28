package com.example.posedetctor

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.google.mlkit.common.model.LocalModel
import com.google.mlkit.vision.demo.CameraSource
import java.io.IOException

class LivePreviewActivity : AppCompatActivity(){
    private var cameraSource: CameraSource? = null
    private var preview: CameraSourcePreview? = null
    private var graphicOverlay: GraphicOverlay? = null
    private var selectedModel = OBJECT_DETECTION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("test","LiveActivity start")
        setContentView(R.layout.activity_vision_live_preview)

        preview = findViewById(R.id.preview_view)
        if (preview == null) {
            Log.d("debug", "Preview is null")
        }
        graphicOverlay = findViewById(R.id.graphic_overlay)
        if (graphicOverlay == null) {
            Log.d("debug", "graphicOverlay is null")
        }
        /*val facingSwitch = findViewById<ToggleButton>(R.id.facing_switch)
        facingSwitch.setOnCheckedChangeListener(this)*/
        createCameraSource(selectedModel)
    }
    private fun createCameraSource(model: String) {
        Log.d("debug","createCameraSorce")
        // If there's no existing cameraSource, create one.
        if (cameraSource == null) {
            cameraSource = graphicOverlay?.let { CameraSource(this, it) }
        }
        try {
            //when (model) {
                /*OBJECT_DETECTION -> {
                    Log.i(TAG, "Using Object Detector Processor")
                    val objectDetectorOptions = PreferenceUtils.getObjectDetectorOptionsForLivePreview(this)
                    cameraSource!!.setMachineLearningFrameProcessor(
                        ObjectDetectorProcessor(this, objectDetectorOptions)
                    )
                }
                OBJECT_DETECTION_CUSTOM -> {
                    Log.i(TAG, "Using Custom Object Detector Processor")
                    val localModel =
                        LocalModel.Builder().setAssetFilePath("custom_models/object_labeler.tflite").build()
                    val customObjectDetectorOptions =
                        PreferenceUtils.getCustomObjectDetectorOptionsForLivePreview(this, localModel)
                    cameraSource!!.setMachineLearningFrameProcessor(
                        ObjectDetectorProcessor(this, customObjectDetectorOptions)
                    )
                }
                CUSTOM_AUTOML_OBJECT_DETECTION -> {
                    Log.i(TAG, "Using Custom AutoML Object Detector Processor")
                    val customAutoMLODTLocalModel =
                        LocalModel.Builder().setAssetManifestFilePath("automl/manifest.json").build()
                    val customAutoMLODTOptions =
                        PreferenceUtils.getCustomObjectDetectorOptionsForLivePreview(
                            this,
                            customAutoMLODTLocalModel
                        )
                    cameraSource!!.setMachineLearningFrameProcessor(
                        ObjectDetectorProcessor(this, customAutoMLODTOptions)
                    )
                }
                TEXT_RECOGNITION_LATIN -> {
                    Log.i(TAG, "Using on-device Text recognition Processor for Latin and Latin")
                    cameraSource!!.setMachineLearningFrameProcessor(
                        TextRecognitionProcessor(this, TextRecognizerOptions.Builder().build())
                    )
                }
                TEXT_RECOGNITION_CHINESE -> {
                    Log.i(TAG, "Using on-device Text recognition Processor for Latin and Chinese")
                    cameraSource!!.setMachineLearningFrameProcessor(
                        TextRecognitionProcessor(this, ChineseTextRecognizerOptions.Builder().build())
                    )
                }
                TEXT_RECOGNITION_DEVANAGARI -> {
                    Log.i(TAG, "Using on-device Text recognition Processor for Latin and Devanagari")
                    cameraSource!!.setMachineLearningFrameProcessor(
                        TextRecognitionProcessor(this, DevanagariTextRecognizerOptions.Builder().build())
                    )
                }
                TEXT_RECOGNITION_JAPANESE -> {
                    Log.i(TAG, "Using on-device Text recognition Processor for Latin and Japanese")
                    cameraSource!!.setMachineLearningFrameProcessor(
                        TextRecognitionProcessor(this, JapaneseTextRecognizerOptions.Builder().build())
                    )
                }
                TEXT_RECOGNITION_KOREAN -> {
                    Log.i(TAG, "Using on-device Text recognition Processor for Latin and Korean")
                    cameraSource!!.setMachineLearningFrameProcessor(
                        TextRecognitionProcessor(this, KoreanTextRecognizerOptions.Builder().build())
                    )
                }
                FACE_DETECTION -> {
                    Log.i(TAG, "Using Face Detector Processor")
                    val faceDetectorOptions = PreferenceUtils.getFaceDetectorOptions(this)
                    cameraSource!!.setMachineLearningFrameProcessor(
                        FaceDetectorProcessor(this, faceDetectorOptions)
                    )
                }
                BARCODE_SCANNING -> {
                    Log.i(TAG, "Using Barcode Detector Processor")
                    cameraSource!!.setMachineLearningFrameProcessor(BarcodeScannerProcessor(this))
                }
                IMAGE_LABELING -> {
                    Log.i(TAG, "Using Image Label Detector Processor")
                    cameraSource!!.setMachineLearningFrameProcessor(
                        LabelDetectorProcessor(this, ImageLabelerOptions.DEFAULT_OPTIONS)
                    )
                }
                IMAGE_LABELING_CUSTOM -> {
                    Log.i(TAG, "Using Custom Image Label Detector Processor")
                    val localClassifier =
                        LocalModel.Builder().setAssetFilePath("custom_models/bird_classifier.tflite").build()
                    val customImageLabelerOptions = CustomImageLabelerOptions.Builder(localClassifier).build()
                    cameraSource!!.setMachineLearningFrameProcessor(
                        LabelDetectorProcessor(this, customImageLabelerOptions)
                    )
                }
                CUSTOM_AUTOML_LABELING -> {
                    Log.i(TAG, "Using Custom AutoML Image Label Detector Processor")
                    val customAutoMLLabelLocalModel =
                        LocalModel.Builder().setAssetManifestFilePath("automl/manifest.json").build()
                    val customAutoMLLabelOptions =
                        CustomImageLabelerOptions.Builder(customAutoMLLabelLocalModel)
                            .setConfidenceThreshold(0f)
                            .build()
                    cameraSource!!.setMachineLearningFrameProcessor(
                        LabelDetectorProcessor(this, customAutoMLLabelOptions)
                    )
                }*/
                //POSE_DETECTION ->

                    val poseDetectorOptions = PreferenceUtils.getPoseDetectorOptionsForLivePreview(this)
                    Log.i(TAG, "Using Pose Detector with options $poseDetectorOptions")
                    val shouldShowInFrameLikelihood =
                        PreferenceUtils.shouldShowPoseDetectionInFrameLikelihoodLivePreview(this)
                    val visualizeZ = PreferenceUtils.shouldPoseDetectionVisualizeZ(this)
                    val rescaleZ = PreferenceUtils.shouldPoseDetectionRescaleZForVisualization(this)
                    val runClassification = PreferenceUtils.shouldPoseDetectionRunClassification(this)
                    cameraSource!!.setMachineLearningFrameProcessor(
                        PoseDetectorProcessor(
                            this,
                            poseDetectorOptions,
                            shouldShowInFrameLikelihood,
                            visualizeZ,
                            rescaleZ,
                            runClassification,
                            /* isStreamMode = */ true
                        )
                    )
                //}
                /*SELFIE_SEGMENTATION -> {
                    cameraSource!!.setMachineLearningFrameProcessor(SegmenterProcessor(this))
                }*/
                //else -> Log.d("debug", "Unknown model")

        } catch (e: Exception) {
            Log.e(TAG, "Can not create image processor: ", e)
            Toast.makeText(
                applicationContext,
                "Can not create image processor: " + e.message,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
    /**
     * Starts or restarts the camera source, if it exists. If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private fun startCameraSource() {
        if (cameraSource != null) {
            try {
                if (preview == null) {
                    Log.d(TAG, "resume: Preview is null")
                }
                if (graphicOverlay == null) {
                    Log.d(TAG, "resume: graphOverlay is null")
                }
                preview!!.start(cameraSource!!, graphicOverlay)
            } catch (e: IOException) {
                Log.e(TAG, "Unable to start camera source.", e)
                cameraSource!!.release()
                cameraSource = null
            }
        }
    }

    public override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        createCameraSource(selectedModel)
        startCameraSource()
    }/** Stops the camera. */
    override fun onPause() {
        super.onPause()
        preview?.stop()
    }
    public override fun onDestroy() {
        super.onDestroy()
        if (cameraSource != null) {
            cameraSource?.release()
        }
    }
    companion object {
        private const val OBJECT_DETECTION = "Object Detection"
        private const val OBJECT_DETECTION_CUSTOM = "Custom Object Detection"
        private const val CUSTOM_AUTOML_OBJECT_DETECTION = "Custom AutoML Object Detection (Flower)"
        private const val FACE_DETECTION = "Face Detection"
        private const val TEXT_RECOGNITION_LATIN = "Text Recognition Latin"
        private const val TEXT_RECOGNITION_CHINESE = "Text Recognition Chinese"
        private const val TEXT_RECOGNITION_DEVANAGARI = "Text Recognition Devanagari"
        private const val TEXT_RECOGNITION_JAPANESE = "Text Recognition Japanese"
        private const val TEXT_RECOGNITION_KOREAN = "Text Recognition Korean"
        private const val BARCODE_SCANNING = "Barcode Scanning"
        private const val IMAGE_LABELING = "Image Labeling"
        private const val IMAGE_LABELING_CUSTOM = "Custom Image Labeling (Birds)"
        private const val CUSTOM_AUTOML_LABELING = "Custom AutoML Image Labeling (Flower)"
        private const val POSE_DETECTION = "Pose Detection"
        private const val SELFIE_SEGMENTATION = "Selfie Segmentation"

        private const val TAG = "LivePreviewActivity"
    }
}