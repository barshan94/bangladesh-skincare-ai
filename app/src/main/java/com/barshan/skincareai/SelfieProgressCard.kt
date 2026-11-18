package com.barshan.skincareai

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetectorOptions
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
internal fun SelfieProgressCard() {
    val context = LocalContext.current
    var status by remember { mutableStateOf(context.getString(R.string.selfie_privacy)) }
    var records by remember { mutableStateOf(SelfieProgressStore.list(context)) }
    var pendingFile by remember { mutableStateOf<File?>(null) }
    var pendingUri by remember { mutableStateOf<Uri?>(null) }
    var deleteTarget by remember { mutableStateOf<SelfieRecord?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { captured ->
        val file = pendingFile
        val uri = pendingUri
        if (!captured || file == null || uri == null) {
            status = context.getString(R.string.selfie_capture_cancelled)
            file?.delete()
        } else {
            status = context.getString(R.string.selfie_checking)
            val options = FaceDetectorOptions.Builder()
                .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
                .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_NONE)
                .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_NONE)
                .setMinFaceSize(0.12f)
                .build()
            val detector = FaceDetection.getClient(options)
            runCatching { InputImage.fromFilePath(context, uri) }
                .onSuccess { input ->
                    detector.process(input)
                        .addOnSuccessListener { faces ->
                            val face = faces.singleOrNull()
                            val bounds = face?.boundingBox
                            val areaRatio = if (bounds == null || input.width <= 0 || input.height <= 0) {
                                0f
                            } else {
                                bounds.width().toFloat() * bounds.height().toFloat() /
                                    (input.width.toFloat() * input.height.toFloat())
                            }
                            val centered = bounds != null &&
                                bounds.centerX() in (input.width * 0.25f).toInt()..(input.width * 0.75f).toInt() &&
                                bounds.centerY() in (input.height * 0.18f).toInt()..(input.height * 0.82f).toInt()
                            if (face == null) {
                                file.delete()
                                status = context.getString(R.string.selfie_face_count_error)
                            } else if (areaRatio < 0.12f || !centered) {
                                file.delete()
                                status = context.getString(R.string.selfie_framing_error)
                            } else {
                                runCatching {
                                    SelfieProgressStore.save(context, file)
                                }.onSuccess { saved ->
                                    records = SelfieProgressStore.list(context)
                                    status = context.getString(R.string.selfie_saved)
                                }.onFailure {
                                    file.delete()
                                    status = context.getString(R.string.selfie_error)
                                }
                            }
                            detector.close()
                        }
                        .addOnFailureListener {
                            file.delete()
                            status = context.getString(R.string.selfie_error)
                            detector.close()
                        }
                }
                .onFailure {
                    file.delete()
                    status = context.getString(R.string.selfie_error)
                    detector.close()
                }
        }
        pendingFile = null
        pendingUri = null
    }

    Card(colors = CardDefaults.cardColors(containerColor = Color.White), shape = RoundedCornerShape(20.dp)) {
        Column(
            Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(stringResource(R.string.selfie_title), fontWeight = FontWeight.Bold, color = Color(0xFF486B5A))
            Text(stringResource(R.string.selfie_description), color = Color(0xFF1E2924))
            Text(stringResource(R.string.selfie_framing_only), color = Color(0xFF5D6962))
            Button(
                onClick = {
                    runCatching {
                        val directory = File(context.cacheDir, "selfie-capture").apply { mkdirs() }
                        val file = File(directory, "capture-${System.currentTimeMillis()}.jpg")
                        val uri = FileProvider.getUriForFile(
                            context,
                            "${context.packageName}.fileprovider",
                            file
                        )
                        pendingFile = file
                        pendingUri = uri
                        cameraLauncher.launch(uri)
                    }.onFailure {
                        status = context.getString(R.string.selfie_error)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.selfie_capture))
            }
            Text(status, color = Color(0xFF486B5A), fontWeight = FontWeight.Medium)
            if (records.isEmpty()) {
                Text(stringResource(R.string.selfie_empty), color = Color(0xFF5D6962))
            } else {
                records.forEach { record ->
                    val image = remember(record.fileName) {
                        val options = BitmapFactory.Options().apply { inSampleSize = 4 }
                        BitmapFactory.decodeFile(SelfieProgressStore.file(context, record).absolutePath, options)
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (image != null) {
                            Image(
                                bitmap = image.asImageBitmap(),
                                contentDescription = stringResource(R.string.selfie_thumbnail_description),
                                modifier = Modifier.height(92.dp),
                                contentScale = ContentScale.Fit
                            )
                        }
                        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text(
                                SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
                                    .format(Date(record.capturedAt)),
                                color = Color(0xFF1E2924),
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(stringResource(R.string.selfie_private_saved), color = Color(0xFF5D6962))
                            TextButton(onClick = { deleteTarget = record }) {
                                Text(stringResource(R.string.selfie_delete))
                            }
                        }
                    }
                }
            }
        }
    }

    deleteTarget?.let { target ->
        AlertDialog(
            onDismissRequest = { deleteTarget = null },
            title = { Text(stringResource(R.string.selfie_delete_title)) },
            text = { Text(stringResource(R.string.selfie_delete_body)) },
            confirmButton = {
                TextButton(onClick = {
                    SelfieProgressStore.delete(context, target)
                    records = SelfieProgressStore.list(context)
                    deleteTarget = null
                    status = context.getString(R.string.selfie_deleted)
                }) { Text(stringResource(R.string.selfie_delete)) }
            },
            dismissButton = {
                TextButton(onClick = { deleteTarget = null }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}
