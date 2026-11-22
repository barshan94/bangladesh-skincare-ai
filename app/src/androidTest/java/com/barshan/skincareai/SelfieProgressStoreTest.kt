package com.barshan.skincareai

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import java.io.File
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SelfieProgressStoreTest {
    private val context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    private val preferences
        get() = context.getSharedPreferences("private_selfie_progress", 0)

    @Before
    fun clearStoreBeforeTest() {
        preferences.edit().clear().commit()
        File(context.filesDir, "selfie-progress").deleteRecursively()
    }

    @After
    fun clearStoreAfterTest() {
        preferences.edit().clear().commit()
        File(context.filesDir, "selfie-progress").deleteRecursively()
    }

    private fun sourceFile(contents: ByteArray = byteArrayOf(1, 2, 3, 4)): File {
        return File(context.cacheDir, "selfie-store-test-${System.nanoTime()}.jpg").apply {
            writeBytes(contents)
        }
    }

    @Test
    fun savingCopiesPhotoIntoPrivateStorageAndPersistsMetadata() {
        val source = sourceFile()

        val saved = SelfieProgressStore.save(context, source)

        assertFalse("Temporary capture should be removed after a successful save.", source.exists())
        assertTrue("Saved photo must exist.", SelfieProgressStore.file(context, saved).isFile)
        assertTrue(
            "Saved photo must be inside app-private files storage.",
            SelfieProgressStore.file(context, saved).canonicalPath.startsWith(
                context.filesDir.canonicalPath + File.separator
            )
        )
        assertEquals(listOf(saved), SelfieProgressStore.list(context))
    }

    @Test
    fun deletingPhotoRemovesBothFileAndMetadata() {
        val saved = SelfieProgressStore.save(context, sourceFile())
        val savedFile = SelfieProgressStore.file(context, saved)
        assertTrue(savedFile.exists())

        SelfieProgressStore.delete(context, saved)

        assertFalse(savedFile.exists())
        assertTrue(SelfieProgressStore.list(context).isEmpty())
    }

    @Test
    fun storeRetainsOnlyTheNewestThirtyPhotos() {
        val savedRecords = (0 until 31).map { index ->
            SelfieProgressStore.save(
                context,
                sourceFile(byteArrayOf(index.toByte(), 7, 8))
            )
        }

        val retained = SelfieProgressStore.list(context)
        assertEquals("The most recently saved photo must remain first.", savedRecords.last(), retained.first())
        assertFalse(
            "The oldest photo must be pruned.",
            SelfieProgressStore.file(context, savedRecords.first()).exists()
        )
        assertEquals(30, retained.size)
        assertTrue(retained.all { SelfieProgressStore.file(context, it).isFile })
        val directory = File(context.filesDir, "selfie-progress")
        assertEquals(30, directory.listFiles()?.count { it.isFile } ?: 0)
    }

    @Test
    fun fullLocalDataResetDeletesPrivateSelfiesAndMetadata() {
        val saved = SelfieProgressStore.save(context, sourceFile())
        val savedFile = SelfieProgressStore.file(context, saved)
        assertTrue(savedFile.exists())

        LocalStore.clearUserData(context)

        assertFalse("Full reset must remove selfie image files.", savedFile.exists())
        assertTrue("Full reset must remove selfie metadata.", SelfieProgressStore.list(context).isEmpty())
    }

    @Test
    fun emptyCaptureIsRejectedWithoutCreatingMetadata() {
        val empty = sourceFile(byteArrayOf())

        val rejected = runCatching { SelfieProgressStore.save(context, empty) }

        assertTrue("An empty image must not be accepted.", rejected.isFailure)
        assertTrue(SelfieProgressStore.list(context).isEmpty())
        assertTrue(empty.exists())
    }
}
